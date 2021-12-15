package com.fornaxwallet.app.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.fornaxwallet.app.C;
import com.fornaxwallet.app.R;
import com.fornaxwallet.app.entity.StandardFunctionInterface;
import com.fornaxwallet.app.viewmodel.TransactionSuccessViewModel;
import com.fornaxwallet.app.viewmodel.TransactionSuccessViewModelFactory;
import com.fornaxwallet.app.widget.CopyTextView;
import com.fornaxwallet.app.widget.FunctionButtonBar;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class TransactionSuccessActivity extends BaseActivity implements StandardFunctionInterface {
    private String transactionHash;

    @Inject
    TransactionSuccessViewModelFactory viewModelFactory;
    TransactionSuccessViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_success);

        transactionHash = getIntent().getStringExtra(C.EXTRA_TXHASH);
        CopyTextView hashText = findViewById(R.id.tx_hash);
        hashText.setText(transactionHash);

        viewModel = new ViewModelProvider(this, viewModelFactory)
                .get(TransactionSuccessViewModel.class);

        toolbar();

        setTitle(getString(R.string.empty));

        FunctionButtonBar functionBar = findViewById(R.id.layoutButtons);
        functionBar.setupFunctions(this, new ArrayList<>(Collections.singletonList(R.string.action_show_tx_details)));
        functionBar.revealButtons();

        viewModel.tryToShowRateAppDialog(this);
    }

    @Override
    public void handleClick(String action, int actionId) {
        Intent intent = new Intent();
        intent.putExtra(C.EXTRA_TXHASH, transactionHash);
        setResult(RESULT_OK, intent);
        finish();
    }
}