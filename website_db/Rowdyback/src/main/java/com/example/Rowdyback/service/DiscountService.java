package com.example.Rowdyback.service;

import com.example.Rowdyback.model.Discount;
import com.example.Rowdyback.repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public Discount createDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    public Optional<Discount> getDiscountByCode(String code) {
        return Optional.ofNullable(discountRepository.findByCode(code));
    }

    public boolean deleteDiscount(String code) {
        Discount discount = discountRepository.findByCode(code);
        if (discount != null) {
            discountRepository.delete(discount);
            return true;
        }
        return false;
    }
    public List<Discount> findAllDiscounts() {
        return discountRepository.findAll();  // Uses JpaRepository's findAll method
    }


}
