import { useState } from "react";
import reactLogo from "./assets/react.svg";
import "./App.css";

function App() {
  const [items, setItems] = useState([]);
  const [newItem, setNewItem] = useState("");
  const [isHidden, setIsHidden] = useState(false);
  const [hideLabel, setHideLabel] = useState("Hide");
  //Thêm 1 nút “add” + 1 ô input để nhập text. Mỗi khi bấm nút thêm 1 thẻ <li> có nội dung là nội dung trong ô input vào cuối danh sách
  const handleAddClick = () => {
    if (newItem.trim()) {
      setItems([...items, newItem]);
      setNewItem("");
    }
  };
  //Thêm 1 nút “remove”. Chức năng để xóa thẻ <li> cuối cùng của danh sách
  function handleRemove() {
    setItems((prevItems) => {
      const newItems = [...prevItems];
      newItems.pop();
      return newItems;
    });
  }
  //Thêm 1 nút “Hide” trên đầu của danh sách <ul>.
  // Khi bấm vào “Hide” thì <ul> sẽ ẩn. Đồng thời label của nút “Hide” => “Show”
  // Và ngược lại Khi bấm vào “Show” thì <ul> sẽ hiện. Đồng thời label của nút “Show” => “Hide”
  function handleToggle() {
    setIsHidden((prevIsHidden) => !prevIsHidden);
    setHideLabel((prevHideLabel) => (prevHideLabel === "Hide" ? "Show" : "Hide"));
  }
  return (
    <div className="">
      <button onClick={handleToggle}>{hideLabel}</button>
      <ul id="list" className={isHidden ? "hidden" : ""}>
        {items.map((item, index) => (
          <li key={index}>{item}</li>
        ))}
      </ul>
      <input
        type="text"
        value={newItem}
        onChange={(e) => setNewItem(e.target.value)}
      />
      <button onClick={handleAddClick}>add</button>
      <button onClick={handleRemove}>Remove</button>
    </div>
  );
}

export default App;
