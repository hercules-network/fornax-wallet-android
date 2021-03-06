package com.fornaxwallet.app.ui.widget.holder;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;

import com.fornaxwallet.app.R;
import com.fornaxwallet.app.entity.tokens.Token;
import com.fornaxwallet.app.entity.tokens.TokenCardMeta;
import com.fornaxwallet.app.entity.tokens.TokenTicker;
import com.fornaxwallet.app.repository.EthereumNetworkRepository;
import com.fornaxwallet.app.repository.TokensRealmSource;
import com.fornaxwallet.app.repository.entity.RealmTokenTicker;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TickerService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.ui.widget.OnTokenClickListener;
import com.fornaxwallet.app.widget.ChainName;
import com.fornaxwallet.app.widget.TokenIcon;

import java.math.BigDecimal;
import java.math.RoundingMode;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.fornaxwallet.ethereum.EthereumNetworkBase.MAINNET_ID;

public class TokenHolder extends BinderViewHolder<TokenCardMeta> implements View.OnClickListener, View.OnLongClickListener {

    public static final int VIEW_TYPE = 1005;
    public static final String EMPTY_BALANCE = "\u2014\u2014";

    private final TokenIcon tokenIcon;
    private final TextView balanceEth;
    private final TextView balanceCurrency;
    private final TextView text24Hours;
    private final TextView textAppreciation;
    private final TextView issuer;
    private final TextView issuerPlaceholder;
    private final TextView contractType;
    private final View contractSeparator;
    private final View layoutAppreciation;
    private final LinearLayoutCompat extendedInfo;
    private final AssetDefinitionService assetDefinition; //need to cache this locally, unless we cache every string we need in the constructor
    private final TokensService tokensService;
    private final TextView pendingText;
    private final RelativeLayout tokenLayout;
    private final ChainName testnet;
    private boolean primaryElement;

    private final Handler handler = new Handler(Looper.getMainLooper());

    public Token token;
    private OnTokenClickListener onTokenClickListener;

    public TokenHolder(ViewGroup parent, AssetDefinitionService assetService, TokensService tSvs) {
        super(R.layout.item_token, parent);

        tokenIcon = findViewById(R.id.token_icon);
        balanceEth = findViewById(R.id.eth_data);
        balanceCurrency = findViewById(R.id.balance_currency);
        text24Hours = findViewById(R.id.text_24_hrs);
        textAppreciation = findViewById(R.id.text_appreciation);
        issuer = findViewById(R.id.issuer);
        issuerPlaceholder = findViewById(R.id.issuerPlaceholder);
        contractType = findViewById(R.id.contract_type);
        contractSeparator = findViewById(R.id.contract_seperator);
        pendingText = findViewById(R.id.balance_eth_pending);
        tokenLayout = findViewById(R.id.token_layout);
        extendedInfo = findViewById(R.id.layout_extended_info);
        layoutAppreciation = findViewById(R.id.layout_appreciation);
        testnet = findViewById(R.id.chain_name);
        itemView.setOnClickListener(this);
        assetDefinition = assetService;
        tokensService = tSvs;
    }

    @Override
    public void bind(@Nullable TokenCardMeta data, @NonNull Bundle addition) {
        try {
            token = tokensService.getToken(data.getChain(), data.getAddress());
            if (token == null) {
                fillEmpty();
                return;
            } else if (data.nameWeight < 1000 && !token.isEthereum()) {
                //edge condition - looking at a contract as an account
                Token backupChain = tokensService.getToken(data.getChain(), "eth");
                if (backupChain != null) token = backupChain;
            }

            tokenLayout.setBackgroundResource(R.drawable.background_marketplace_event);
            if (EthereumNetworkRepository.isPriorityToken(token))
                extendedInfo.setVisibility(View.GONE);
            contractSeparator.setVisibility(View.GONE);

            //setup name and value (put these together on a single string to make wrap-around text appear better).
            String nameValue = token.getStringBalance() + " " + token.getFullName(assetDefinition, token.getTokenCount());
            balanceEth.setText(nameValue);

            primaryElement = false;

            tokenIcon.bindData(token, assetDefinition);
            tokenIcon.setOnTokenClickListener(onTokenClickListener);


            populateTicker();

            setContractType();

            setPendingAmount();

        } catch (Exception ex) {
            fillEmpty();
        }
    }

    @Override
    public void onDestroyView() {

    }

    private void setPendingAmount() {
        String pendingDiff = token.getPendingDiff();
        if (pendingDiff != null) {
            pendingText.setText(pendingDiff);
            pendingText.setTextColor(ContextCompat.getColor(getContext(), (pendingDiff.startsWith("-")) ? R.color.red : R.color.green));
        } else {
            pendingText.setText("");
        }
    }

    private void populateTicker() {
        TokenTicker ticker = tokensService.getTokenTicker(token);
        if (ticker != null || (token.isEthereum() && EthereumNetworkRepository.hasRealValue(token.tokenInfo.chainId))) {
            handleTicker(ticker);
        } else {
            balanceCurrency.setVisibility(View.GONE);
            layoutAppreciation.setVisibility(View.GONE);
            setIssuerDetails();
        }

        if (!token.isEthereum() && token.tokenInfo.chainId != MAINNET_ID) {
            showNetworkLabel();
        } else {
            hideNetworkLabel();
        }
    }

