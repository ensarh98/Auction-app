import React from "react";
import { Routes, Route, Link, NavLink } from "react-router-dom";

export default function Home() {
  return (
    <div>
      <ul>
        <li>
          <NavLink to="/" exact activeStyle={{ color: "red" }}>
            Home
          </NavLink>
        </li>
        <li>
          <NavLink to="/login" exact activeStyle={{ color: "green" }}>
            Login
          </NavLink>
        </li>
        <li>
          <NavLink to="/register" exact activeStyle={{ color: "magenta" }}>
            Register
          </NavLink>
        </li>
      </ul>
    </div>
  );
}
