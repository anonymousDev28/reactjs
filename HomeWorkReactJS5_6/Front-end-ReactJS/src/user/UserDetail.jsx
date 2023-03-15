import axios from "axios";
import React, { useEffect, useRef, useState } from "react";
import { Link, useParams } from "react-router-dom";
function UserDetail() {
  let { userId } = useParams();
  // const { user } = props.location.state;
  const [user, setUser] = useState({});
  const [provinces, setProvinces] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const data = await fetch(`https://provinces.open-api.vn/api/p/`);
      const dataJSON = await data.json();
      setProvinces(dataJSON);
    };
    fetchData();
  }, []);
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [address, setAddress] = useState("");
  const [password, setPassword] = useState("");
  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [passwordChanged, setPasswordChanged] = useState(false);
  const modal = useRef(null);
  // const fadeRef = useRef(null);
  useEffect(() => {
    // Gọi API để lấy thông tin user tương ứng với ID từ đường dẫn URL
    fetch(`http://localhost:8080/api/v1/users/${userId}`)
      .then((response) => response.json())
      .then((data) => {
        setUser(data);
        setName(data.name);
        setEmail(data.email);
        setAddress(data.address);
        setPhone(data.phone);
        setPassword(data.password);
      });
  }, [userId]);

  const id = userId;
  const handleUpdate = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.put("http://localhost:8080/api/v1/users", {
        id,
        name,
        email,
        phone,
        address,
        password,
      });
      //   console.log(response.data);
    } catch (error) {
      console.error(error);
    }
    alert("Cập nhật thành công");
  };
  const handleChangeAvatar = async (e) => {
    const file = e.target.files[0];
    console.log(file);
    const formData = new FormData();
    formData.append("file", file);
    try {
      const rs = await axios.put(
        `http://localhost:8080/api/v1/users/${userId}/update-avatar`,
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );
      console.log(rs);
      setUser({ ...user, avatar: rs.data.url });
    } catch (error) {
      console.error(error);
    }
  };
  // handle password
  const handleUpdatePassword = async (event) => {
    event.preventDefault();
    const formData = {
      oldPassword: oldPassword,
      newPassword: newPassword,
    };
    try {
      const response = await axios.put(
        `http://localhost:8080/api/v1/users/${userId}/update-password`,
        formData,
        {
          // chỉ cần thiết khi gửi lên dạng JSON
          // headers:{
          //   "Content-Type":"multipart/form-data"
          // }
        }
      );
      console.log(response);
      alert(response.data);
      // document.querySelector("#modal-change-password").style.display = 'none'
      // document.querySelector("#modal-change-password").hide();
      // console.log(modal);
      // let myModal = new bootstrap.Modal(modal.current, {
      //   keyboard: false
      // })
      // myModal.hide();
      // modal.current?.hide();
      setPasswordChanged(true);
    } catch (error) {
      alert(error.response.data.message);
    }
  };
  const handleForgotPassword = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post(
        `http://localhost:8080/api/v1/users/${userId}/forgot-password`,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );
      alert("new password is " + response.data);
    } catch (error) {
      console.log(error);
    }
  };
  // useEffect(() => {
  //   if (passwordChanged) {
  //     const timeoutId = setTimeout(() => {
  //       fadeRef.current?.hide();
  //       setPasswordChanged(false);
  //     }, 3000);
  //     return () => clearTimeout(timeoutId);
  //   }
  // }, [passwordChanged]);
  return (
    <div>
      <div className="container mt-5 mb-5">
        <h2 className="text-center text-uppercase mb-3">Thông tin user</h2>

        <form onSubmit={handleUpdate} className="row justify-content-center">
          <div className="col-md-6">
            <div className="bg-light p-4">
              <div className="mb-3">
                <label className="col-form-label">Fullname</label>
                <input
                  type="text"
                  id="fullname"
                  className="form-control"
                  defaultValue={user.name}
                  onChange={(e) => setName(e.target.value)}
                />
              </div>
              <div className="mb-3">
                <label className="col-form-label">Email</label>
                <input
                  type="text"
                  id="email"
                  className="form-control"
                  disabled
                  defaultValue={user.email}
                  onChange={(e) => setEmail(e.target.value)}
                />
              </div>
              <div className="mb-3">
                <label className="col-form-label">Phone</label>
                <input
                  type="text"
                  id="phone"
                  className="form-control"
                  defaultValue={user.phone}
                  onChange={(e) => setPhone(e.target.value)}
                />
              </div>
              <div className="mb-3">
                <label className="col-form-label">Address</label>
                <select
                  className="form-select"
                  id="address"
                  onChange={(e) => setAddress(e.target.value)}
                >
                  <option>{user.address}</option>
                  {provinces.map((province) => (
                    <option key={province.code} value={province.name}>
                      {province.name}
                    </option>
                  ))}
                </select>
              </div>
              <div className="mb-3">
                <label className="form-label">Avatar</label>
                <div className="avatar-preview mb-3 rounded">
                  <img
                    src={
                      `http://localhost:8080/${user?.avatar}` ??
                      "https://via.placeholder.com/200"
                    }
                    alt="avatar"
                    id="avatar-preview"
                    className="rounded"
                  />
                </div>

                <label className="btn btn-warning" htmlFor="input">
                  Upload ảnh
                </label>
                <input
                  type="file"
                  id="input"
                  className="d-none"
                  onChange={(e) => handleChangeAvatar(e)}
                />
              </div>
              <div className="mb-3">
                <label className="col-form-label">Password</label>
                <div className="">
                  <button
                    type="button"
                    className="btn btn-primary"
                    data-bs-toggle="modal"
                    data-bs-target="#modal-change-password"
                  >
                    Đổi Mật Khẩu
                  </button>
                  <button
                    className="btn btn-warning"
                    id="btn-forgot-password"
                    onClick={(e) => handleForgotPassword(e)}
                  >
                    Quên Mật Khẩu
                  </button>
                </div>
              </div>
            </div>
            <div className="text-center mt-3">
              <Link to="/">
                <button className="btn btn-secondary btn-back">Quay lại</button>
              </Link>
              <button className="btn btn-success" id="btn-save" type="submit">
                Cập nhật
              </button>
            </div>
          </div>
        </form>
        <div
          className="modal fade"
          id="modal-change-password"
          data-bs-backdrop="static"
          data-bs-keyboard="false"
          tabIndex="-1"
          aria-labelledby="staticBackdropLabel"
          aria-hidden="true"
          ref={modal}
        >
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title" id="staticBackdropLabel">
                  Đổi mật khẩu
                </h5>
                <button
                  type="button"
                  className="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <form onSubmit={handleUpdatePassword}>
                <div className="modal-body">
                  <div className="mb-3">
                    <label className="col-form-label">Mật khẩu cũ</label>
                    <input
                      type="text"
                      id="old-password"
                      className="form-control"
                      onChange={(e) => setOldPassword(e.target.value)}
                    />
                  </div>
                  <div className="mb-3">
                    <label className="col-form-label">Mật khẩu mới</label>
                    <input
                      type="text"
                      id="new-password"
                      className="form-control"
                      onChange={(e) => setNewPassword(e.target.value)}
                    />
                  </div>
                </div>
                <div className="modal-footer">
                  <button
                    type="button"
                    className="btn btn-secondary"
                    data-bs-dismiss="modal"
                  >
                    Đóng
                  </button>
                  <button
                    className="btn btn-primary"
                    id="btn-change-password"
                    type="submit"
                  >
                    Xác nhận
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default UserDetail;
