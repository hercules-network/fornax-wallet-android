package com.fornaxwallet.app.viewmodel;

import com.fornaxwallet.app.repository.EthereumNetworkRepository;
import com.fornaxwallet.app.repository.EthereumNetworkRepositoryType;
import com.fornaxwallet.app.entity.NetworkInfo;

public class CustomNetworkViewModel extends BaseViewModel {
    private final EthereumNetworkRepositoryType ethereumNetworkRepository;

    CustomNetworkViewModel(
            EthereumNetworkRepositoryType ethereumNetworkRepository) {
        this.ethereumNetworkRepository = ethereumNetworkRepository;
    }

    public void addNetwork(String name, String rpcUrl, long chainId, String symbol, String blockExplorerUrl, String explorerApiUrl, boolean isTestnet, Long oldChainId) {
        this.ethereumNetworkRepository.addCustomRPCNetwork(name, rpcUrl, chainId, symbol, blockExplorerUrl, explorerApiUrl, isTestnet, oldChainId);
    }

    public NetworkInfo getNetworkInfo(long chainId) {
        return this.ethereumNetworkRepository.getNetworkByChain(chainId);
    }

    public boolean isTestNetwork(NetworkInfo network) {
        return !EthereumNetworkRepository.hasRealValue(network.chainId);
    }
}