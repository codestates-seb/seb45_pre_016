/* eslint-disable no-undef */
/* eslint-disable react/no-children-prop */
/* eslint-disable react/react-in-jsx-scope */
import { Button } from "../components/Button";
import { useState } from "react";
import ModalBasic from "../components/Modal";
import { InputForm } from "../components/InputForm";

import { AllContainer } from "../styles/Style";

export const AskQuestions = () => {
  const [IsModalOpen, setModalOpen] = useState(false);
  const [post, setPost] = useState(false);
  const [title, setTitle] = useState("d");
  
  

  const postQuestion = () => {
    localStorage.setItem('title', title);
  };

  const changeToPostPage = () => {
    setPost(true);
  };

  const showModal = () => {
    setModalOpen(true);
  };

  return (
    <div key="" className="post-question-all">
      <AllContainer>
        <InputForm onClick={changeToPostPage} post={post} setTitle={setTitle}/>
        <div className="btn-flex">
          {post === true && (
            <Button
              className="postbtn-margin"
              text="Post your question"
              onClick={postQuestion}
            />
          )}

          <Button text="Discard draft" onClick={showModal} />
        </div>
        {IsModalOpen && <ModalBasic setModalOpen={setModalOpen} />}
      </AllContainer>
    </div>
  );
};
