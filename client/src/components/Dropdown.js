/* eslint-disable no-unused-vars */
/* eslint-disable react/jsx-key */
/* eslint-disable react/react-in-jsx-scope */

import React, { useState } from "react";
import { DropdownStyle } from "../tokens/Style";
import down from "../utils/downarrow.png";
import up from "../utils/uparrow.png";

const ReviewDropdown = () => {
  const options = ["No duplicate questions found."];
  const [isClicked, setIsClicked] = useState(true);
  const [isChosen, setIsChosen] = useState();
  const [isSelected, setIsSelected] = useState(
    "Do any of these posts answer your question?"
  );

  const selected = (e) => {
    if (!isChosen) {
      setIsChosen(true);
      setIsSelected(e.target.innerText);
    }
  };

  const viewOption = () => {
    if (isClicked === false) {
      setIsClicked(true);
    } else {
      setIsClicked(false);
      setIsChosen(false);
    }
  };

  return (
    <div className="dropdown">
      <DropdownStyle onClick={viewOption}>
        <div className="dropdown-container">
          <div className="controler-flex">
            <div className="flex1">{isSelected}</div>
            <div className="arrow">
              <img src={isClicked ? up : down} />
            </div>
          </div>
          {isClicked &&
            (options.length > 0 ? (
              options.map((option) => {
                return (
                  <div
                    key={option}
                    onClick={selected}
                    className="dropdown-option"
                  >
                    {option}
                  </div>
                );
              })
            ) : (
              <div key="not-found" className="dropdown-option">
                No duplicate questions found
              </div>
            ))}
        </div>
      </DropdownStyle>
    </div>
  );
};

export default ReviewDropdown;
