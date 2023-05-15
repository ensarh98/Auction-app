import React from "react";
import "./aboutProduct.css";

export default function AboutProduct() {
  return (
    <div>
      <div className="group160sp">
        <span className="sellerTextsp">BECOME SELLER</span>
      </div>
      <div className="frame204sp">
        <span className="myAccountText">MY ACCOUNT</span>
        <span className="slashText">/</span>
        <span className="becomeSellerText">BECOME SELLER</span>
      </div>

      <div className="frame434sp">
        <div className="reactangle66">
          <div className="frame178sp">
            <span className="addItemTextsp">
              DETAIL INFORMATION ABOUT PRODUCT
            </span>
          </div>
        </div>
      </div>

      <div className="frame179sp">
        <div className="frame435sp">
          <div className="inputItemsp">
            <span className="whatDoYouSellText">What do you sell?</span>
            <input className="frame230sp" type="text" />
            <div className="wordsCharactersText">2-5 words (60 characters)</div>
          </div>
          <div className="frame227sp">
            <div className="dropdownCategory">
              <select className="frame40sp" name="category" id="category">
                <option disabled selected value="category">
                  Select Category
                </option>
                <option value="clothes">Clothes</option>
              </select>
            </div>
            {/* <div className="dropdownSubategory">
              <select className="frame40sp" name="Subcategory" id="subcategory">
                <option disabled selected value="subcategory">
                  Select Subcategory
                </option>
                <option value="shoes">Shoes</option>
              </select>
            </div> */}
          </div>

          {/* <div className="frame229sp">
            <div className="textArea">
              <span className="descriptionTextsp">Description</span>
              <textarea
                id="description"
                name="description"
                className="frame228sp"
              ></textarea>
              <div className="wordsCharactersDescText">
                2-5 words (60 characters)
              </div>
            </div>
          </div> */}
          <label className="inputPhoto" htmlFor="photo">
            <div className="frame233sp">
              <div className="frame231sp">
                <label className="frame232sp">
                  <span className="uploadPhotosText">Upload Photos</span>
                  <span className="orJustDragAndDrop">
                    or just drag and drop
                  </span>
                </label>
                <span className="AddAtLeast3Photos">
                  + Add at least 3 photos
                </span>
              </div>
              <input
                type="file"
                id="photo"
                name="photo"
                className="uploadPhotosInput"
              />
            </div>
          </label>
          <div className="frame230ab">
            <div className="nextButtonsap">
              <span className="nextTextab">NEXT</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
