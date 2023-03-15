import { useState } from 'react'
import { Route, Routes } from 'react-router-dom'
import './App.css'
import NotFound from './pages/NotFound'
import UserCreate from './user/UserCreate'
import UserDetail from './user/UserDetail'
import UserList from './user/UserList'

function App() {
  const [count, setCount] = useState(0)
  return (
    <>
    <Routes>
      <Route path="/">
        <Route index element={<UserList/>}/>
        <Route path="create" element={<UserCreate/>}/>
        <Route path="users/:userId" element={<UserDetail/>}/>
      </Route>
      <Route path="*" element={<NotFound/>}/>
    </Routes>
    </>
  )
}

export default App
