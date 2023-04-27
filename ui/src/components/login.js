import React, { useState, useEffect } from "react";
import "./login.css";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    const user = { email, password };

    setEmail("");
    setPassword("");

    fetch("", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(user),
    }).then(() => {
      console.log("New user added");
    });
  };
  return (
    <div className="login_page">
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
            <h5 className="loginTitle">LOGIN</h5>
          </div>

          <div className="frame179">
            <div className="frame270">
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

            <div className="frame271">
              <button type="submit" className="ctaLog">
                <span className="logButton">LOGIN</span>
              </button>
              <div className="frame185">
                <div className="ctaGmail">
                  <span className="image6"></span>
                  <span className="loginWithGmail">Signup with Gmail</span>
                </div>
              </div>
              <div className="ctaFb">
                <span className="image6Fb"></span>
                <span className="loginWithFb">Signup with Facebook</span>
              </div>
            </div>
          </div>

          <span className="forgotPassword">Forgot password?</span>
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
