/* eslint-disable react/prop-types */
import { ButtonStyle, DiscardButton } from "../styles/Style";
import React from "react";

export const Button = ({
  onClick,
  text,
  className,
  isFocused,
  value,
  disabled,
}) => {
  return (
    <div>
      {className !== "discard" ? (
        <ButtonStyle
          value={value}
          isFocused={isFocused}
          className={className}
          onClick={onClick}
          disabled={disabled}
        >
          {text}
        </ButtonStyle>
      ) : (
        <DiscardButton
          value={value}
          isFocused={isFocused}
          className={className}
          onClick={onClick}
          disabled={disabled}
        >
          {text}
        </DiscardButton>
      )}
    </div>
  );
};
