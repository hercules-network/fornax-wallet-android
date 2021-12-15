package com.fornaxwallet.app.ui.widget.holder;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.ViewGroup;

import com.fornaxwallet.app.entity.tokens.Token;
import com.fornaxwallet.token.entity.TicketRange;
import com.fornaxwallet.app.service.AssetDefinitionService;

public class TicketHolder extends BaseTicketHolder {
    public static final int VIEW_TYPE = 1066;

    @Deprecated
    public TicketHolder(int resId, ViewGroup parent, Token ticket, AssetDefinitionService service) {
        super(resId, parent, ticket, service);
    }

    @Override
    public void bind(@Nullable TicketRange data, @NonNull Bundle addition) {
        super.bind(data, addition);

        ticketLayout.setOnClickListener(v -> {

        });
    }
}