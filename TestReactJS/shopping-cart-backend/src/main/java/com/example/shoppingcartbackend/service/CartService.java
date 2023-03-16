package com.example.shoppingcartbackend.service;

import com.example.shoppingcartbackend.controller.CartController;
import com.example.shoppingcartbackend.dto.CartItemDto;
import com.example.shoppingcartbackend.exception.BadRequestException;
import com.example.shoppingcartbackend.exception.NotFoundException;
import com.example.shoppingcartbackend.model.CartItem;
import com.example.shoppingcartbackend.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartItemRepository cartItemRepository;


    public List<CartItemDto> findAll() {
        return cartItemRepository.findAll();
    }

    public void deleteById(int id) {
        cartItemRepository.deleteById(id);
    }

    public void increaseById(int id) {
        CartItem cartItem = cartItemRepository.findById(id);
        if (cartItem != null){
            cartItem.setCount(cartItem.getCount()+1);
        }
        else {
            throw new NotFoundException("Không tồn tại id "+ id);
        }
    }

    public void decreaseById(int id) {
        CartItem cartItem = cartItemRepository.findById(id);
        if (cartItem != null){
            if(cartItem.getCount() == 1){
                throw new BadRequestException("số lượng phải lớn hon 0");
            }
            cartItem.setCount(cartItem.getCount()-1);
        }
        else {
            throw new NotFoundException("Không tồn tại id "+ id);
        }
    }
}
