package com.fornaxwallet.app.ui.widget.entity;

import com.fornaxwallet.app.ui.widget.holder.TokenDescriptionHolder;
import com.fornaxwallet.app.entity.tokens.Token;

public class TokenBalanceSortedItem extends SortedItem<Token> {

    public TokenBalanceSortedItem(Token value) {
        super(TokenDescriptionHolder.VIEW_TYPE, value, 0);
    }

    @Override
    public int compare(SortedItem other) {
        return weight - other.weight;
    }

    @Override
    public boolean areContentsTheSame(SortedItem newItem) {
        return newItem.viewType == viewType
                || (((TokenBalanceSortedItem) newItem).value.getTokenCount() == value.getTokenCount())
                && ((TokenBalanceSortedItem) newItem).value.getFullName().equals(value.getFullName());
    }

    @Override
    public boolean areItemsTheSame(SortedItem other) {
        return other.viewType == viewType;
    }
}