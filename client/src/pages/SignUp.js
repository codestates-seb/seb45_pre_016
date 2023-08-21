import styled from 'styled-components';
import { useForm } from "react-hook-form";
import { useNavigate, Link } from "react-router-dom";
import { signUp } from "../utils/API";
import React from 'react';
import globalTokens from '../tokens/global.json';

const Wrap = styled.div`
  position: fixed;
  display: flex;
  justify-content: center;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: ${globalTokens.Gray[200].value};
  z-index: 800;
`;

const Container = styled.div`
  margin-top: ${globalTokens.fontSize[5].value}px;
  width: 774px;
  height: 633px;
  display: flex;
  justify-content: center;
  text-align: center;
`;

const LoginWrap = styled.div`
  margin-top: ${globalTokens.fontSize[3].value}px;
  display: flex;
  justify-content: center;
  text-align: center;
  font-size: ${globalTokens.fontSize[1].value}px;
  > a {
    margin-left: 10px;
    text-decoration-line: none;
    color: ${globalTokens.Blue_Text.value};
    :hover {
      cursor: pointer;
      color: ${globalTokens.Blue_Hover_Text.value};
    }
  }
`;

const Info = styled.div`
  display: flex;
  justify-content: center;
  text-align: left;
  width: 410px;
  margin: auto auto auto 0;
  > div > div {
    margin-bottom: ${globalTokens.fontSize[5].value}px;
    font-size: ${globalTokens.fontSize[4].value}px;
  }
  > div > h1 {
    font-size: ${globalTokens.fontSize[5].value}px;
    margin-bottom: ${globalTokens.fontSize[6].value}px;
  }
  > div > div:nth-last-child(2) {
    font-size: ${globalTokens.fontSize[2].value}px;
    margin-bottom: 0px;
  }
  > div > div:nth-last-child(1) {
    font-size: ${globalTokens.fontSize[2].value}px;
    color: ${globalTokens.Blue_Text.value};
    :hover {
      cursor: pointer;
      color: ${globalTokens.Blue_Hover_Text.value};
    }
  }
`;

const SignUpContainer = styled.div`
  margin-top: ${globalTokens.fontSize[5].value}px;
  display: flex;
  text-align: center;
  justify-content: center;
  height: auto;
  width: 300px;
  padding: 10px;
  background-color: ${globalTokens.White.value};
  border-radius: 4px;
  box-shadow: ${globalTokens.Box_Shadow.x.value}px ${globalTokens.Box_Shadow.y.value}px ${globalTokens.Box_Shadow.blur.value}px ${globalTokens.Box_Shadow.color.value};
`;

const SignUpForm = styled.form`
  display: flex;
  flex-direction: column;
  text-align: center;
  justify-content: center;
  height: auto;
  width: 278px;
  margin-top: ${globalTokens.fontSize[5].value}px;
  .singUpInstruction {
    text-align: left;
    font-size: ${globalTokens.fontSize[2].value}px;
    color: ${globalTokens.Gray[400].value};
    margin-top: 0px;
  }
`;

const SignUpLabel = styled.label`
  text-align: left;
  margin-bottom: 5px;
  font-weight: ${globalTokens.fontWeights['inter-1'].value};
`;

const SignUpInput = styled.input`
  height: 30px;
  margin-bottom: ${globalTokens.fontSize[4].value}px;
  border: 1px solid ${globalTokens.Gray[300].value};
  border-radius: 4px;
  :focus {
    outline: none;
    border: 1px solid ${globalTokens.Blue_Text.value};
    box-shadow: 0 0 0 5px ${globalTokens.Input_Active.color.value};
  }
`;

const SignUpButton = styled.button`
  height: 40px;
  margin: 10px 0 10px 0;
  background-color: ${globalTokens.Button_Blue.value};
  color: ${globalTokens.White.value};
  border: solid;
  border-color: ${globalTokens.Gray[500].value};
  border-radius: 4px;
  cursor: pointer;
  :hover {
    background-color: ${globalTokens.Button_Hover_Blue.value};
  }
`;

const ErrorMsg = styled.p`
  color: ${globalTokens.Button_Orange.value};
  font-size: ${globalTokens.fontSize[0].value}px;
  width: 100%;
  margin-top: 0px;
`;





