package com.example.Rowdyback.controller;

import com.example.Rowdyback.model.Discount;
import com.example.Rowdyback.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping
    public ResponseEntity<Discount> createDiscount(@RequestBody Discount discount) {
        return ResponseEntity.ok(discountService.createDiscount(discount));
    }

    @GetMapping("/{code}")
    public ResponseEntity<Discount> getDiscountByCode(@PathVariable String code) {
        return discountService.getDiscountByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable String code) {
        boolean isDeleted = discountService.deleteDiscount(code);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Discount>> getAllDiscounts() {
        List<Discount> discounts = discountService.findAllDiscounts();  // Assuming this method exists in your service
        return ResponseEntity.ok(discounts);
    }


}
