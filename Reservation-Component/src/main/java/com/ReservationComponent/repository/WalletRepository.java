package com.ReservationComponent.repository;

import com.ReservationComponent.model.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WalletRepository extends MongoRepository<Wallet, Long> {


}
