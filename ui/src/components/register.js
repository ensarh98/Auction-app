import React, { useState, useEffect } from "react";
import "./register.css";

export default function Register() {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [users, setUsers] = useState([]);

  const handleSubmit = (e) => {
    e.preventDefault();

    const user = { firstName, lastName, email, password };

    setFirstName("");
    setLastName("");
    setEmail("");
    setPassword("");

    fetch("http://localhost:8080/users", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(user),
    }).then(() => {
      console.log("New user added");
    });
  };

  return (
    <div className="register_page">
      <header className="header">
        <div className="frame_369"></div>
      </header>
      <div className="group161"></div>
      <div className="frame185">
        <div className="frame176">
          <div className="frame108">
            <span className="auction-app-logo"></span>
          </div>
        </div>
      </div>

      <form className="frame184" onSubmit={handleSubmit}>
        <div className="frame180">
          <div className="frame178">
            <h5 className="registerTitle">REGISTER</h5>
          </div>

          <div className="frame179">
            <div className="frame268">
              <div className="inputFirstName">
                <span className="labelText">First name</span>
                <input
                  type="text"
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                  className="frame239"
                  placeholder="John"
                  required
                />
              </div>
              <div className="inputLastName">
                <span className="labelText">Last name</span>
                <input
                  type="text"
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
                  className="frame239"
                  placeholder="Doe"
                  required
                />
              </div>
              <div className="inputEmail">
                <span className="labelText">Enter Email</span>
                <input
                  type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  className="frame239"
                  placeholder="user@domain.com"
                  required
                />
              </div>
              <div className="inputPassword">
                <span className="labelText">Password</span>
                <input
                  type="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="frame239"
                  placeholder="********"
                  required
                />
              </div>
            </div>

            <button type="submit" className="ctaReg">
              <span className="regButton">REGISTER</span>
            </button>

            <div className="frame185">
              <div className="cta">
                <span className="image6"></span>
                <span className="loginWithGmail">Signup with Gmail</span>
              </div>
            </div>
            <div className="ctaFb">
              <span className="image6Fb"></span>
              <span className="loginWithFb">Signup with Facebook</span>
            </div>
          </div>

          <div className="frame186">
            <span className="alreadyText">Already have an account?</span>
            <span className="loginText">Login</span>
          </div>
        </div>
      </form>

      <footer>
        <div className="frame34">
          <span className="getInTouch">GET IN TOUCH</span>
          <div className="frame32">
            <div className="frame35">
              <span className="callUsAt">Call Us at</span>
              <span className="phoneNum">+123 797-567-2535</span>
            </div>
            <span className="support">support@auction.com</span>
            <div className="frame36">
              <div className="frame369"></div>
            </div>
          </div>
        </div>
        <div className="frame33">
          <span className="auction">AUCTION</span>
          <div className="frame32">
            <span className="aboutUs">About Us</span>
            <span className="termsAndCond">Terms and Conditions</span>
            <span className="privacyAndPolicy">Privacy and Policy</span>
          </div>
        </div>
      </footer>
    </div>
  );
}