    private void handleTicker(TokenTicker ticker) {
        if (ticker != null) {
            primaryElement = true;
            hideIssuerViews();
            layoutAppreciation.setVisibility(View.VISIBLE);
            balanceCurrency.setVisibility(View.VISIBLE);
            setTickerInfo(ticker);
        } else {
            //Ethereum token without a ticker
            issuer.setVisibility(View.GONE);
            issuerPlaceholder.setVisibility(View.GONE);
            balanceCurrency.setVisibility(View.GONE);
            layoutAppreciation.setVisibility(View.GONE);
            primaryElement = true;
        }
    }

    private void showNetworkLabel() {
        testnet.setVisibility(View.VISIBLE);
        testnet.setChainID(token.tokenInfo.chainId);
    }

    private void hideNetworkLabel() {
        testnet.setVisibility(View.GONE);
    }

    private void fillEmpty() {
        balanceEth.setText(R.string.NA);
        balanceCurrency.setText(EMPTY_BALANCE);
    }

    private final Runnable clearElevation = new Runnable() {
        @Override
        public void run() {
            tokenLayout.setElevation(0.0f);
        }
    };

    @Override
    public void onClick(View v) {
        if (onTokenClickListener != null) {
            tokenLayout.setElevation(-10.0f);
            onTokenClickListener.onTokenClick(v, token, null, true);
            handler.postDelayed(clearElevation, 800);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (onTokenClickListener != null) {
            onTokenClickListener.onLongTokenClick(v, token, null);
        }

        return true;
    }

    public void setOnTokenClickListener(OnTokenClickListener onTokenClickListener) {
        this.onTokenClickListener = onTokenClickListener;
    }

    public void setOnLongClickListener(OnTokenClickListener onTokenClickListener) {
        this.onTokenClickListener = onTokenClickListener;
    }

    private void setIssuerDetails() {
        if (token.isEthereum())     // If token is eth and we get here, it's a testnet chain, show testnet
        {
            issuer.setVisibility(View.VISIBLE);
            issuer.setText(R.string.testnet);
            issuerPlaceholder.setVisibility(View.GONE);
            primaryElement = true;
        } else {
            String issuerName = assetDefinition.getIssuerName(token);
            if (issuerName != null && !issuerName.equalsIgnoreCase(getString(R.string.app_name))) //don't display issuer if it's fornaxwallet
            {
                issuer.setVisibility(View.VISIBLE);
                issuerPlaceholder.setVisibility(View.VISIBLE);
                primaryElement = true;
                issuer.setText(issuerName);
            } else {
                hideIssuerViews();
            }
        }
    }

    private void hideIssuerViews() {
        issuer.setVisibility(View.GONE);
        issuerPlaceholder.setVisibility(View.GONE);
        contractSeparator.setVisibility(View.GONE);
    }

    private void setContractType() {
        //Display contract type if required
        int contractStringId = token.getContractType();
        if (contractStringId > 0) {
            contractType.setText(contractStringId);
            contractType.setVisibility(View.VISIBLE);
            if (primaryElement) contractSeparator.setVisibility(View.VISIBLE);
        } else {
            contractType.setVisibility(View.GONE);
        }
    }

    private void setTickerInfo(TokenTicker ticker) {
        //Set the fiat equivalent (leftmost value)
        BigDecimal correctedBalance = token.getCorrectedBalance(18);
        BigDecimal fiatBalance = correctedBalance.multiply(new BigDecimal(ticker.price)).setScale(18, RoundingMode.DOWN);
        String converted = TickerService.getCurrencyString(fiatBalance.doubleValue());

        String lbl = getString(R.string.token_balance, "", converted);
        lbl += " " + ticker.priceSymbol;
        if (correctedBalance.compareTo(BigDecimal.ZERO) > 0) {
            issuer.setVisibility(View.GONE);
        } else {
            lbl = EMPTY_BALANCE;
        }

        balanceCurrency.setText(lbl);
        balanceCurrency.setTextColor(getContext().getColor(R.color.text_dark_gray));

        //This sets the 24hr percentage change (rightmost value)
        try {
            double percentage = Double.parseDouble(ticker.percentChange24h);
            String formattedPercents = (percentage < 0 ? "(" : "(+") + ticker.percentChange24h + "%)";
            text24Hours.setText(formattedPercents);
            text24Hours.setTextColor(ContextCompat.getColor(getContext(), percentage < 0 ? R.color.red : R.color.green));
        } catch (Exception ex) { /* Quietly */ }

        //This sets the crypto price value (middle amount)
        String formattedValue = TickerService.getCurrencyString(new BigDecimal(ticker.price).doubleValue());
        //TickerService.getCurrencyWithoutSymbol(new BigDecimal(ticker.price).doubleValue());

        lbl = getString(R.string.token_balance, "", formattedValue);
        //lbl += " " + ticker.priceSymbol;
        textAppreciation.setText(lbl);
        textAppreciation.setTextColor(getContext().getColor(R.color.text_dark_gray));

        tokensService.addTokenValue(token.tokenInfo.chainId, token.getAddress(), fiatBalance.floatValue());
    }
}