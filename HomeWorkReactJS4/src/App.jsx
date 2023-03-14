import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import ToDoList from './component/ToDoList'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="App">
      <ToDoList/>
    </div>
  )
}

export default App
