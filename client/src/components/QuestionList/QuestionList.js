/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from "react";
import { QuestionListPage, QuestionListContainer } from "./Styles";
import QuestionListContents from "./QuestionListContents";
import QuestionListHeader from "./QuestionListHeader";
// import SidebarLeft from "../component/SidebarLeft";

// FIXME
import { GetQuestions } from "../../utils/API";

function QuestionList() {
  // FIXME
  const [questionData, setQuestionData] = useState([]);

  // FIXME : 화면이 렌더링되면 미리보기 정보를 가져오게
  // useEffect(() => fetchQuestions(), []);s

  // 질문리스트 불러오는 함수s
  useEffect(() => {
    GetQuestions()
    .then((response) => { 
      setQuestionData(response.data);
      })
      .catch((error) => {
        console.log('Error fetching questions:', error);
      });
    },[]);

  return (
    <QuestionListPage>
      <QuestionListContainer>
        <QuestionListHeader questionDataLength={questionData.length} />
        <QuestionListContents questionData={questionData}/>
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
