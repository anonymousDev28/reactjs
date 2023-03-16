package com.example.shoppingcartbackend.repository;

import com.example.shoppingcartbackend.db.CartDB;
import com.example.shoppingcartbackend.db.CourseDB;
import com.example.shoppingcartbackend.dto.CartItemDto;
import com.example.shoppingcartbackend.exception.NotFoundException;
import com.example.shoppingcartbackend.model.CartItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CartItemRepository {

    public List<CartItem> findAll() {

        return CartDB.cart;
    }

    public void deleteById(int id) {
        CartItem cartItem = CartDB.cart.stream().filter(cartItem1 -> cartItem1.getId() == id).findFirst().orElse(null);
        if (cartItem != null){
            CartDB.cart.remove(cartItem);
        }
        else {
            throw new NotFoundException("Không tồn tại id "+ id);
        }
    }


    public CartItem findById(int id) {
        return CartDB.cart.stream().filter(cartItem -> cartItem.getId() == id).findFirst().orElse(null);
    }
}
