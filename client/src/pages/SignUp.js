import styled from "styled-components";
import { useForm } from "react-hook-form";
import { useNavigate, Link } from "react-router-dom";
import { signUp } from "../utils/API";
import React from 'react';




const Wrap = styled.div`
  position: fixed;
  display: flex;
  justify-content: center;
  left: 0;
  width: 100%;2
  height: 100%;
  background-color: #F6F6F6;
  z-index: 800;
`;

const Container = styled.div`
  margin-top: 100px;
  width: 774px;
  height: 633px;
  display: flex;
  justify-content: center;
  text-align: center;
`;

const LoginWrap = styled.div`
  margin-top: 30px;
  display: flex;
  justify-content: center;
  text-align: center;
  font-size: 14px;
  > a {
    margin-left: 10px;
    text-decoration-line: none;
    color: #3172c6;
    :hover {
      cursor: pointer;
      color: #4393f7;
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
    margin-bottom: 25px;
    font-size: 16px;
  }
  > div > h1 {
    font-size: 25px;
    margin-bottom: 40px;
  }
  > div > div:nth-last-child(2) {
    font-size: 14px;
    margin-bottom: 0px;
  }
  > div > div:nth-last-child(1) {
    font-size: 14px;
    color: #3172c6;
    :hover {
      cursor: pointer;
      color: #4393f7;
    }
  }
`;

const SignUpContainer = styled.div`
  margin-top: 50px;
  display: flex;
  text-align: center;
  justify-content: center;
  height: auto;
  width: 300px;
  padding: 10px;
  background-color: #ffffff;
  border-radius: 4px;
  box-shadow: 0px 4px 4px rgba(0,0,0,0.25);
`;

const SignUpForm = styled.form`
  display: flex;
  flex-direction: column;
  text-align: center;
  justify-content: center;
  height: auto;
  width: 278px;
  margin-top: 20px;
  .singUpInstruction {
    text-align: left;
    font-size: 14px;
    color: #6a737c;
    margin-top: 0px;
  }
`;

const SignUpLabel = styled.label`
  text-align: left;
  margin-bottom: 5px;
  font-weight: bold;
`;

const SignUpInput = styled.input`
  height: 30px;
  margin-bottom: 20px;
  border: 1px solid #c4c8cc;
  border-radius: 4px;
  :focus {
    outline: none;
    border: 1px solid #83a6c4;
    box-shadow: 0 0 0 5px #e0eaf6;
  }
`;

const SignUpButton = styled.button`
  height: 40px;
  margin: 10px 0 10px 0;
  background-color: #4393f7;
  color: #ffffff;
  border: solid;
  border-color: #83a6c4;
  border-radius: 4px;
  cursor: pointer;
  :hover {
    background-color: #3172c6;
  }
`;

const ErrorMsg = styled.p`
  color: #ff0000;
  font-size: small;
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
      // 서버로 회원 가입 정보 전송
      const response = await signUp(data);

      if (response?.status === 200) { // 회원 가입이 성공한 경우
        // 회원 가입 성공 처리
        alert("회원 가입이 완료되었습니다. 로그인 페이지로 이동합니다.");
        navigate("/login"); // 로그인 페이지로 리디렉션
      } else {
        // 회원 가입 실패 처리
        alert("회원 가입에 실패했습니다. 입력 정보를 확인해주세요.");
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