/* eslint-disable no-undef */
/* eslint-disable react/no-children-prop */
/* eslint-disable react/react-in-jsx-scope */
import { Button } from "../components/Button";
import { useState } from "react";
import ModalBasic from "../components/Modal";
import { InputForm } from "../components/InputForm";
import { AllContainer } from "../tokens/Style";
import { useNavigate } from "react-router-dom";
import { PostAsk } from "../utils/API";

const AskQuestions = () => {
  const [IsModalOpen, setModalOpen] = useState(false);
  const [post, setPost] = useState(false);
  const navigator = useNavigate();
  

  const postQuestion = () => {
    PostAsk();
    // GetQuestions();
    navigator('/');
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
        <InputForm onClick={changeToPostPage} post={post}/>
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

export default AskQuestions;
