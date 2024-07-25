import React, { useEffect, useState } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import { Route, Routes, useNavigate } from 'react-router-dom';
import Login from './components/Login/Login';
import POS from './Pages/POS/POS';
import AddCategory from './Pages/AddCategory/AddCategory';
import AddProduct from './Pages/AddProduct/AddProduct';
import NavbarHeader from './components/NavbarHeader/NavbarHeader';



const App = () => {

  const [isLoggedin, setIsLoggedIn] = useState(false);
  const navigate = useNavigate();
  const [username, setUsername] = useState('');

  const url = 'http://localhost:3000';
  useEffect(() => {
    const loggedIn = localStorage.getItem('isLoggedIn');
    const storedUsername = localStorage.getItem('username');
    if (loggedIn) {
      setIsLoggedIn(true);
      if (storedUsername) {
        setUsername(storedUsername);
      }
    } else {
      navigate('/login')
    }
  }, [navigate]);

  const handleLogin = (username) => {
    setIsLoggedIn(true);
    localStorage.setItem('isLoggedIn', 'true');
    localStorage.setItem('username', username);
    navigate('/pos');
  };
  const handleLogout = () => {
    setIsLoggedIn(false);
    localStorage.removeItem('isLoggedIn');
    navigate('/login');
  };
  return (
    <div>
     
      {isLoggedin ? (
        <>
         <NavbarHeader onLogout={handleLogout} />
          <Routes>
            <Route path="/pos" element={<POS />} />
            <Route path="/addCategory" element={<AddCategory />} />
            <Route path="/addProduct" element={<AddProduct />} />

          </Routes>
        </>

      ):(
        <Login setIsLoggedIn = {handleLogin} navigate = {navigate} />
      )}

    </div>
  );
}

export default App;