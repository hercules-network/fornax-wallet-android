package com.fornaxwallet.app.ui.widget.entity;

import com.fornaxwallet.app.ui.widget.holder.WarningHolder;

public class WarningSortedItem extends SortedItem<WarningData> {

    public WarningSortedItem(WarningData value, int weight) {
        super(WarningHolder.VIEW_TYPE, value, weight);
    }

    @Override
    public int compare(SortedItem other) {
        return weight - other.weight;
    }

    @Override
    public boolean areContentsTheSame(SortedItem newItem) {
        return false;
    }

    @Override
    public boolean areItemsTheSame(SortedItem other) {
        return other.viewType == viewType;
    }
}
