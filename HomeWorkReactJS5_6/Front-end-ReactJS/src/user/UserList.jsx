import axios from "axios";
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";

function UserList() {
  const [users, setUsers] = useState([]);
  const [searchValue, setSearchValue] = useState("");
  useEffect(() => {
    const fetchData = async () => {
      const data = await fetch(`http://localhost:8080/api/v1/users`);
      const dataJSON = await data.json();
      setUsers(dataJSON);
    };

    fetchData();
  });
  const deleteById = async (id) => {
    try {
      const response = await axios.delete(
        `http://localhost:8080/api/v1/users/${id}`
      );
    } catch (error) {
      console.error(error);
    }
    alert("Xóa Thành Công User có ID: " + id);
  };
  const searchByValue = async (name) => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/v1/users/user?name=${name}`
      );
      console.log(response);
    } catch (error) {
      console.error(error);
    }
    // console.log("search value: "+name);
  };
  return (
    <div className="container mt-5 mb-5">
      <h2 className="text-center text-uppercase">Danh sách user</h2>

      <div className="row justify-content-center">
        <div className="col-md-10">
          <div className="d-flex justify-content-between align-items-center mt-5 mb-4">
            <Link to="./create">
              <button className="btn btn-warning">Tạo User</button>
            </Link>
            <input
              type="text"
              id="search"
              className="form-control w-50"
              placeholder="Tìm kiếm user"
              onChange={(e) => setSearchValue(e.target.value)}
              onKeyDown={(e) => {
                if (e.key === "Enter") {
                  searchByValue(searchValue);
                }
              }}
            />
          </div>

          <div className="bg-light p-4">
            <table className="table table-hover">
              <thead>
                <tr>
                  <th>STT</th>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Phone</th>
                  <th>Address</th>
                  <th></th>
                </tr>
              </thead>

              <tbody>
                {users.map((user) => (
                  <tr key={user.id}>
                    <td>{user.id}</td>
                    <td>{user.name}</td>
                    <td>{user.email}</td>
                    <td>{user.phone}</td>
                    <td>{user.address}</td>
                    <td>
                      <Link
                        to={{ pathname: `/users/${user.id}`, state: { user } }}
                      >
                        <button className="btn btn-success">
                          Xem chi tiết
                        </button>
                      </Link>
                      <button
                        className="btn btn-danger"
                        onClick={() => deleteById(user.id)}
                      >
                        Xóa
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>

            <p className="message d-none"></p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default UserList;
