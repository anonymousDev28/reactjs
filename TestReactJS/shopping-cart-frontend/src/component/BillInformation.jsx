import axios from "axios";
import React, { useEffect, useState } from "react";

function BillInformation() {
  const [total,setTotal] = useState(0);
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/cartItems/total")
      .then((response) => {
        setTotal(response.data);
        console.log(response.data)
      })
      .catch((error) => {
        console.log(error);
      });
  });
  return (
    <div>
      <div className="bill">
        <div className="border mb-2 p-3 fs-5 fw-normal d-flex justify-content-between align-items-center">
          <span className="text-black-50">Tạm tính:</span>
          <span className="text-primary" id="sub-total-money">
            {total}
          </span>
        </div>
        <div className="border mb-2 p-3 fs-5 fw-normal d-flex justify-content-between align-items-center">
          <span className="text-black-50">VAT (10%):</span>
          <span className="text-primary" id="vat-money">
            {
              total*10/100
            }
          </span>
        </div>
        <div className="border mb-2 p-3 fs-5 fw-normal d-flex justify-content-between align-items-center">
          <span className="text-black-50">Thành tiền:</span>
          <span className="text-primary" id="total-money">
            {total*90/100}
          </span>
        </div>
      </div>
    </div>
  );
}

export default BillInformation;
