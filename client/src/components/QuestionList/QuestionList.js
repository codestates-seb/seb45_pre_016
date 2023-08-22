/* eslint-disable no-unused-vars */
import React, { useState } from "react";
import {QuestionListPage, QuestionListContainer} from "./Styles";
import QuestionListContents from "./QuestionListContents";
import QuestionListHeader from "./QuestionListHeader";
// import SidebarLeft from "../component/SidebarLeft";

// FIXME
import mockData from "./mockData.json";

function QuestionList() {
    // FIXME
    const [questionData, setQuestionData] = useState(mockData);

    // FIXME : 화면이 렌더링되면 미리보기 정보를 가져오게
    // useEffect(() => fetchQuestions(), []);s

    // 질문리스트 불러오는 함수s
    function fetchQuestions() {
        fetch("https://test.com/questions", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        })
            .then((response) => {
                response.json();
            })
            .then((data) => {
                setQuestionData({ ...questionData /* FIXME : 서버에서 받아온 정보*/ });
            });
    }

    return (
        <QuestionListPage >
            <QuestionListContainer>
                <QuestionListHeader questionDataLength={questionData.length} />
                <QuestionListContents questionData={questionData} />
            </QuestionListContainer>
        </QuestionListPage>
    );
}

export default QuestionList;

/* 
{
  "question_id": 1,
  "title": "질문 제목",
  "body": "질문 내용",
  "view_count": 0,
  "created_at": "yyyy-MM-dd HH:mm:ss",
  "modified_at": "yyyy-MM-dd HH:mm:ss"
} */