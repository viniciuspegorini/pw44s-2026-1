import './App.css'
import { RegisterPage } from '@/pages/register'
import { HomePage } from '@/pages/home'
import { LoginPage } from './pages/login'

function App() {
  
  return (
    <>
      <HomePage/>
      <RegisterPage />
      <LoginPage />
    </>
  )
}

export default App