import React from "react";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function Login() {
  const navigate = useNavigate();
  const schema = yup
    .object({
      username: yup.string().required(),
      password: yup.string().required(),
    })
    .required();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(schema),
  });
  const onSubmit = async (data) => {
    const formData = new FormData();
    formData.append("username", data.username);
    formData.append("password", data.password);
    try {
      const response = await axios
        .post("http://localhost:8080/login", formData, {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((response) => {
          if (response.status === 200) {
            navigate("/home", {
              state: {
                username: response.data.username,
                email: response.data.email,
                avatar: response.data.avatar,
              },
            });
          }
        });
    } catch (error) {
      console.log(error);
      alert("username hoặc password chưa chính xác");
    }
  };
  return (
    <div className="container-fluid w-25">
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="form-group">
          <label htmlFor="inputUsername">Username</label>
          <input
            type="username"
            className="form-control"
            placeholder="Username"
            {...register("username", { required: true })}
          />
           {errors.username && <span className="error">This field is required</span>}
        </div>
        <div className="form-group">
          <label htmlFor="inputPassword">Password</label>
          <input
            type="password"
            className="form-control"
            placeholder="Password"
            {...register("password", { required: true })}
          />
           {errors.password && <span className="error">This field is required</span>}
        </div>
        <button type="submit" className="btn btn-primary">
          Submit
        </button>
      </form>
    </div>
  );
}

export default Login;
