package com.fornaxwallet.app.di;

import com.fornaxwallet.app.ui.ActivityFragment;
import com.fornaxwallet.app.ui.AddCustomRPCNetworkActivity;
import com.fornaxwallet.app.ui.AddTokenActivity;
import com.fornaxwallet.app.ui.AdvancedSettingsActivity;
import com.fornaxwallet.app.ui.AssetDisplayActivity;
import com.fornaxwallet.app.ui.BackupKeyActivity;
import com.fornaxwallet.app.ui.DappBrowserFragment;
import com.fornaxwallet.app.ui.Erc1155Activity;
import com.fornaxwallet.app.ui.Erc1155AssetDetailActivity;
import com.fornaxwallet.app.ui.Erc1155AssetListActivity;
import com.fornaxwallet.app.ui.Erc1155AssetSelectActivity;
import com.fornaxwallet.app.ui.Erc1155AssetsFragment;
import com.fornaxwallet.app.ui.Erc1155InfoFragment;
import com.fornaxwallet.app.ui.Erc20DetailActivity;
import com.fornaxwallet.app.ui.FunctionActivity;
import com.fornaxwallet.app.ui.GasSettingsActivity;
import com.fornaxwallet.app.ui.HelpActivity;
import com.fornaxwallet.app.ui.HomeActivity;
import com.fornaxwallet.app.ui.ImportTokenActivity;
import com.fornaxwallet.app.ui.ImportWalletActivity;
import com.fornaxwallet.app.ui.MyAddressActivity;
import com.fornaxwallet.app.ui.NameThisWalletActivity;
import com.fornaxwallet.app.ui.NewSettingsFragment;
import com.fornaxwallet.app.ui.RedeemAssetSelectActivity;
import com.fornaxwallet.app.ui.RedeemSignatureDisplayActivity;
import com.fornaxwallet.app.ui.SelectNetworkActivity;
import com.fornaxwallet.app.ui.SelectNetworkFilterActivity;
import com.fornaxwallet.app.ui.SellDetailActivity;
import com.fornaxwallet.app.ui.SendActivity;
import com.fornaxwallet.app.ui.SplashActivity;
import com.fornaxwallet.app.ui.TokenActivity;
import com.fornaxwallet.app.ui.TokenActivityFragment;
import com.fornaxwallet.app.ui.TokenDetailActivity;
import com.fornaxwallet.app.ui.TokenFunctionActivity;
import com.fornaxwallet.app.ui.TokenManagementActivity;
import com.fornaxwallet.app.ui.TokenScriptManagementActivity;
import com.fornaxwallet.app.ui.TransactionDetailActivity;
import com.fornaxwallet.app.ui.TransactionSuccessActivity;
import com.fornaxwallet.app.ui.TransferNFTActivity;
import com.fornaxwallet.app.ui.TransferTicketDetailActivity;
import com.fornaxwallet.app.ui.WalletActionsActivity;
import com.fornaxwallet.app.ui.WalletConnectActivity;
import com.fornaxwallet.app.ui.WalletConnectSessionActivity;
import com.fornaxwallet.app.ui.WalletFragment;
import com.fornaxwallet.app.ui.WalletsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = SplashModule.class)
    abstract SplashActivity bindSplashModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = AccountsManageModule.class)
    abstract WalletsActivity bindManageWalletsModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = ImportModule.class)
    abstract ImportWalletActivity bindImportWalletModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = TransactionDetailModule.class)
    abstract TransactionDetailActivity bindTransactionDetailModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = SendModule.class)
    abstract SendActivity bindSendModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = TransactionSuccessModule.class)
    abstract TransactionSuccessActivity bindTransactionSuccessModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = GasSettingsModule.class)
    abstract GasSettingsActivity bindGasSettingsModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = AddTokenModule.class)
    abstract AddTokenActivity bindAddTokenActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = RedeemSignatureDisplayModule.class)
    abstract RedeemSignatureDisplayActivity bindSignatureDisplayActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = TokenFunctionModule.class)
    abstract AssetDisplayActivity bindAssetDisplayActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = SellDetailModule.class)
    abstract SellDetailActivity bindSellDetailsActivity();

    @FragmentScope
    @ContributesAndroidInjector(modules = NewSettingsModule.class)
    abstract NewSettingsFragment bindNewSettingsFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    abstract ActivityFragment bindActivityFragment();

    @ActivityScope
    @ContributesAndroidInjector(modules = RedeemAssetSelectModule.class)
    abstract RedeemAssetSelectActivity bindRedeemTokenSelectActivity();

    @FragmentScope
    @ContributesAndroidInjector(modules = WalletModule.class)
    abstract WalletFragment bindWalletFragment();

    @ActivityScope
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity bindHomeActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = ImportTokenModule.class)
    abstract ImportTokenActivity bindImportTokenActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = TransferTicketDetailModule.class)
    abstract TransferTicketDetailActivity bindTransferTicketDetailActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = HelpModule.class)
    abstract HelpActivity bindHelpActivity();

    @FragmentScope
    @ContributesAndroidInjector(modules = DappBrowserModule.class)
    abstract DappBrowserFragment bindDappBrowserFragment();

    @ActivityScope
    @ContributesAndroidInjector(modules = Erc20DetailModule.class)
    abstract Erc20DetailActivity bindErc20DetailActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = WalletActionsModule.class)
    abstract WalletActionsActivity bindWalletActionsActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = BackupKeyModule.class)
    abstract BackupKeyActivity bindBackupKeyActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = MyAddressModule.class)
    abstract MyAddressActivity bindMyAddressActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = TokenFunctionModule.class)
    abstract TokenFunctionActivity bindTokenFunctionActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = TokenFunctionModule.class)
    abstract FunctionActivity bindFunctionActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = TokenFunctionModule.class)
    abstract TokenDetailActivity bindTokenDetailActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = TokenFunctionModule.class)
    abstract TokenActivity bindTokenActivity();

    @ContributesAndroidInjector(modules = SelectNetworkModule.class)
    abstract SelectNetworkActivity bindSelectNetworkActivity();

    @ContributesAndroidInjector(modules = CustomNetworkModule.class)
    abstract AddCustomRPCNetworkActivity bindAddCustomRPCNetworkActivity();

    @ContributesAndroidInjector(modules = SelectNetworkFilterModule.class)
    abstract SelectNetworkFilterActivity bindSelectNetworkFilterActivity();

    @ContributesAndroidInjector(modules = TokenManagementModule.class)
    abstract TokenManagementActivity bindTokenManagementActivity();

    @ContributesAndroidInjector(modules = AdvancedSettingsModule.class)
    abstract AdvancedSettingsActivity bindAdvancedSettingsActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = TokenScriptManagementModule.class)
    abstract TokenScriptManagementActivity bindTokenScriptManagementActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = WalletConnectModule.class)
    abstract WalletConnectActivity bindWalletConnectActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = WalletConnectModule.class)
    abstract WalletConnectSessionActivity bindWalletConnectSessionActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = Erc1155Module.class)
    abstract Erc1155Activity bindErc1155Activity();

    @ActivityScope
    @ContributesAndroidInjector(modules = Erc1155AssetDetailModule.class)
    abstract Erc1155AssetDetailActivity bindErc1155AssetDetailActivity();

    @FragmentScope
    @ContributesAndroidInjector(modules = Erc1155InfoModule.class)
    abstract Erc1155InfoFragment bindErc1155InfoFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = Erc1155AssetsModule.class)
    abstract Erc1155AssetsFragment bindErc1155AssetsFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = Erc1155AssetSelectModule.class)
    abstract Erc1155AssetSelectActivity bindErc1155AssetSelectActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = Erc1155AssetListModule.class)
    abstract Erc1155AssetListActivity bindErc1155AssetListActivity();

    @FragmentScope
    @ContributesAndroidInjector(modules = TokenActivityModule.class)
    abstract TokenActivityFragment bindTokenActivityFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = TransferTicketDetailModule.class)
    abstract TransferNFTActivity bindTransferNFTActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = NameThisWalletModule.class)
    abstract NameThisWalletActivity bindNameThisWalletActivity();
}