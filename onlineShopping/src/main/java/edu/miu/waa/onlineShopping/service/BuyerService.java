package edu.miu.waa.onlineShopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.repository.BuyerRepository;

@Service
public class BuyerService {

	@Autowired
    private BuyerRepository buyerRepository;

    public Buyer findUserById(Long id) {
        return buyerRepository.findUserById(id);
    }

}
