import axios from "axios";
import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router-dom";
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from "yup";
function UserCreate() {
  const [provinces, setProvinces] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      const data = await fetch(`https://provinces.open-api.vn/api/p/`);
      const dataJSON = await data.json();
      setProvinces(dataJSON);
    };
    fetchData();
  }, []);
  const createUser =async (data) =>{
    console.log(data);
    try {
          const response = await axios.post("http://localhost:8080/api/v1/users", 
            data
          );
          alert("Tạo người dùng thành công");
          console.log(response.data);
          navigate("/")
        } catch (error) {
          console.error(error);
        }
        
  }
  const schema = yup
    .object({
      name: yup.string().required("name is not null"),
      email: yup.string().required("email is not null").matches(/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/,"email is invalid"),
      phone: yup.string().required("phone is not null"),
      password: yup.string().required("password is not null"),
      // address: yup.string().required("address is required")
    })
    .required();
    const { register,handleSubmit, formState:{ errors } } = useForm({
        resolver: yupResolver(schema),
        mode:"all",
        defaultValues:{
          address:"Thành phố Hà Nội"
        }
      });
  return (
    <div>
      <div className="container mt-5 mb-5">
        <h2 className="text-center text-uppercase mb-3">Tạo user</h2>

        <form className="row justify-content-center" onSubmit={handleSubmit(createUser)}>
          <div className="col-md-6">
            <div className="bg-light p-4">
              <div className="mb-3">
                <label className="col-form-label">Name</label>
                <input
                  type="text"
                  id="name"
                  className="form-control"
                {...register("name")}
                />
                <p className="text-danger">{errors.name?.message}</p>
              </div>
              <div className="mb-3">
                <label className="col-form-label">Email</label>
                <input
                  type="text"
                  id="email"
                  className="form-control"
                {...register("email")}
                  
                />
                <p className="text-danger">{errors.email?.message}</p>
              </div>
              <div className="mb-3">
                <label className="col-form-label">Phone</label>
                <input
                  type="text"
                  id="phone"
                  className="form-control"
                {...register("phone")}
                  
                />
                <p className="text-danger">{errors.phone?.message}</p>
              </div>
              <div className="mb-3">
                <label className="col-form-label">Address</label>
                <select
                  className="form-select"
                  id="address"
                {...register("address")}
                >
                  {provinces.map((province) => (
                    <option key={province.code} value={province.name}>
                      {province.name}
                    </option>
                  ))}
                </select>
              </div>
              <div className="mb-3">
                <label className="col-form-label">Password</label>
                <input
                  type="password"
                  id="password"
                  className="form-control"
                {...register("password")}
                />
              </div>
            </div>
            <div className="text-center mt-3">
              <Link to="/">
                <button className="btn btn-secondary btn-back">Quay lại</button>
              </Link>
              <button className="btn btn-success" id="btn-save" type="submit">
                Tạo User
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}

export default UserCreate;
