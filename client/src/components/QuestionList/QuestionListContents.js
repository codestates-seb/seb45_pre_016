/* eslint-disable react/prop-types */
import React from "react";

import { ListItem, QuestionTitle } from "./Styles";

import { Link } from "react-router-dom";

import QuestionDetail from "../../pages/QuestionDetails";

import { useState } from "react";

function QuestionListContents({ questionData }) {

	const [currentParam, setCurrentParam] = useState();

	const sendParam = (e) => {
		setCurrentParam(e.target.value)
		console.log(currentParam)
	}


	return (
		<ListItem >
			{questionData.map((item) => {
				// 질문 내용이 280글자가 넘어가면 나머지 내용은 ...으로 표시하는 함수
				const bodyTextFilter = (text) => {
					const maxLength = 280;
					if (text.length <= maxLength) {
						return text;
					}
					return text.slice(0, maxLength) + "...";
				};

				return (
					<div
						className="-list-items"
						key={item.data.question_id}>
						<div className="-list-item--left">
							<span
								className={`preview-votes ${
									item.data.votes === "0" ? "" : "preview-bolder"
								}`}>
								{item.data.votes} votes
							</span>

							{item.data.selection ? (
								<span className="preview-answers selection">
									<i className="fa-solid fa-check"></i>answers
								</span>
							) : (
								<span className="preview-answers">answers</span>
							)}

							<span
								className={`preview-views ${
									item.data.views !== "0"
										? "preview-bolder preview-views--orange "
										: ""
								}`}>
								{parseInt(item.data.views, 10)} views
							</span>
						</div>
						<div className="-list-item--right">
							<QuestionTitle>
								{/* FIXME :  question_id를 가진페이지로 이동해야 한다. 그리고 해당페이지는 제목, 질문 등 정보를 가져야 함.*/}
								<Link to="/questions/detail" className="-item--right--title"><div value={item.data.questionId} onClick={sendParam} >{item.data.title}</div></Link>
							</QuestionTitle>
							<p className="-item--right--preview">{bodyTextFilter(item.data.content)}</p>
							<p className="-item--right--username">
								{item.userId} {item.data.modified_At}
							</p>
						</div>
						<div className="hidden"><QuestionDetail  param={currentParam}/></div>

					</div>
				);
			})}
		</ListItem>
	);
}

export default QuestionListContents;

/* 
{
	"userId": 1,
        "data": {
            "questionId": 2,
            "title": "안녕",
            "content": "하세요\n\n이것은 테스트",
            "views": 0,
            "modified_At": "2023-08-21T22:13:27.763134",
            "tags": [
                {
                    "tagId": 1,
                    "tagName": "java"
                },
                {
                    "tagId": 2,
                    "tagName": "python"
                }
            ],
            "answers": []
}
*/