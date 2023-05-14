import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./components/home";
import Register from "./components/register";
import Login from "./components/login";
import Header from "./components/header";
import Footer from "./components/footer";
import HeaderPage from "./components/headerPage";

function App(props) {
  const [user, setUser] = useState("");
  const [loggedIn, setLoggedIn] = useState(false);

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
          <HeaderPage user={user} />
        ) : (
          <Header
            onClickLogin={handleClickLogin}
            onClickRegister={handleClickRegister}
          />
        )}

        <Routes>
          {user ? (
            <Route path="/" element={<Home />}></Route>
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
        </Routes>

        <Footer />
      </Router>
    </div>
  );
}

export default App;
