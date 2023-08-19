/* eslint-disable react/react-in-jsx-scope */
import { Button } from "../components/Button";
import { useState } from "react";
import ModalBasic from "../components/Modal";
import { InputForm } from "../components/InputForm";
import {
  Notice,
  Title,
  FinalReview,
  AllContainer,
  Container,
} from "../styles/Style";

export const AskQuestions = () => {
  const [IsModalOpen, setModalOpen] = useState(false);
 


  const showModal = () => {
    setModalOpen(true);
  };



  return (
    <div key="" className="post-question">
      <AllContainer>
        <Title>Ask a public question</Title>
        <FinalReview>
          Please do a final review of your question and then post.
        </FinalReview>
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
                  Add “tags” which help surface your question to members of the
                  community.
                </li>
                <li>Review your question and post it to the site.</li>
              </ul>
            </div>
          </Notice>
        </div>

        <Container>
          <InputForm />
        </Container>

        <div>
          <button className="hidden">Post your question</button>
          <Button
            className="hidden"
            value="discard"
            text="Discard draft"
            onClick={showModal}
          >
          </Button>
        </div>
        {IsModalOpen && <ModalBasic setModalOpen={setModalOpen} />}
      </AllContainer>
    </div>
  );
};
