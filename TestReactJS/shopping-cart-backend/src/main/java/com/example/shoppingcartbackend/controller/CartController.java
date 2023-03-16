package com.example.shoppingcartbackend.controller;

import com.example.shoppingcartbackend.dto.CartItemDto;
import com.example.shoppingcartbackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cartItems")
public class CartController {
    @Autowired
    CartService cartService;
    @GetMapping
    public List<CartItemDto> get(){
        return cartService.findAll();
    }
    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable int id){
        cartService.deleteById(id);
    }
    @PutMapping(value = "{id}/increment")
    public void putIncreament(@PathVariable int id){
        cartService.increaseById(id);
    }
    @PutMapping(value = "{id}/decrement")
    public void putDecreament(@PathVariable int id){
        cartService.decreaseById(id);
    }
    @GetMapping(value = "total")
    public Integer getTotal(){
        return cartService.getTotal();
    }
}
