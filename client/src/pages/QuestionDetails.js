/* eslint-disable react/react-in-jsx-scope */
/* eslint-disable no-unused-vars */

import { useEffect, useState } from "react";
import { Button } from "../components/Button";
import { QuestionDetailStyle, Answer, Textarea } from "../tokens/Style";
import { DeleteAsk, GetQuestions, PostAnswer } from "../utils/API";
import Header from "../components/Header/Header";
// import { GetQuestions } from "../utils/API";

//질문 조회 페이지에서 헤더 값을 이용해 이쪽으로 링크 시킨다
// 여기서는 파라미터를 이용해 값을 받아온다
import { useNavigate } from "react-router-dom";

const QuestionDetail = () => {
  // const questionId = GetQuestions().then((res) => {
  //   return res.headers.location;
  // });//link할 컴포넌트에서 이걸 이용해 페이징

  const [answer, setAnswer] = useState();
  const [editor, setEditor] = useState(false);
  const navigate = useNavigate();

  // useEffect((e) => {
  //   if (e.keyCode === 8) {
  //     navigator(-1);
  //   }
  // });

  const onKeyDownHandler = (e) => {
    setAnswer(e.target.value);
  };
  localStorage.setItem("answer", answer);

  const postAnswer = () => {
    PostAnswer();
    navigate("/questions/1");
  };

  const edit = () => {
    setEditor(true);
    GetQuestions();
  };

  const remove = () => {
    DeleteAsk();
    navigate("/");
  };

  return (
    <div>
      <Header />
      <QuestionDetailStyle>
        <p>조회된 질문의 제목</p>
        <div className="question-info">
          <span>Ask 질문등록 날짜</span>
          <span>Modefied 수정날짜</span>
          <span>Viewed 클릭한 사람 수</span>
        </div>
        <div className={editor === true ? "editor" : "content"}>본문 내용</div>
        <div className="tagwrap">태그들</div>
        <span className="edit" onClick={edit}>
          edit
        </span>
        <span className="delete" onClick={remove}>
          delete
        </span>
        <Answer>
          <p className="answer-title">Your Answer</p>
          <Textarea onKeyDown={onKeyDownHandler}></Textarea>
          <Button
            className="postbtn-margin"
            text="Post Your Answer"
            onClick={postAnswer}
          />
        </Answer>
      </QuestionDetailStyle>
    </div>
  );
};

export default QuestionDetail;
