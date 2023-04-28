import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./components/home";
import Register from "./components/register";
import Login from "./components/login";
import Header from "./components/header";
import Footer from "./components/footer";

function App() {
  return (
    <div className="page">
      <Router>
        <Header />

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
        </Routes>

        <Footer />
      </Router>
    </div>
  );
}

export default App;
