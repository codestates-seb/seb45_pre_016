/* eslint-disable react/react-in-jsx-scope */

import { Button } from "../components/Button";
import { useRef, useEffect, useState } from "react";
import { ReviewDropdown } from "../components/Dropdown";
import ModalBasic from "../components/Modal";
import {
  Notice,
  Title,
  FinalReview,
  AllContainer,
  Container,
  Input,
  Textarea,
} from "../styles/Style";

export const AskQuestions = () => {
  const [IsModalOpen, setModalOpen] = useState(false);
  const titleRef = useRef();
  const problemRef = useRef();
  const tneRef = useRef();
  const tagRef = useRef();
  const reviewRef = useRef();
  const [isFocused, setIsFocused] = useState(false);
  const [isTitleHidden, setIsTitleHidden] = useState("");
  const [isProblemHidden, setIsProblemHidden] = useState("hidden");
  const [isTneHidden, setIsTneHidden] = useState("hidden");
  const [isTagHidden, setIsTagHidden] = useState("hidden");
  const [isReviewHidden, setIsReviewHidden] = useState("hidden");
  const [isDiscardHidden, setIsDiscardHidden] = useState("hidden");
  const [isProblemDisabled, setIsProblemDisabled] = useState(true);
  const [isTneDisabled, setIsTneDisabled] = useState(true);
  const [isTagDisabled, setIsTagDisabled] = useState(true);
  const [isReviewDisabled, setIsReviewDisabled] = useState(true);
  const [isChecked, setIsChecked] = useState(false);

  const checking = () => {
    if (!isChecked) {
      setIsChecked(true);
    } else {
      setIsChecked(false);
    }
  };

  const showModal = () => {
    setModalOpen(true);
  };

  const scrollHandler = (e) => {
    if (isFocused) {
      setIsFocused(true);
    } else if (!isFocused && e.target.htmlFor === "title") {
      window.scroll(0, 0);
    } else if (!isFocused && e.target.htmlFor === "problem") {
      window.scroll(0, 430);
    } else if (!isFocused && e.target.htmlFor === "tne") {
      window.scroll(0, 850);
    } else if (!isFocused && e.target.htmlFor === "tag") {
      window.scroll(0, 1200);
    } else if (!isFocused && e.target.htmlFor === "review") {
      window.scroll(0, 1210);
      setIsReviewDisabled(false);
    }
  };

  const onClickHandler = (e) => {
    if (e.target.value === "title") {
      problemRef.current.focus();
      window.scroll(0, 430);
      setIsTitleHidden("hidden");
      setIsProblemHidden("");
      setIsProblemDisabled(false);
      console.log("connected")
    } else if (e.target.value === "problem") {
      tneRef.current.focus();
      window.scroll(0, 850);
      setIsProblemHidden("hidden");
      setIsTneDisabled(false);
      setIsTneHidden("");
    } else if (e.target.value === "tne") {
      tagRef.current.focus();
      window.scroll(0, 1200);
      setIsTneHidden("hidden");
      setIsTagDisabled(false);
      setIsTagHidden("");
    } else if (e.target.value === "tag") {
      reviewRef.current.focus();
      window.scroll(0, 1210);
      setIsTagHidden("hidden");
      setIsReviewHidden("");
      setIsReviewDisabled(false);
    } else if (e.target.value === "review" && e.target.disabled === false) {
      setTimeout(() => {
        setIsDiscardHidden("discard");
        setTimeout(() => {
          window.scroll(0, 2000);
        }, 10);
      }, 700);
    }
  };

  useEffect(() => {
    titleRef.current.focus();
  }, []);

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
        <div className="content">
        <div className="title">
          <label htmlFor="title" onClick={scrollHandler}>
            Title
          </label>
          <div className="flex-box-input-notice">
            <div className="input-specific">
              Be specific and imagine you’re asking a question to another
              person.
            </div>
            <div className="alert">alert</div>
          </div>
        </div>
        <Input
          ref={titleRef}
          id="title"
          placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
        ></Input>
        <div>
          <Button
            className={isTitleHidden}
            onClick={onClickHandler}
            value="title"
            text="Next"
          />
        </div>
      </div>

          <div className="content">
            <div className="tipbox"></div>
            <label
              onClick={(e) =>
                e.currentTarget.isProblemDisabled === true ? "" : scrollHandler
              }
              htmlFor="problem"
            >
              What are the details of your problem?
            </label>
            <div className="flex-box-input-notice">
              <div className="input-specific">
                Introduce the problem and expand on what you put in the title.
                Minimum 20 characters.
              </div>
            </div>
            <Textarea
              id="problem"
              ref={problemRef}
              disabled={isProblemDisabled}
            />
            <div>
              <Button
                onClick={onClickHandler}
                className={isProblemHidden}
                value="problem"
                text="Next"
              />
            </div>
          </div>

          <div className="tipbox"></div>
          <div className="content">
            <label
              onClick={(e) =>
                e.currentTarget.isTneDisabled === true ? "" : scrollHandler
              }
              htmlFor="tne"
            >
              What did you try and what were you expecting?
            </label>
            <div className="flex-box-input-notice">
              <div className="input-specific">
                Describe what you tried, what you expected to happen, and what
                actually resulted. Minimum 20 characters.
              </div>
            </div>
            <Textarea id="tne" ref={tneRef} disabled={isTneDisabled} />
            <div>
              <Button
                onClick={onClickHandler}
                className={isTneHidden}
                value="tne"
                text="Next"
              />
            </div>
          </div>

          <div className="tipbox"></div>
          <div className="content">
            <label
              onClick={(e) =>
                e.currentTarget.isTagDisabled === true ? "" : scrollHandler
              }
              htmlFor="tag"
            >
              Tags
            </label>
            <div className="flex-box-input-notice">
              <div className="input-specific">
                Add up to 5 tags to describe what your question is about. Start
                typing to see suggestions.
              </div>
            </div>
            <Input
              id="tag"
              placeholder="tagbox"
              ref={tagRef}
              disabled={isTagDisabled}
            />
            <Button
              onClick={onClickHandler}
              className={isTagHidden}
              value="tag"
              text="Next"
            />
          </div>

          <div className="content">
            <label>
              Review questions already on Stack Overflow to see if your question
              is a duplicate.
            </label>
            <div className="flex-box-input-notice">
              <div className="input-specific">
                <p>
                  Clicking on these questions will open them in a new tab for
                  you to review. Your progress here will be saved so you can
                  come back and continue.
                </p>
              </div>
              <div ref={reviewRef}>
                <ReviewDropdown disabled={isReviewDisabled} />
              </div>
              <div className="confirm-review-check">
                <p>
                  Confirm that none of these existing posts on Stack Overflow
                  answers your question.
                </p>
                <div className="checkbox">
                  <input
                    type="checkbox"
                    id="confirm"
                    onClick={checking}
                    disabled={isReviewDisabled}
                  ></input>
                  <label htmlFor="confirm" aria-disabled={isReviewDisabled}>
                    I confirm that none of these posts answers my question.
                  </label>
                </div>
              </div>
              <Button
                className={isReviewHidden}
                value="review"
                text="Review your question"
                onClick={onClickHandler}
                disabled={isChecked === false ? true : false}
              />
            </div>
          </div>
        </Container>

        <div>
          <button className="hidden">Post your question</button>
          <Button
            className={isDiscardHidden}
            value="discard"
            text="Discard draft"
            onClick={showModal}
          >
            {console.log("modal")}
          </Button>
        </div>
        {IsModalOpen && <ModalBasic setModalOpen={setModalOpen} />}
      </AllContainer>
    </div>
  );
};
