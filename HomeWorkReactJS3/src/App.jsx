import { useState } from "react";
import "./App.css";
import Login from "./component/Login";
import "bootstrap/dist/css/bootstrap.css";
import Home from "./component/Home";
import { Route, Router, Routes } from "react-router-dom";
function App() {
  return (
    // <div className="App">
    <>
    <Routes>
        <Route path="/">
          <Route index element={<Login/>} />
          <Route path="home" element={<Home/>} />
        </Route>   
      </Routes>
    </>
      
    // </div>
  );
}

export default App;
