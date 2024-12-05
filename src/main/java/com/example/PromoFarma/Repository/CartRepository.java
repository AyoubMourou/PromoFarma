package com.example.PromoFarma.Repository;

import com.example.PromoFarma.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long idUser);
    void deleteByUserId(Long idUser);
    void deleteByUserIdAndProductId(Long idUser, Long productId);
}
