/* eslint-disable react/react-in-jsx-scope */
/* eslint-disable no-undef */
/* eslint-disable react/display-name */
import { useState } from "react";
import InputInfo from "../elements/InputInfo";
import {
  Title,
  Description,
  Input,
  Container,
  AllContainer,
  Textarea,
} from "../styles/Style";

export const PostInput = (titleValue) => {
  const infos = InputInfo;
  const [title, setTitle] = useState(titleValue.titleValue);
  const [content, setContent] = useState(titleValue.bodyValue);
  const filteredInfoId = infos.filter((el) => {
    return el.id === "title" || el.id === "tag" || el.id === "problem";
  });

  const changeValue = (e) => {
    if (e.target.id === "title") {
      setTitle(e.target.value);
    } else if (e.target.id === "body") {
      setContent(e.target.value);
    }
  };
  
  localStorage.setItem("title", title);
  localStorage.setItem("content", content);



  const filteredInfoList = filteredInfoId.map((filtered) => {
    return (
      <div key={filtered.id} className="content">
        <div className={filtered.id}>
          <label htmlFor={filtered.id}>
            {filtered.id === "problem" ? "Body" : filtered.title}
          </label>
          <div>
            <div>
              {filtered.id === "problem"
                ? "The body of your question contains your problem details and results. Minimum 220 characters."
                : filtered.description}
            </div>
          </div>
        </div>
        {(() => {
          switch (filtered.id) {
            case "title":
            case "tag":
              return (
                <Input
                  id={filtered.id}
                  onChange={changeValue}
                  defaultValue={titleValue.titleValue}
                />
              );
            case "problem":
              return (
                <Textarea
                  id="body"
                  onChange={changeValue}
                  defaultValue={titleValue.bodyValue}
                />
              );
          }
        })()}
      </div>
    );
  });

  return (
    <AllContainer key="" className="post-review">
      <Title>Review your question</Title>
      <Description>
        Please do a final review of your question and then post.
      </Description>
      <Container>{filteredInfoList}</Container>
    </AllContainer>
  );
};
