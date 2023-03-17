import axios from 'axios';
import React, { useEffect, useState } from 'react'
import ProductItem from './ProductItem';

function ProductList() {
    const [cartItems, setCartItems] = useState([]);
    useEffect(() => {
        axios
          .get("http://localhost:8080/api/v1/cartItems")
          .then((response) => {
            setCartItems(response.data);
            console.log(response.data)
          })
          .catch((error) => {
            console.log(error);
          });
      }, []);
  return (
    <div>
        <div className="product-list">
                        {cartItems.map(item=>(
                            <ProductItem key = {item.id}  {...item}/>
                        ))}
                        
                        {/* <div className="product-item d-flex border mb-4">
                            <div className="image">
                                <img src="https://images.unsplash.com/photo-1612423284934-2850a4ea6b0f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mjl8fGNsb3RoZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60" alt="sản phẩm 2" />
                            </div>
                            <div className="info d-flex flex-column justify-content-between px-4 py-3 flex-grow-1">
                                <div>
                                    <div className="d-flex justify-content-between align-items-center">
                                        <h2 className="text-dark fs-5 fw-normal">
                                            Sản phẩm 2
                                        </h2>
                                        <h2 className="text-danger fs-5 fw-normal">
                                            400.000 VND
                                        </h2>
                                    </div>
                                    <div className="text-black-50">
                                        <div className="d-inline-block me-3">
                                            <button className="border py-2 px-3 d-inline-block fw-bold bg-light">-</button>
                                            <span className="py-2 px-3 d-inline-block fw-bold">1</span>
                                            <button className="border py-2 px-3 d-inline-block fw-bold bg-light">+</button>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <button className="text-primary border-0 bg-transparent fw-light">
                                        <span><i className="fa-solid fa-trash-can"></i></span>
                                        Xóa
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div className="product-item d-flex border mb-4">
                            <div className="image">
                                <img src="https://images.unsplash.com/photo-1564584217132-2271feaeb3c5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzJ8fGNsb3RoZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60" alt="sản phẩm 3" />
                            </div>
                            <div className="info d-flex flex-column justify-content-between px-4 py-3 flex-grow-1">
                                <div>
                                    <div className="d-flex justify-content-between align-items-center">
                                        <h2 className="text-dark fs-5 fw-normal">
                                            Sản phẩm 3
                                        </h2>
                                        <h2 className="text-danger fs-5 fw-normal">
                                            500.000 VND
                                        </h2>
                                    </div>
                                    <div className="text-black-50">
                                        <div className="d-inline-block me-3">
                                            <button className="border py-2 px-3 d-inline-block fw-bold bg-light">-</button>
                                            <span className="py-2 px-3 d-inline-block fw-bold">1</span>
                                            <button className="border py-2 px-3 d-inline-block fw-bold bg-light">+</button>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <button className="text-primary border-0 bg-transparent fw-light">
                                        <span><i className="fa-solid fa-trash-can"></i></span>
                                        Xóa
                                    </button>
                                </div>
                            </div>
                        </div> */}
                    </div>
    </div>
  )
}

export default ProductList
