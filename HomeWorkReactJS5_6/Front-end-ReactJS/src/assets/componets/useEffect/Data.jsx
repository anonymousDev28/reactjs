import React, { useEffect, useState } from 'react'

function Data(){
    const [data,setData] = useState([]);
    const [type,setType] = useState("posts");
    useEffect(()=>{
        const fetchData = async ()=>{
            const data = await fetch(`https://jsonplaceholder.typicode.com/${type}`);
            const dataJSON = await data.json();
            setData(dataJSON);
        };

        fetchData();
    },[type])
    return (<div>
        <h2>Danh Sách {type}</h2>
        {["posts","photos","albums"].map(item=>(
            <button key={item} onClick={()=>setType(item)} style={type === item ?{color:"white",backgroundColor:"red"}:null}>
            {item}
            </button>
        ))}
            {/* <button onClick={()=>setType("posts")}>Posts</button>
            <button onClick={()=>setType("photos")}>Photos</button>
            <button onClick={()=>setType("albums")}>Albums</button> */}
        <ul>
            {data.map(item => (<li key={item.id}>{item.title}</li>))}
        </ul>
    </div>
            )
}

export default Data;