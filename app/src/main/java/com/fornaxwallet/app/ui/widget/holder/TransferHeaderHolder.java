package com.fornaxwallet.app.ui.widget.holder;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fornaxwallet.app.R;
import com.fornaxwallet.app.entity.tokens.Token;

public class TransferHeaderHolder extends BinderViewHolder<Token> {

    public static final int VIEW_TYPE = 1128;

    private final TextView title;

    public TransferHeaderHolder(int resId, ViewGroup parent) {
        super(resId, parent);
        title = findViewById(R.id.name);
    }

    @Override
    public void bind(@Nullable Token token, @NonNull Bundle addition) {
        title.setText(R.string.select_tickets_transfer);
    }
}