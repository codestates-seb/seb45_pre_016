/* eslint-disable react/react-in-jsx-scope */

import Sidebar from "../components/Sidebar/Sidebar";
import { Button } from "../components/Button";
import Header from "../components/Header/Header";
import { PostAnswer } from "../utils/API";
import { AllContainer, QuestionDetailStyle, Answer, Textarea } from "../tokens/Style";
import { useNavigate } from "react-router-dom";

const AnswerUpdate = () => {

    const navigator = useNavigate();

    const onKeyDownHandler = (e) => {
        localStorage.setItem("answer", e.target.value);
      };

      const postAnswer = () => {
        PostAnswer();
        navigator(-1)
      };

  return (
    <div className="post-question-all">
      <Header />

      <div
        style={{
          display: "flex",
          justifyContent: "center",
          marginLeft: "120px",
        }}
      >
        <Sidebar />
        <AllContainer>
          <QuestionDetailStyle>
            <Answer>
              <p className="answer-title">Your Answer</p>
              <Textarea
                onKeyDown={onKeyDownHandler}
                defaultValue={localStorage.getItem("answer")}
                //응답 데이터의 특정 질문 들어갈 곳
              ></Textarea>
              <Button
                className="postbtn-margin"
                type="submit"
                text="Post Your Answer"
                onClick={postAnswer}
              />
            </Answer>
          </QuestionDetailStyle>
        </AllContainer>
      </div>
    </div>
  );
};
export default AnswerUpdate;
