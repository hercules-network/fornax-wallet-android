package com.fornaxwallet.app.ui.widget;

import java.math.BigInteger;
import java.util.List;

public interface NonFungibleAdapterInterface {
    List<BigInteger> getSelectedTokenIds(List<BigInteger> selection);
    int getSelectedGroups();
    void setRadioButtons(boolean selected);
}
