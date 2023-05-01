import React, { useState } from "react";
import "./headerPage.css";
import FacebookIcon from "@mui/icons-material/Facebook";
import InstagramIcon from "@mui/icons-material/Instagram";
import TwitterIcon from "@mui/icons-material/Twitter";
import { Link } from "react-router-dom";

export default function HeaderPage(props) {
  const [showMenu, setShowMenu] = useState(false);

  const handleMenuClick = () => {
    setShowMenu(!showMenu);
  };

  const handleLogout = () => {
    props.removeCookie();
  };
  return (
    <div className="frame170hp">
      <div className="frame168hp">
        <header className="header">
          <div className="frame_369">
            <span className="akar">
              <FacebookIcon />
            </span>
            <span className="akar">
              <InstagramIcon />
            </span>
            <span className="akar">
              <TwitterIcon />
            </span>
          </div>
          <div className="frame1">
            <Link to="" className="createText" onClick={handleMenuClick}>
              {"Hi, " + props.user.firstName + " " + props.user.lastName}
            </Link>
            {showMenu && (
              <div className="frame1">
                <div className="logoutText" onClick={handleLogout}>
                  Logout
                </div>
              </div>
            )}
          </div>
        </header>
        <div className="frame108hp">
          <span className="auction-app-logo-hp"></span>
          <div className="frame107hp">
            <div style={{ position: "relative" }}>
              <input
                type="search"
                className="searchBar"
                placeholder="Try enter: Shoes"
              />
              <div
                style={{
                  position: "absolute",
                  top: "50%",
                  transform: "translateY(-50%)",
                  right: "10px",
                }}
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="24"
                  height="24"
                  viewBox="0 0 24 24"
                >
                  <path
                    fill="currentColor"
                    d="M9.5 3A6.5 6.5 0 0 1 16 9.5c0 1.61-.59 3.09-1.56 4.23l.27.27h.79l5 5l-1.5 1.5l-5-5v-.79l-.27-.27A6.516 6.516 0 0 1 9.5 16A6.5 6.5 0 0 1 3 9.5A6.5 6.5 0 0 1 9.5 3m0 2C7 5 5 7 5 9.5S7 14 9.5 14S14 12 14 9.5S12 5 9.5 5Z"
                  />
                </svg>
              </div>
            </div>
            <div className="frame16">
              <span className="homeText">HOME</span>
              <span className="shopText">SHOP</span>
              <span className="myaccountText">MY ACCOUNT</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
