package com.fornaxwallet.app.entity.walletconnect;

import com.fornaxwallet.app.repository.entity.RealmWCSession;

public class WalletConnectSessionItem {
    public final String name;
    public final String url;
    public final String icon;
    public final String sessionId;
    public final String localSessionId;
    public final long chainId;

    public WalletConnectSessionItem(RealmWCSession s) {
        name = s.getRemotePeerData().getName();
        url = s.getRemotePeerData().getUrl();
        icon = s.getRemotePeerData().getIcons().size() > 0 ? s.getRemotePeerData().getIcons().get(0) : null;
        sessionId = s.getSession().getTopic();
        localSessionId = s.getSessionId();
        chainId = s.getChainId() == 0 ? 1 : s.getChainId(); //older sessions without chainId set must be mainnet
    }
}