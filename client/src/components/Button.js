/* eslint-disable react/prop-types */
import { ButtonStyle, DiscardButton } from "../styles/Style";
import React from "react";

export const Button = ({ className, onClick, text }) => {
  return (
    <div>
      {text !== "Discard draft" ? (
        <ButtonStyle className={className} onClick={onClick}>
          {text}
        </ButtonStyle>
      ) : (
        <DiscardButton onClick={onClick}>{text}</DiscardButton>
      )}
    </div>
  );
};
