import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router";
import "./login.css";

export default function Login() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    const user = { email, password };

    setEmail("");
    setPassword("");

    fetch("http://localhost:8080/user/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(user),
    })
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error("Authentication failed");
        }
      })
      .then((user) => {
        console.log(user);
        if (user === null) {
          navigate("/login");
        } else {
          navigate("/", { user });
        }
      })
      .catch((error) => console.error(error));
  };
  return (
    <form className="frame184l" onSubmit={handleSubmit}>
      <div className="frame180l">
        <div className="frame178l">
          <h5 className="loginTitle">LOGIN</h5>
        </div>

        <div className="frame179l">
          <div className="frame270">
            <div className="inputEmailL">
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
            <div className="frame269">
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
              <div className="frame181">
                <label>
                  <input type="checkbox" />
                  Remember me
                </label>
              </div>
            </div>
          </div>

          <div className="frame271">
            <button type="submit" className="ctaLog">
              <span className="logButton">LOGIN</span>
            </button>
            <div className="frame185l">
              <div className="ctaGmailL">
                <span className="loginWithGmail">Signup with Gmail</span>
                <span className="imageGmail"></span>
              </div>

              <div className="ctaFbL">
                <span className="loginWithFb">Signup with Facebook</span>
                <span className="image6Fbl"></span>
              </div>
            </div>
          </div>
        </div>

        <span className="forgotPassword">Forgot password?</span>
      </div>
    </form>
  );
}
