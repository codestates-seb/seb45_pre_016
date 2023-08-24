/* eslint-disable react/jsx-key */
/* eslint-disable react/prop-types */
/* eslint-disable react/react-in-jsx-scope */
import { Input, Textarea, Container, Title, Notice } from "../tokens/Style";
import { Button } from "./Button";
import ReviewDropdown from "./Dropdown";
import InputInfo from "../elements/InputInfo";
import { useState } from "react";
import { PostInput } from "./PostInput";

export const InputForm = ({ onClick, post }) => {
  const [currentIndex, setCurrentIndex] = useState(0);
  const [isFocused, setIsFocused] = useState(400);
  const [titleValue, setTitleValue] = useState();
  const [problemValue, setProBlemValue] = useState();
  const [bodyValue, setBodyValue] = useState();
  let infos = InputInfo;

  const onKeyDown = (e) => {
    if (e.target.id === "title") {

      setTitleValue(e.target.value);
      console.log(titleValue);
    } else if (e.target.id === "problem") {
      setProBlemValue(e.target.value);
    } else if (e.target.id === "tne") {
      setBodyValue(problemValue + "\n\n" + e.target.value);
      console.log(bodyValue)
    }
  };

  const onClickHandler = () => {
    if (currentIndex == infos.length - 1) {
      console.log("제출하기");
      onClick();
      return;
    }
    if (infos[currentIndex].isHidden == false) {
      infos[currentIndex].isHidden = true;
      window.scroll(0, isFocused);
      infos[currentIndex + 1].isHidden = false;
      setCurrentIndex(currentIndex + 1);
      setIsFocused(isFocused + 400);
    }
  };

  const inputList = infos.map((info) => {
    return (
      <div key={info.id} className="content">
        <div className={info.id}>
          <label htmlFor={info.id}>{info.title}</label>
          <div>
            <div>{info.description}</div>
          </div>
        </div>
        {(() => {
          switch (info.id) {
            case "title":
            case "tag":
              return (
                <Input
                  id={info.id}
                  placeholder={info.placeholder}
                  onKeyUp={onKeyDown}
                ></Input>
              );
            case "problem":
            case "tne":
              return <Textarea id={info.id} onKeyUp={onKeyDown} />;
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

  return (
    <>
      {post === false ? (
        <>
          <Title>Ask a public question</Title>
          <div className="flexbox">
            <Notice>
              <h2>Writing a good question</h2>
              <p>
                You’re ready to{" "}
                <a href="https://stackoverflow.com/help/how-to-ask">ask</a> a{" "}
                <a href="https://stackoverflow.com/help/on-topic">
                  programming-related question
                </a>{" "}
                and this form will help guide you through the process.
              </p>
              <p className="margin">
                Looking to ask a non-programming question? See{" "}
                <a href="https://stackexchange.com/sites#technology">
                  the topics here
                </a>{" "}
                to find a relevant site.
              </p>
              <div className="steps">
                <h5>steps</h5>
                <ul>
                  <li>Summarize your problem in a one-line title.</li>
                  <li>Describe your problem in more detail.</li>
                  <li>
                    Describe what you tried and what you expected to happen.
                  </li>
                  <li>
                    Add “tags” which help surface your question to members of
                    the community.
                  </li>
                  <li>Review your question and post it to the site.</li>
                </ul>
              </div>
            </Notice>
          </div>
          <Container>{inputList}</Container>
        </>
      ) : (
        <PostInput titleValue={titleValue} bodyValue={bodyValue} />
      )}
    </>
  );
};
