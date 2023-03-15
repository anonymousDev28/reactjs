import React, { useEffect, useState } from 'react'

function Post() {
    // th1: useEffect(callBack)
    useEffect(()=>{
        console.log("TH1: useEffect(callBack)");
    })
    const [count, setCount] = useState(0);
    const [count1, setCount1] = useState(0);

    const addCounter = () => {
        setCount((counter) => counter + 1);
      };
      const addCounter1 = () => {
        setCount1((counter) => counter + 1);
      };
  return (
    <div>
      {console.log("Render")}
      <h2>Count: {count}</h2>
      <button onClick={addCounter}>Add</button>
      <h2>Count: {count1}</h2>
      <button onClick={addCounter1}>Add</button>
    </div>
  )
}

export default Post