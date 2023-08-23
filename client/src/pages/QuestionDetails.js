/* eslint-disable react/prop-types */
/* eslint-disable react/react-in-jsx-scope */
/* eslint-disable no-unused-vars */

import { useEffect, useState } from "react";
import { Button } from "../components/Button";
import { QuestionDetailStyle, Answer, Textarea } from "../tokens/Style";
import { DeleteAsk, GetQuestions, PatchPost, PostAnswer } from "../utils/API";
import Header from "../components/Header/Header";
import Sidebar from "../components/Sidebar/Sidebar";
import { AllContainer } from "../tokens/Style";
import axios from "axios";

//질문 조회 페이지에서 헤더 값을 이용해 이쪽으로 링크 시킨다
// 여기서는 파라미터를 이용해 값을 받아온다
import { Link, useNavigate } from "react-router-dom";

const QuestionDetail = ({param}) => {
  const [editor, setEditor] = useState(false);
  const navigate = useNavigate();




  // useEffect(()=> {
  //   axios.get(`http://43.201.157.40:8080/questions/${param}`).then((res)=>res.data).catch((e)=>console.log(e))
  // })


  const tags = ["React", "Java", "Http Request", "fullstack", "team project"];


  const onKeyDownHandler = (e) => {
    localStorage.setItem("answer", e.target.value);
  };

  const postAnswer = () => {
    PostAnswer();
    location.reload();
  };

  const edit = () => {
    setEditor(true);
    GetQuestions();
  };

  const deleteTag = (e) => {
    e.currentTarget.className = "hidden";
    // 삭제요청
  };

  const update = () => {
    PatchPost();
    location.reload();
  };
  const remove = () => {
    DeleteAsk();
    navigate("/");
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
            {console.log(param)}
            <p>title of clicked questions</p>
            <div className="question-info">
              <span>Ask 질문등록 날짜</span>
              <span>Modefied 수정날짜</span>
              <span>Viewed 클릭한 사람 수</span>
            </div>
            {editor === false ? (
              <div className="content">
                When I run the above code on write case (case 1) fgets() is not
                taking any inputs from the user via keyboard. I need the user
                contents which the user types on the keyboard should be stored
                in a file and to be read when required.
              </div>
            ) : (
              <Textarea defaultValue="데이터값" className="editor"></Textarea>
            )}
            <div className="tagwrap">
              {tags.map((el, index) => {
                return (
                  <div key={index} onClick={deleteTag} className="tag">
                    {el} <button className="x">x</button>
                  </div>
                );
              })}
            </div>
            {editor === false ? (
              <span className="edit" onClick={edit}>
                edit
              </span>
            ) : (
              <span className="update" onClick={update}>
                update
              </span>
            )}

            <span className="delete" onClick={remove}>
              delete
            </span>
            <Answer>
              <p className="answer-title">  Answer</p>
              <div className="content">
                this is a answer for this question. How do you think about this?
              </div>
              <Link to="./answer/update">
                <div className="edit">edit</div>
              </Link>
            </Answer>
            <Answer>
              <p className="answer-title">Your Answer</p>
              <Textarea onKeyDown={onKeyDownHandler}></Textarea>
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

export default QuestionDetail;
