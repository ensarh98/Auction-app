import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./components/home";
import Register from "./components/register";
import Login from "./components/login";
import Header from "./components/header";
import Footer from "./components/footer";
import HeaderPage from "./components/headerPage";
import MyAccount from "./components/myAccount";
import SetPrices from "./components/setPrices";
import AboutProduct from "./components/aboutProduct";

function App() {
  const [loggedIn, setLoggedIn] = useState(() => {
    const storedLoggedIn = localStorage.getItem("loggedIn");
    return storedLoggedIn ? JSON.parse(storedLoggedIn) : false;
  });

  const [user, setUser] = useState(() => {
    const storedUser = localStorage.getItem("user");
    return storedUser ? JSON.parse(storedUser) : null;
  });

  useEffect(() => {
    localStorage.setItem("user", JSON.stringify(user));
  }, [user]);

  useEffect(() => {
    localStorage.setItem("loggedIn", JSON.stringify(loggedIn));
  }, [loggedIn]);

  function handleClickLogin() {
    setLoggedIn(true);
  }

  function handleClickRegister() {
    setLoggedIn(false);
  }

  return (
    <div className="page">
      <Router>
        {user ? (
          <HeaderPage user={user} setLoggedIn={setLoggedIn} setUser={setUser} />
        ) : (
          <Header
            onClickLogin={handleClickLogin}
            onClickRegister={handleClickRegister}
          />
        )}

        <Routes>
          {user ? (
            <Route path="/" element={<SetPrices />}></Route>
          ) : loggedIn ? (
            <Route
              path="/login"
              element={<Login setUser={setUser} setLoggedIn={setLoggedIn} />}
            />
          ) : (
            <Route
              path="/register"
              element={<Register setLoggedIn={setLoggedIn} />}
            />
          )}

          <Route path="/account" element={<MyAccount />} />
        </Routes>

        <Footer />
      </Router>
    </div>
  );
}

export default App;
