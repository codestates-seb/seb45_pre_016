/* eslint-disable react/jsx-key */
/* eslint-disable react/prop-types */
/* eslint-disable react/react-in-jsx-scope */
import { Input, Textarea } from "../styles/Style";
import { Button } from "./Button";
import ReviewDropdown from "./Dropdown";
import InputInfo from "../elements/InputInfo";
import { useState } from "react";

export const InputForm = () => {
  const [currentIndex, setCurrentIndex] = useState(0);
  let infos = InputInfo;

  const onClickHandler = () => {
    if (currentIndex == infos.length - 1) {
      console.log("제출하기");
      return
    }
    if (infos[currentIndex].isHidden == false) {
      infos[currentIndex].isHidden = true;
      infos[currentIndex + 1].isHidden = false;
      setCurrentIndex(currentIndex + 1);
    }
  };

  return infos.map((info) => {
    return (
      <div key={info.id} className="content">
        <div className={info.id}>
          <label>{info.title}</label>
          <div>
            <div>{info.description}</div>
          </div>
        </div>
        {(() => {
          switch (info.id) {
            case "title":
            case "tag":
              return (
                <Input placeholder={info.placeholder} />
              );
            case "problem":
            case "tne":
              return <Textarea />;
            case "review":
              return <ReviewDropdown />;
          }
        })()}
        <div>
          {info.isHidden === true ? null : (
            <Button
              onClick={onClickHandler}
              text={info.id === "review" ? "Review your question" : "Next"}
            />
          )}
        </div>
      </div>
    );
  });
};

