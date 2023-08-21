import styled from "styled-components";

export const Flex = styled.div`
  display: flex;
  flex-direction: row;
`;

export const AllContainer = styled.div`
  flex-grow: 1;
  padding-top: 24px;

  .flexbox {
    margin-top: 16px;
  }

  .hidden {
    display: none;
  }

  .btn-flex {
    display: flex;
    flex-direction: row;
  }
`;

export const Title = styled.div`
  font-size: 27px;
  margin: 24px 0 27px;
  font-weight: 600;
  line-height: 1.3;
`;

export const Description = styled.div`
  font-size: 15px;
  background-color: #ebf4fb;
  padding: 16px;
  border: 1px solid #a6ceed;
  border-radius: 6px;
  margin-bottom: 16px;
`;

export const Notice = styled.div`
  background-color: #ebf4fb;
  width: inherit;
  height: fit-content;
  padding: 24px;
  color: #3b4045;
  border: 1px solid #a6ceed;
  border-radius: 6px;
  margin-bottom: 16px;

  a {
    color: #0074cc;
    text-decoration: none;
  }

  a:hover {
    color: #0a95ff;
  }

  h1 {
    font-size: 21px;
  }

  h2 {
    font-size: 21px;
    font-weight: 400;
    margin: 0 0 8px;
  }

  p {
    font-size: 15px;
    margin: 0;
  }

  .margin {
    margin: 0 0 15px 0;
  }

  h5 {
    margin: 0 0 8px;
    font-weight: 600;
    font-size: 13px;
  }

  .steps {
    font-size: 13px;
  }

  ul {
    margin: 0;
  }
`;

export const Container = styled.div`
  color: #232629;

  .content {
    display: flex;
    flex-direction: column;
    padding: 24px;
    margin-bottom: 16px;
    border: 1px solid #e4e5e7;
    border-radius: 6px;
    width: inherit;
    height: fit-content;
    background-color: #ffffff;
  }

  .title {
    flex-grow: 1;
  }

  label {
    flex-grow: 1;
    font-size: 15px;
    padding: 0px 2px;
    font-weight: 600;
    cursor: pointer;
  }

  .flex-box-input-notice {
    flex-grow: 1;
    margin: 2px 0;
  }

  .input-specific {
    font-size: 12px;
  }
`;

export const Input = styled.input`
  width: inherit;
  height: 14.994px;
  flex-grow: 1;
  margin: 4px 0;
  padding: 7.8px 9.1px;
  border: 1px solid #babfc5;
  border-radius: 6px;

  &::placeholder {
    color: #babfc3;
  }

  &:focus {
    outline: none;
    border: 1px solid #59a4de;
    box-shadow: 0 0 0 4px #deeaf7;
  }
`;

export const Textarea = styled.textarea`
  width: inherit;
  height: 254.664px;
  flex-grow: 1;
  margin: 4px 0;
  padding: 7.8px 9.1px;
  border: 1px solid #babfc5;
  border-radius: 6px;

  &::placeholder {
    color: #babfc3;
  }

  &:focus {
    outline: none;
    border: 1px solid #59a4de;
    box-shadow: 0 0 0 4px #deeaf7;
  }
`;

export const TipboxStyle = styled.div``;

export const ButtonStyle = styled.button`
  margin-top: 4px;
  flex-grow: 1;
  width: fit-content;
  background-color: #0a95ff;
  padding: 10.4px;
  height: fit-content;
  color: #fcfcff;
  font-size: 13px;
  border-radius: 6px;
  border: none;
  cursor: pointer;

  &:hover {
    background-color: #0074cc;
    color: #fafcfe;
  }

  &:active {
    outline: none;
    border: 1px solid #59a4de;
    box-shadow: 0 0 0 4px #deeaf7;
  }

  &.postbtn-margin {
    margin: 4px 8px 0px;
  }
`;

export const DiscardButton = styled.button`
  background-color: #f8f9f9;
  color: #c22d33;
  margin-top: 4px;
  flex-grow: 1;
  width: fit-content;
  padding: 10.4px;
  height: fit-content;
  font-size: 13px;
  border-radius: 6px;
  border: none;
  cursor: pointer;

  &:hover {
    background-color: #fdf2f2;
  }

  &:active {
    outline: none;
    background-color: #f9d3d3;
    box-shadow: 0 0 0 4px #f2dcdc;
  }
`;

export const DropdownStyle = styled.div`
  .dropdown-container {
    display: flex;
    flex-direction: column;
    background-color: #f8f9f9;
    color: #6a737c;
    font-size: 15px;
    outline: 1px solid #babfc5;
    border-radius: 6px;
    cursor: pointer;
  }
  .controler-flex {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
  }
  .flex1 {
    flex-grow: 1;
    padding: 12px 16px;
  }
  .arrow {
    flex-grow: 0;
    width: 18px;
    height: 18px;
    padding-right: 16px;
  }
  .dropdown-option {
    flex-grow: 1;
    background-color: #ffffff;
    color: #6a737c;
    font-size: 13px;
    text-align: center;
    border-top: 1px solid #d5d9db;
    padding: 12px 16px;
  }
  .dropdown-container:active {
    border: 1px solid #242629;
  }
`;

export const Modal = styled.div`
  .close-back {
    z-index: 1;
    position: fixed;
    width: 100vw;
    height: 100vh;
    color: black;
    top: 0;
    left: 0;
    background-color: hsla(358, 67%, 6%, 0.5);
  }

  .content {
    z-index: 999;
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    margin: auto;
    width: 432.047px;
    height: fit-content;
    background-color: hsl(0, 0%, 100%);
    text-align: center;
    padding: 24px;
    border-radius: 8px;
    box-shadow: 0 1px 4px hsla(0, 0%, 0%, 0.09);
  }

  p {
    font-size: 13px;
    margin: 0 0 24px;
    color: hsl(210, 8%, 25%);
  }

  h1 {
    font-size: 27px;
    margin: 0 0 16px;
    color: hsl(358, 62%, 47%);
    font-weight: normal;
  }

  .flex {
    display: flex;
    flex-direction: row;
    width: 100%;
  }

  .discardq,
  .cancel {
    height: 15px;
    width: fit-content;
    margin: 0px 4px;
    padding: 10.4px;
    font-size: 13px;
    border-radius: 6px;
    cursor: pointer;
  }
  .discardq {
    background-color: #d0393e;
    color: #ffffff;
  }
  .discardq:hover {
    background-color: #c22d33;
  }
  .discardq:active {
    outline: 4px solid #f8e1e1;
    background-color: #ac252a;
  }

  .cancel {
    color: #3b4045;
  }
  .cancel:hover,
  .close-btn:hover {
    background-color: hsl(210, 8%, 97.5%);
  }
  .cancel:active {
    outline: 4px solid #e9e9e9;
    background-color: #f1f2f3;
  }

  .close-btn {
    position: absolute;
    background-color: #ffffff;
    border: none;
    border-radius: 6px;
    right: 8px;
    top: 8px;
    padding: 12px;
  }
`;
