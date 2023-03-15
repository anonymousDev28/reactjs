import React, { useState } from "react";
const orders = [10000, 23000, 990000];
function Content() {
  const [total, setTotal] = useState(()=>{
    const rs = orders.reduce((a, b) => a + b, 0);
    console.log(rs);
    return rs;
  });
  const [user, setUser] = useState({
    id: 1,
    name: "Nguyen A",
    email: "test@gmail.com",
  });
  const [counter, setCounter] = useState(0);
  const [products, setProducts] = useState([
    { id: 1, name: "product 1", price: 20000 },
    { id: 2, name: "product 2", price: 30000 },
    { id: 3, name: "product 3", price: 50000 },
  ]);
  const randomName = () => {
    const rdName = `New Name ${Math.floor(Math.random() * 1000)}`;
    setUser({ ...user, name: rdName });
  };
  const randomPrice = () => {
    const productId = 1;
    const rdPrice = Math.floor(Math.random() * 100000);
    const newProducts = products.map((p) => {
      if (p.id === productId) {
        return { ...p, price: rdPrice };
      }
      return p;
    });
    setProducts(newProducts);
  };
  const deleleProduct = (id) => {
    const newProducts = products.filter((p) => p.id !== id);
    setProducts(newProducts);
  };
  const addCounter = () => {
    setCounter((counter) => counter + 1);
    setCounter((counter) => counter + 1);
    setCounter((counter) => counter + 1);
  };
  return (
    <div>
      {console.log("Render")}
      <h1>Counter : {counter}</h1>
      <button onClick={addCounter}>Add</button>
      <h1>User</h1>
      <hr />
      <p>
        {user.id} - {user.name} - {user.email}
      </p>
      <button onClick={randomName}>Random infor</button>
      <hr />
      <h1>Products</h1>
      <ul>
        {products.map((p) => (
          <li key={p.id}>
            {p.id}-{p.name}-{p.price}
            <button onClick={() => deleleProduct(p.id)}>Delete</button>
          </li>
        ))}
      </ul>
      <button onClick={randomPrice}>Random Price</button>
    </div>
  );
}

export default Content;
