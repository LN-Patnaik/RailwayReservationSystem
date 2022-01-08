package com.ReservationComponent.service;

import com.ReservationComponent.model.Wallet;

public interface WalletService {

    public Wallet getWalletByWalletId(long walletId);

    public Wallet createWallet(Wallet wallet);

    public Wallet updateBalance(long walletId, double amount);

    public String deleteWalletByWalletId(long walletId);

    public Wallet updateWallet(Wallet wallet);

    public Wallet getWalletByUserId(String userId);

}
