package com.example.PromoFarma.Service;

import com.example.PromoFarma.Model.Seller;
import com.example.PromoFarma.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    // add a seller
    public Seller addSeller(String name) {
        Seller seller = new Seller();
        seller.setName(name);
        return sellerRepository.save(seller);
    }

    // delete a seller
    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }
}
