import React, { useState } from 'react'
const colors = [
    '#3498db',
    '#9b59b6',
    '#e74c3c',
    '#2c3e50',
    '#d35400',
  ];
function BoxContainer() {
    const [numBoxes, setNumBoxes] = useState(5);
    const [totalBoxes, setTotalBoxes] = useState(5);
  
    const handleClick = (e) => {
      const box = e.target;
      box.style.display = 'none';
      setTotalBoxes(totalBoxes - 1);
    };
  
    const handleMoreBoxes = () => {
      setNumBoxes(numBoxes + 5);
      setTotalBoxes(totalBoxes + 5);
    };
  
    const boxes = Array.from({ length: numBoxes }).map((_, i) => (
      <div
        key={i}
        className="box"
        style={{ backgroundColor: colors[i % colors.length] }}
        onClick={handleClick}
      ></div>
    ));
  
    return (
      <div className="wrap">
        <h1>JS DOM</h1>
        <button id="btn" onClick={handleMoreBoxes}>
          More boxes
        </button>
        <h4 id="score">
          Total box: <span className="points">{totalBoxes}</span>
        </h4>
        <div className="boxes">{boxes}</div>
      </div>
    );
}

export default BoxContainer
