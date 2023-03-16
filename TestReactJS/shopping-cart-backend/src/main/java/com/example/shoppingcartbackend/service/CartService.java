package com.example.shoppingcartbackend.service;

import com.example.shoppingcartbackend.controller.CartController;
import com.example.shoppingcartbackend.db.CartDB;
import com.example.shoppingcartbackend.db.CourseDB;
import com.example.shoppingcartbackend.dto.CartItemDto;
import com.example.shoppingcartbackend.exception.BadRequestException;
import com.example.shoppingcartbackend.exception.NotFoundException;
import com.example.shoppingcartbackend.model.CartItem;
import com.example.shoppingcartbackend.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    CourseService courseService;
    public List<CartItemDto> findAll() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        List<CartItemDto> cartItemDtos = cartItems.stream().map(cartItem -> {
            CartItemDto cartItemDto =  new CartItemDto();
            cartItemDto.setId(cartItem.getId());
            cartItemDto.setCourse(CourseDB.courses.get(cartItem.getCourseId()));
            cartItemDto.setCount(cartItem.getCount());
            return cartItemDto;
        }).collect(Collectors.toList());
        return cartItemDtos;
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

    public Integer getTotal() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        return cartItems
                .stream()
                .mapToInt(item->item.getCount()*courseService.getCourseById(item.getId()).getPrice())
                .sum();
    }
}
