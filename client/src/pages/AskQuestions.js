/* eslint-disable no-undef */
/* eslint-disable react/no-children-prop */
/* eslint-disable react/react-in-jsx-scope */
import { Button } from "../components/Button";
import { useState } from "react";
import ModalBasic from "../components/Modal";
import { InputForm } from "../components/InputForm";
import { AllContainer } from "../tokens/Style";
import { Link, useNavigate } from "react-router-dom";
import { PostAsk } from "../utils/API";
import Header from "../components/Header/Header";
import Sidebar from "../components/Sidebar/Sidebar";

const AskQuestions = () => {
  const [IsModalOpen, setModalOpen] = useState(false);
  const [post, setPost] = useState(false);
  const userId = localStorage.getItem("userId");
  const [isAlert, setAlert] = useState(false);
  const navigator = useNavigate();

  const postQuestion = () => {
    if (userId) {
      PostAsk();
      navigator("/");
    } else {
      setAlert(true);
    }
    console.log("ok");

  };

  const changeToPostPage = () => {
    setPost(true);
  };

  const showModal = () => {
    setModalOpen(true);
  };

  return (
    <div key="" className="post-question-all">
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
          <InputForm onClick={changeToPostPage} post={post} />
          <div className="btn-flex">
            {post === true && (
              <Button
                className="postbtn-margin"
                text="Post your question"
                onClick={postQuestion}
              />
            )}
            {isAlert === true && (
              <div className="alert-back" onClick={() => setAlert(false)}>
                <div className={!userId ? "alert" : "hidden"}>
                  <p>로그인을 해야 질문 작성이 가능합니다.</p>
                  <Link to="/login">
                    <button onClick={() => setAlert(false)}>확인</button>
                  </Link>
                </div>
              </div>
            )}

            <Button text="Discard draft" onClink={showModal} />
          </div>
          {IsModalOpen && <ModalBasic setModalOpen={setModalOpen} />}
        </AllContainer>
      </div>
    </div>
  );
};

export default AskQuestions;
