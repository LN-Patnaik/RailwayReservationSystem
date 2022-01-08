package com.ReservationComponent.service;

import com.ReservationComponent.model.Wallet;
import com.ReservationComponent.repository.WalletRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    WalletRepository walletRepository;

    @Override
    public Wallet getWalletByWalletId(long walletId) {
        Wallet wallet=walletRepository.findById(walletId).get();
        return wallet;
    }

    @Override
    public Wallet createWallet(Wallet wallet) {
        Wallet added_wallet=walletRepository.save(wallet);
        return added_wallet;
    }

    @Override
    public Wallet updateBalance(long walletId, double amount) {

        Optional<Wallet> walletOpt=walletRepository.findById(walletId);
        if(walletOpt.isPresent())
        {
            Wallet wallet = walletOpt.get();
            int availableBalance = wallet.getBalance();
            availableBalance+=amount;
            wallet.setBalance(availableBalance);
            Wallet updatedWallet = walletRepository.save(wallet);
            return updatedWallet;
        }
        return null;
    }

    @Override
    public String deleteWalletByWalletId(long walletId) {
        walletRepository.deleteById(walletId);
        return "Wallet deleted successfully";
    }

    @Override
    public Wallet updateWallet(Wallet wallet) {
        Optional<Wallet> walletOpt = walletRepository.findById(wallet.getWalletId());
        if(walletOpt.isPresent()){
            Wallet updatedWallet = walletRepository.save(wallet);
            return updatedWallet;
        }
        return null;
    }

    @Override
    public Wallet getWalletByUserId(String userId) {
        List<Wallet> walletList = walletRepository.findAll();
        if(!CollectionUtils.isEmpty(walletList)){
            Wallet wallet = walletList.stream().filter(x -> (StringUtils.isNotBlank(x.getUserId()) && x.getUserId().equals(userId))).collect(Collectors.toList()).get(0);
            return wallet;
        }
        return null;
    }
}
