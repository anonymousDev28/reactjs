import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import BoxContainer from './component/BoxContainer'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="App">
      <BoxContainer/>
    </div>
  )
}

export default App
