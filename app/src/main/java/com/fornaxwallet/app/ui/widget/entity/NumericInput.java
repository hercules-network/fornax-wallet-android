package com.fornaxwallet.app.ui.widget.entity;

import android.content.Context;
import android.text.Editable;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import com.fornaxwallet.app.BuildConfig;
import com.fornaxwallet.app.util.BalanceUtils;
import com.fornaxwallet.app.util.LocaleUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class NumericInput extends AppCompatAutoCompleteTextView {
    private final Locale deviceSettingsLocale = LocaleUtils.getDeviceLocale(getContext());

    public NumericInput(@NonNull Context context) {
        super(context);
    }

    public NumericInput(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //ensure we use the decimal separator appropriate for the phone settings
        char separator = DecimalFormatSymbols.getInstance(deviceSettingsLocale).getDecimalSeparator();
        setKeyListener(DigitsKeyListener.getInstance("0123456789" + separator));
    }

    public BigDecimal getBigDecimalValue() {
        CharSequence text = super.getText();
        BigDecimal value = BigDecimal.ZERO;

        try {
            DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(deviceSettingsLocale);
            df.setParseBigDecimal(true);
            value = (BigDecimal) df.parseObject(text.toString());
        } catch (Exception e) {
            if (BuildConfig.DEBUG) e.printStackTrace();
        }

        return value;
    }
}