import styled from "styled-components";
import { useForm } from "react-hook-form";
import { useNavigate, Link } from "react-router-dom";
import axios from "axios";
import jwt_decode from "jwt-decode";
import React from 'react';
import { useDispatch } from "react-redux";
import { userInfo } from "../utils/userIdslice";
import globalTokens from '../tokens/global.json';
import Header from '../components/Header/Header';

const Wrap = styled.div`
  position: fixed;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: ${globalTokens.Gray[100].value};
  z-index: 800;
`;

const SignupWrap = styled.div`
  display: flex;
  justify-content: center;
  text-align: center;
  font-size: ${globalTokens.fontSize[1].value}px;
  > a {
    margin-left: 10px;
    text-decoration-line: none;
    :hover {
      cursor: pointer;
    }
  }
`;

const LoginContainer = styled.div`
  margin: 150px auto;
  display: flex;
  text-align: center;
  justify-content: center;
  height: 400px;
  width: 412px;
  background-color: ${globalTokens.White.value};
  border-radius: 2%;
  box-shadow: ${globalTokens.Box_Shadow.x.value}px ${globalTokens.Box_Shadow.y.value}px ${globalTokens.Box_Shadow.blur.value}px ${globalTokens.Box_Shadow.color.value};
`;

const LoginForm = styled.form`
  display: flex;
  flex-direction: column;
  text-align: center;
  justify-content: center;
  height: 390px;
  width: 278px;
`;

const LoginLabel = styled.label`
  text-align: left;
  margin-bottom: 5px;
  font-weight: ${globalTokens.fontWeights['inter-1'].value};
`;

const LoginInput = styled.input`
  height: 30px;
  margin-bottom: 20px;
  border: 1px solid ${globalTokens.Gray[300].value};
  border-radius: 4px;
  :focus {
    outline: none;
   
    box-shadow: 0 0 0 5px ${globalTokens.Input_Active.color.value};
  }
`;

const LoginButton = styled.button`
  height: 40px;
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
  margin-bottom: 10px;
`;


function Login() {
  const dispatch = useDispatch();
  const {
    register,
    handleSubmit,
    formState: { isSubmitting, isDirty, errors },
  } = useForm();
  const navigate = useNavigate();

  const onSubmit = async (data) => {
    try {
      const res = await axios.post("http://43.201.157.40:8080/users/login", data); // Use axios for POST request
      console.log("응답 데이터:", res.data); // Log the response data

      if (res?.status === 200) {
        const accessToken = res.headers.authorization;
        const userId = jwt_decode(accessToken);
        const email = data.email; // 이메일 값을 추출합니다
        const name = data.userName;
        localStorage.setItem("Id", email); // 이메일 값을 사용하여 저장합니다
        localStorage.setItem("Token", accessToken);
        localStorage.setItem("Name", name);
        dispatch(userInfo({ userId, accessToken }));
        navigate("/");
      } else {
        const errorText = res?.data?.message || "로그인에 실패했습니다. 이메일과 비밀번호를 다시 확인해주세요.";
        alert(errorText);
      }
    } catch (error) {
      console.error("로그인 에러:", error);
      if (error.response && error.response.status === 401) {
        alert("유효하지 않은 토큰입니다. 다시 로그인해주세요.");
      } else {
        alert("로그인 중에 문제가 발생했습니다. 나중에 다시 시도해주세요.");
      }
    }
  };
  
  
  
  
  return (
    <Wrap>
      <Header />
      <LoginContainer>
        <LoginForm onSubmit={handleSubmit(onSubmit)}>
          <LoginLabel htmlFor="email">Email</LoginLabel>
          <LoginInput
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
          <LoginLabel htmlFor="password">Password</LoginLabel>
          <LoginInput
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
           <ErrorMsg role="alert">{errors.password.message.replace("'", "&apos;")}</ErrorMsg>
          )}
          <LoginButton type="submit" disabled={isSubmitting}>
            Log in
          </LoginButton>
        </LoginForm>
      </LoginContainer>
      <SignupWrap>
        <div>New here?</div>
      <Link to="/signup">Sign up</Link>
       </SignupWrap>

    </Wrap>
  );
}

export default Login;