function SignUp() {
  const {
    register,
    handleSubmit,
    formState: { isSubmitting, isDirty, errors },
  } = useForm();

  const navigate = useNavigate();

  const onSubmit = async (data) => {
    try {
      const response = await signUp({
        userName: data.name, // 클라이언트에서는 name을 username으로 변경하여 보냄
        email: data.email,
        password: data.password,
      });

      if (response?.status === 201) {
        alert("회원 가입이 완료되었습니다. 로그인 페이지로 이동합니다.");
        navigate("/login");
      } else {
        alert("회원 가입에 실패했습니다. 중복된 이메일 또는 닉네임 입니다.");
      }
    } catch (error) {
      console.error("회원 가입 에러:", error);
      alert("회원 가입 중에 문제가 발생했습니다. 나중에 다시 시도해주세요.");
    }
  };
  

  return (
    <Wrap>
      <Container>
        <Info>
          <div>
            <h1>Join the Stack Overflow community</h1>
            <div>
              <div>Get unstuck — ask a question</div>
            </div>
            <div>
              <div>Unlock new privileges like voting and commenting</div>
            </div>
            <div>
              <div>Save your favorite tags, filters, and jobs</div>
            </div>
            <div>
              <div>Earn reputation and badges</div>
            </div>
            <div>
              Collaborate and share knowledge with a private group for FREE.
            </div>
            <div>Get Stack Overflow for Teams free for up to 50 users.</div>
          </div>
        </Info>
        <div>
          <SignUpContainer>
            <SignUpForm onSubmit={handleSubmit(onSubmit)}>
              <SignUpLabel htmlFor="Display Name">Display Name</SignUpLabel>
              <SignUpInput
                type="text"
                name="username"
                aria-invalid={
                  !isDirty ? undefined : errors.name ? "true" : "false"
                }
                {...register("name", {
                  required: "유저명은 필수 입력입니다.",
                  pattern: {
                    value: /^[a-zA-Z가-힣0-9]{4,16}$/,
                    message:
                      "이름은 특수문자 없이 4~16자 사이로 만들어야 합니다.",
                  },
                })}
              />
              {errors.name && (
                <ErrorMsg role="alert">{errors.name.message}</ErrorMsg>
              )}
              <SignUpLabel htmlFor="Email">Email</SignUpLabel>
              <SignUpInput
                type="email"
                name="email"
                aria-invalid={
                  !isDirty ? undefined : errors.email ? "true" : "false"
                }
                {...register("email", {
                  required: "이메일은 필수 입력입니다.",
                  pattern: {
                    value:
                      /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/,
                    message: "이메일 형식에 맞지 않습니다.",
                  },
                })}
              />
              {errors.email && (
                <ErrorMsg role="alert">{errors.email.message}</ErrorMsg>
              )}
              <SignUpLabel htmlFor="Password">Password</SignUpLabel>
              <SignUpInput
                type="password"
                name="password"
                autoComplete="on"
                aria-invalid={
                  !isDirty ? undefined : errors.password ? "true" : "false"
                }
                {...register("password", {
                  required: "비밀번호는 필수 입력입니다.",
                  pattern: {
                    value: /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z\d]).{8,}$/,
                    message:
                    "비밀번호는 8자리 이상 숫자, 문자, 특수문자 조합으로 입력해야 합니다.",
                  },
                })}
              />
              {errors.password && (
                <ErrorMsg role="alert">{errors.password.message}</ErrorMsg>
              )}
              <p className="singUpInstruction">
                Passwords must contain at least eight characters, including at
                least 1 letter and 1 number.
              </p>
              <SignUpButton type="submit" disabled={isSubmitting}>
                Sign up
              </SignUpButton>
              <p className="singUpInstruction">
                By clicking “Sign up”, you agree to our terms of service,
                privacy policy and cookie policy
              </p>
            </SignUpForm>
          </SignUpContainer>
          <LoginWrap>
            <span>Already have an account?</span>
            <Link to="/login">Login</Link>
          </LoginWrap>
        </div>
      </Container>
    </Wrap>
  );
}

export default SignUp;