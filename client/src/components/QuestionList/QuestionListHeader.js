/* eslint-disable react/prop-types */
import React, { useState, useEffect } from "react";
import { QuestionListHead, FilterBtn, AskButton } from "./Styles";
import { Link } from "react-router-dom";

// import AskButton from "./AskButton";

function QuestionListHeader({ questionDataLength }) {
    // FIXME : 질문 갯수
    const [questionAmount, setQuestionAmount] = useState(1);

    useEffect(() => setQuestionAmount(questionDataLength), [questionDataLength]);

    const formatAmount = questionAmount.toLocaleString(); // 자릿수 표시

    return (
      <QuestionListHead >
          <div className="-list--header-title">
              <h1 className="q-heading">All Questions</h1>
                <div>
                    <AskButton>
                      <Link to={"/ask"}>
                        <button
                            id="askBtn"
                            type="button">
                            Ask Question
                        </button>
                    </Link>
                    </AskButton>
                </div>
            </div>
            <div className="-list--header-sub">
                <div className="sub-left">
                    <span>{formatAmount} questions</span>
                </div>
                <div className="sub-right">
                    <div className="question-navigation">
                      <ul>
                          <li>Newest</li>
                          <li>Active</li>
                          <li>Bountied</li>
                          <li>Unanswered</li>
                          <li>
                            More<i className="fa-solid fa-caret-down filter-icon"></i>
                          </li>
                      </ul>
                    </div>
                    <div>
                      <FilterBtn id="filterBtn">
                          <i className="fa-solid fa-filter filter-icon"></i>Filter
                      </FilterBtn>
                  </div>
              </div>
          </div>
      </QuestionListHead>
  );
}
export default QuestionListHeader;