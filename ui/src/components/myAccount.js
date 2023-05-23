import React from "react";
import "./myAccount.css";
import AddIcon from "@mui/icons-material/Add";
import SettingsOutlinedIcon from "@mui/icons-material/SettingsOutlined";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import MonetizationOnOutlinedIcon from "@mui/icons-material/MonetizationOnOutlined";
import ListOutlinedIcon from "@mui/icons-material/ListOutlined";
import PersonIcon from "@mui/icons-material/Person";

export default function MyAccount() {
  function handleAddItem() {}

  return (
    <div className="frame450">
      <div className="frame450m">
        <div className="ctaButton" onClick={handleAddItem}>
          <AddIcon className="vectorPlus" />
          <span className="addItemText">ADD ITEM</span>
        </div>
        <div className="tabSettings">
          <SettingsOutlinedIcon className="tabIcon" />
          <span className="settingsText">Settings</span>
        </div>
        <div className="tabWishList">
          <FavoriteBorderIcon className="tabIcon" />
          <span className="wishlistText">Wishlist</span>
        </div>
        <div className="tabBids">
          <MonetizationOnOutlinedIcon className="tabIcon" />
          <span className="bidsText">Bids</span>
        </div>
        <div className="tabSeller">
          <ListOutlinedIcon className="tabIcon" />
          <span className="sellerText">Seller</span>
        </div>
        <div className="tabProfile">
          <PersonIcon className="tabIcon" />
          <span className="profileText">Profile</span>
        </div>
      </div>

      <div className="frame449">
        <div className="frame447">
          <div className="frame441">
            <div className="frame380">
              <div className="frame379">
                <div className="frame378">
                  <div className="frame377">
                    <div className="frame376">
                      <span className="pInformationText">
                        Personal Information
                      </span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className="frame440">
              <div className="frame423">
                <div className="group162">
                  <div className="image8"></div>
                </div>
                <div className="ctaChangePhoto">
                  <span className="goText">Change Photo</span>
                </div>
              </div>
              <div className="frame415">
                <div className="inputF">
                  <span className="fnText">First Name</span>
                  <input className="frame239" type="text" placeholder="John" />
                </div>
                <div className="inputF">
                  <span className="fnText">Last Name</span>
                  <input className="frame239" type="text" placeholder="Doe" />
                </div>
                <div className="inputF">
                  <span className="fnText">Email Address</span>
                  <input
                    className="frame239"
                    type="email"
                    placeholder="user@domain.com"
                  />
                </div>
                <div className="frame438">
                  <div className="dateInputDD">
                    <span className="fnText">Date of Birth</span>
                    <input
                      className="frame239d"
                      placeholder="DD"
                      type="number"
                      min="1"
                      max="31"
                    />
                  </div>
                  <div className="dateInputMM">
                    <input
                      className="frame239d"
                      placeholder="MM"
                      type="number"
                      min="1"
                      max="12"
                    />
                  </div>
                  <div className="dateInputYYYY">
                    <input
                      className="frame239d"
                      placeholder="YYYY"
                      type="number"
                      min="1800"
                      max="2500"
                    />
                  </div>
                </div>
                <div className="inputPhoneNumber">
                  <span className="phoneNumberText">Phone Number</span>
                  <div className="frame439">
                    <input
                      className="frame239Phone"
                      placeholder="+32534231564"
                    />
                    <div className="frame240">
                      <span className="notVerified">Not verified</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="ctaSaveInfo">
          <div className="saveInfoButton">SAVE INFO</div>
        </div>
      </div>
    </div>
  );
}
