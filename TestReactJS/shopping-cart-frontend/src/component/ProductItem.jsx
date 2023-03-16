import React from 'react'

function ProductItem(props) {
//   const { cartItems } = props;
    console.log(props)
  return (
    <div>
       <div className="product-item d-flex border mb-4">
                            <div className="image">
                                <img src="https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg" alt="sản phẩm 1" />
                            </div>
                            <div className="info d-flex flex-column justify-content-between px-4 py-3 flex-grow-1">
                                <div>
                                    <div className="d-flex justify-content-between align-items-center">
                                        <h2 className="text-dark fs-5 fw-normal">
                                            {props.course.name}
                                        </h2>
                                        <h2 className="text-danger fs-5 fw-normal">
                                            {props.course.price}
                                        </h2>
                                    </div>
                                    <div className="text-black-50">
                                        <div className="d-inline-block me-3">
                                            <button className="border py-2 px-3 d-inline-block fw-bold bg-light">-</button>
                                            <span className="py-2 px-3 d-inline-block fw-bold">{props.course.categories.length}</span>
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
    </div>
  )
}

export default ProductItem
