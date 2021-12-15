package com.fornaxwallet.app.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fornaxwallet.app.C;
import com.fornaxwallet.app.R;
import com.fornaxwallet.app.entity.CreateWalletCallbackInterface;
import com.fornaxwallet.app.entity.CustomViewSettings;
import com.fornaxwallet.app.entity.ErrorEnvelope;
import com.fornaxwallet.app.entity.Operation;
import com.fornaxwallet.app.entity.Wallet;
import com.fornaxwallet.app.entity.WalletConnectActions;
import com.fornaxwallet.app.repository.EthereumNetworkRepository;
import com.fornaxwallet.app.service.KeyService;
import com.fornaxwallet.app.service.WalletConnectService;
import com.fornaxwallet.app.ui.widget.adapter.WalletsAdapter;
import com.fornaxwallet.app.ui.widget.divider.ListDivider;
import com.fornaxwallet.app.viewmodel.WalletsViewModel;
import com.fornaxwallet.app.viewmodel.WalletsViewModelFactory;
import com.fornaxwallet.app.widget.AWalletAlertDialog;
import com.fornaxwallet.app.widget.AddWalletView;
import com.fornaxwallet.app.widget.SignTransactionDialog;
import com.fornaxwallet.app.widget.SystemView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class WalletsActivity extends BaseActivity implements
        View.OnClickListener,
        AddWalletView.OnNewWalletClickListener,
        AddWalletView.OnImportWalletClickListener,
        AddWalletView.OnWatchWalletClickListener,
        AddWalletView.OnCloseActionListener,
        CreateWalletCallbackInterface {
    @Inject
    WalletsViewModelFactory walletsViewModelFactory;
    WalletsViewModel viewModel;

    private RecyclerView list;
    private SwipeRefreshLayout refreshLayout;
    private SystemView systemView;
    private Dialog dialog;
    private AWalletAlertDialog aDialog;
    private WalletsAdapter adapter;
    private final Handler handler = new Handler();
    private Wallet selectedWallet;

    private boolean requiresHomeRefresh;
    private String dialogError;
    private final long balanceChain = EthereumNetworkRepository.getOverrideToken().chainId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallets);
        toolbar();
        setTitle(getString(R.string.title_change_add_wallet));
        requiresHomeRefresh = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initViewModel();
        initViews();
    }

    private void initViewModel() {
        if (viewModel == null) {
            systemView = findViewById(R.id.system_view);
            viewModel = new ViewModelProvider(this, walletsViewModelFactory)
                    .get(WalletsViewModel.class);
            viewModel.error().observe(this, this::onError);
            viewModel.progress().observe(this, systemView::showProgress);
            viewModel.wallets().observe(this, this::onFetchWallets);
            viewModel.defaultWallet().observe(this, this::onChangeDefaultWallet);
            viewModel.createdWallet().observe(this, this::onCreatedWallet);
            viewModel.createWalletError().observe(this, this::onCreateWalletError);
            viewModel.noWalletsError().observe(this, this::noWallets);
        }

        viewModel.onPrepare(balanceChain); //adjust here to change which chain the wallet show the balance of, eg use CLASSIC_ID for an Eth Classic wallet
    }

    protected Activity getThisActivity() {
        return this;
    }

    private void noWallets(Boolean aBoolean) {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
    }

    private void initViews() {
        refreshLayout = findViewById(R.id.refresh_layout);
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));

        adapter = new WalletsAdapter(this, this::onSetWalletDefault, viewModel.getWalletInteract());
        list.setAdapter(adapter);
        list.addItemDecoration(new ListDivider(this));

        systemView.attachRecyclerView(list);
        systemView.attachSwipeRefreshLayout(refreshLayout);
        refreshLayout.setOnRefreshListener(this::onSwipeRefresh);
    }

    private void onSwipeRefresh() {
        viewModel.swipeRefreshWallets(); //check all records
    }

    private void onCreateWalletError(ErrorEnvelope errorEnvelope) {
        dialogError = errorEnvelope.message;
        if (handler != null) handler.post(displayWalletError);
    }

    private Runnable displayWalletError = new Runnable() {
        @Override
        public void run() {
            aDialog = new AWalletAlertDialog(getThisActivity());
            aDialog.setTitle(R.string.title_dialog_error);
            aDialog.setIcon(AWalletAlertDialog.ERROR);
            aDialog.setMessage(TextUtils.isEmpty(dialogError)
                    ? getString(R.string.error_create_wallet)
                    : dialogError);
            aDialog.setButtonText(R.string.dialog_ok);
            aDialog.setButtonListener(v -> aDialog.dismiss());
            aDialog.show();
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        hideDialog();
        viewModel.onPause(); //no need to update balances if view isn't showing
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null) adapter.onDestroy();
    }

    @Override
    public void onBackPressed() {
        // User can't start work without wallet.
        if (adapter.getItemCount() > 0) {
            finish();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (CustomViewSettings.canChangeWallets()) getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int action_add = R.id.action_add;
        switch (item.getItemId()) {
            case action_add: {
                onAddWallet();
            }
            break;
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initViewModel();

        if (requestCode >= SignTransactionDialog.REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS && requestCode <= SignTransactionDialog.REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS + 10) {
            Operation taskCode = Operation.values()[requestCode - SignTransactionDialog.REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS];
            if (resultCode == RESULT_OK) {
                viewModel.completeAuthentication(taskCode);
            } else {
                viewModel.failedAuthentication(taskCode);
            }
        } else if (requestCode == C.IMPORT_REQUEST_CODE) {
            showToolbar();
            if (resultCode == RESULT_OK) {
                Snackbar.make(systemView, getString(R.string.toast_message_wallet_imported), Snackbar.LENGTH_SHORT)
                        .show();

                Wallet importedWallet = data.getParcelableExtra(C.Key.WALLET);
                if (importedWallet != null) {
                    requiresHomeRefresh = true;
                    viewModel.setDefaultWallet(importedWallet);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.try_again) {
            viewModel.fetchWallets();
        }
    }

    @Override
    public void onNewWallet(View view) {
        hideDialog();
        viewModel.newWallet(this, this);
    }

    @Override
    public void onWatchWallet(View view) {
        hideDialog();
        viewModel.watchWallet(this);
    }

    @Override
    public void onImportWallet(View view) {
        hideDialog();
        viewModel.importWallet(this);
    }

    @Override
    public void onClose(View view) {
        hideDialog();
    }

    private void onAddWallet() {
        AddWalletView addWalletView = new AddWalletView(this);
        addWalletView.setOnNewWalletClickListener(this);
        addWalletView.setOnImportWalletClickListener(this);
        addWalletView.setOnWatchWalletClickListener(this);
        addWalletView.setOnCloseActionListener(this);
        dialog = new BottomSheetDialog(this);
        dialog.setContentView(addWalletView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        BottomSheetBehavior behavior = BottomSheetBehavior.from((View) addWalletView.getParent());
        dialog.setOnShowListener(dialog -> behavior.setPeekHeight(addWalletView.getHeight()));
        dialog.show();
    }

    private void onChangeDefaultWallet(Wallet wallet) {
        if (adapter == null) return;

        if (selectedWallet != null && !wallet.sameAddress(selectedWallet.address)) {
            requiresHomeRefresh = true;
        }

        adapter.setDefaultWallet(wallet);
        if (requiresHomeRefresh) {
            viewModel.stopUpdates();
            requiresHomeRefresh = false;
            viewModel.showHome(this);

            Intent bIntent = new Intent(this, WalletConnectService.class);
            bIntent.setAction(String.valueOf(WalletConnectActions.DISCONNECT.ordinal()));
            bIntent.putExtra("wallet", selectedWallet);
            startService(bIntent);
        }

        selectedWallet = wallet;
    }

    private void onFetchWallets(Wallet[] wallets) {
        enableDisplayHomeAsUp();
        if (adapter != null) adapter.setWallets(wallets);
        invalidateOptionsMenu();
    }

    private void onCreatedWallet(Wallet wallet) {
        hideToolbar();
        viewModel.setDefaultWallet(wallet);
        callNewWalletPage(wallet);
        finish();
    }

    private void callNewWalletPage(Wallet wallet) {
        Intent intent = new Intent(this, WalletActionsActivity.class);
        intent.putExtra("wallet", wallet);
        intent.putExtra("currency", viewModel.getNetwork().symbol);
        intent.putExtra("walletCount", adapter.getItemCount());
        intent.putExtra("isNewWallet", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    private void onError(ErrorEnvelope errorEnvelope) {
        systemView.showError(errorEnvelope.message, this);
    }

    private void onSetWalletDefault(Wallet wallet) {
        requiresHomeRefresh = true;
        viewModel.setDefaultWallet(wallet);
    }

    private void hideDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }

        if (aDialog != null && aDialog.isShowing()) {
            aDialog.dismiss();
            aDialog = null;
        }
    }

    @Override
    public void HDKeyCreated(String address, Context ctx, KeyService.AuthenticationLevel level) {
        if (address == null) onCreateWalletError(new ErrorEnvelope(""));
        else viewModel.StoreHDWallet(address, level);
    }

    @Override
    public void keyFailure(String message) {
        onCreateWalletError(new ErrorEnvelope(message));
    }

    @Override
    public void cancelAuthentication() {
        onCreateWalletError(new ErrorEnvelope(getString(R.string.authentication_cancelled)));
    }

    @Override
    public void fetchMnemonic(String mnemonic) {

    }
}