import axios from "axios";
import React, { useEffect, useState } from "react";

function ToDoList() {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState("");
  useEffect(() => {
    axios
      .get("http://localhost:8081/api/todos")
      .then((response) => {
        setTasks(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);
  const handleSubmit = (event) => {
    event.preventDefault();
    axios
      .post(`http://localhost:8081/api/todos`, {
        title: newTask,
        status: false,
      })
      .then((response) => {
        // if (newTask.trim() !== "") {
        //   setTasks([...tasks, { title: newTask, status: false }]);
        //   setNewTask("");
        // }
        setTasks([...tasks,response.data])
      })
      .catch((error) => {
        console.log(error);
      });
  };
  const handleChange = (event) => {
    setNewTask(event.target.value);
  };
  const handleEditTask = (taskId,index) => {
    const newTitle = window.prompt("Nhập tiêu đề mới");
    console.log(tasks[index]);
    if (newTitle) {
      axios
        .put(`http://localhost:8081/api/todos`, {
          id: taskId,
          title: newTitle,
          status: tasks[index].status,
        })
        .then(() => {
          const newTasks = [...tasks];
          newTasks[index].title = newTitle;
          setTasks(newTasks);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };
  const handleDeleteTask = (index) => {
    axios
      .delete(`http://localhost:8081/api/todos/${index}`)
      .then(() => {
        const newTasks = [...tasks];
        newTasks.splice(index, 1);
        setTasks(newTasks);
      })
      .catch((error) => {
        console.log(error);
      });
  };
  const handleChangeStatus = (taskId,index) => {
    axios
      .put(`http://localhost:8081/api/todos`, {
        id: taskId,
        title: tasks[index].title,
        status: !tasks[index].status,
      })
      .then(() => {
        const newTasks = [...tasks];
        newTasks[index].status = !newTasks[index].status;
        setTasks(newTasks);
      })
      .catch((error) => {
        console.log(error);
      });
  };
  return (
    <div>
      <h2>ToDoList App</h2>
      {tasks.length > 0 ? (
        <ul>
          {tasks.map((task, index) => (
            <li key={index}>
              <input
                type="checkbox"
                checked={task.status}
                onChange={() => handleChangeStatus(parseInt(task.id),index)}
                value={newTask}
              />
              <span style={{ color: task.status ? "red" : "inherit" }}>
                {task.title}
              </span>
              <button onClick={() => handleEditTask(parseInt(task.id),index)}>Edit</button>
              <button onClick={() => handleDeleteTask(parseInt(task.id))}>Delete</button>
            </li>
          ))}
        </ul>
      ) : (
        <p>Danh sách công việc trống</p>
      )}

      <form onSubmit={handleSubmit}>
        <input type="text" value={newTask} onChange={handleChange} />
        <button type="submit">add</button>
      </form>
    </div>
  );
}

export default ToDoList;
