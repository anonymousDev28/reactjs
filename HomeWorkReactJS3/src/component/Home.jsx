import React from "react";
import { useLocation } from "react-router-dom";
function Home() {
  const location = useLocation();
  const {username,email,avatar} = location.state || {}; 
  return (
    <div>
      <h2>{username}</h2>
      <h2>{email}</h2>
      <img src={`${avatar}`} alt="" />
    </div>
  );
}

export default Home;
