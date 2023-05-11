import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./components/home";
import Register from "./components/register";
import Login from "./components/login";
import Header from "./components/header";
import Footer from "./components/footer";
import HeaderPage from "./components/headerPage";
import Cookies from "js-cookie";
import MyAccount from "./components/myAccount";

function App(props) {
  const [user, setUser] = useState("");
  const [loggedIn, setLoggedIn] = useState(false);

  useEffect(() => {
    const storedUser = Cookies.get("user");
    if (storedUser) {
      setUser(JSON.parse(storedUser));
      setLoggedIn(true);
    }
  }, []);

  const SetCookie = (userData) => {
    Cookies.set("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9", {
      expires: 7,
    });
    Cookies.set("user", JSON.stringify(userData), { expires: 7 });
    setUser(userData);
    setLoggedIn(true);
  };

  const GetCookie = () => {
    alert(Cookies.get("token"));
  };

  const RemoveCookie = () => {
    Cookies.remove("token");
    Cookies.remove("user");
    setUser("");
    setLoggedIn(false);
  };

  return (
    <div className="page">
      <Router>
        {loggedIn ? (
          <HeaderPage removeCookie={RemoveCookie} user={user} />
        ) : (
          <Header />
        )}

        <Routes>
          {loggedIn ? (
            <Route path="/" element={<Home />} />
          ) : (
            <Route
              path="/login"
              element={<Login setUser={setUser} setCookie={SetCookie} />}
            />
          )}

          <Route path="/register" element={<Register />} />
          <Route path="/myAccount" element={<MyAccount />} />
        </Routes>

        <Footer />
      </Router>
    </div>
  );
}

export default App;
