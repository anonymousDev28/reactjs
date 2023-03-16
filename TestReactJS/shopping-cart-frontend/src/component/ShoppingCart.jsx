import React, { useState } from 'react'
import ProductItem from './ProductItem';
import ProductList from './ProductList';
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap.bundle"
import "../assets/Shopping-cart.css"
import BillInformation from './BillInformation';
function ShoppingCart() {
//   const addToCart = (product) => {
    // setCartItems([...cartItems, products]);
//   };
  return (
    <div>
         <div className="shopping-cart-container mt-5">
        <div className="container">
            <div className="row">
                <div className="col-md-12">
                    <div className="mb-4">
                        <h2>Shopping Cart</h2>
                    </div>
                </div>
            </div>

            <p className="fst-italic message">Không có sản phẩm trong giỏ hàng</p>
            <div className="row shopping-cart">
                <div className="col-md-8">
                    <ProductList/>
                </div>
                <div className="col-md-4">
                    <BillInformation/>
                </div>
            </div>
        </div>
    </div>
        {/* <ProductList/> */}
    </div>
  )
}

export default ShoppingCart
