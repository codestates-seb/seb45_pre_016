import styled from 'styled-components';
import globalTokens from '../../tokens/global.json';

export const HeaderContainer = styled.header`
  position: fixed;
  top: 0;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  flex-wrap: nowrap;
  height: 56px;
  width: 100%;
  background-color: ${globalTokens.White.value};
  border-top: 3px solid ${globalTokens.Header_Orange.value};
  border-bottom: 1px solid ${globalTokens.Gray[300].value};
  z-index: 2;

  font-family: ${globalTokens.Basic.fontFamily.value};
  font-style: normal;
  font-weight: ${globalTokens.Basic.fontWeight.value};
  font-size: ${globalTokens.Basic.fontSize.value}px;
`;

export const LogoContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 8px;
  margin-bottom: 5px;
  cursor: pointer;

  img{
    width: 150px;
    height: 30px;
  }
`;


export const Nav = styled.nav`
  display: flex;
  align-items: center;
  gap: 2px;
`;

export const NavItem = styled.div`
  display: flex;
  flex-direction: center;
  align-items: center;
  padding: 6px 12px;
  gap: 10px;
  color: ${globalTokens.Gray[700].value};
  text-decoration: none;

  a {
    text-decoration: none;
    color: #53595F;
  }

  &:hover {
    background-color:${globalTokens.Gray[200].value};
    border-radius: 50px;
    cursor: pointer;
  }
`;

export const InputContainer = styled.div`
  display: flex;
  align-items: center;
  position: relative;
  .search-icon{
    position: absolute;
    left: 20px;
    color: ${globalTokens.Gray[500].value};
  }
`;

export const SearchBox = styled.input`
  display: flex;
  flex-direction: center;
  align-items: center;
  width: 656px;
  margin: 0 8px 0 12px;
  padding: 7.8px 9.1px 7.8px 32px;
  border: 1px solid ${globalTokens.Gray[400].value};
  border-radius: 6px;
  gap: 10px;
  color: ${globalTokens.Gray[500].value};
  text-decoration: none;

  &:focus {
    outline: none;
    border-radius: 6px;
    border: 1px solid #69A3D9;
    box-shadow: 0px 0px 0px 4px #E0EAF6;
  }
`;

export const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const LoginButton = styled.div`
  .login-button {
  display: flex;
  flex-direction: center;
  align-items: center;
  padding: 8px 10.4px;
  gap: 10px;
  background-color:#E3ECF3;
  color: #477399;
  border-radius: 5px;
  text-decoration: none;

  &:hover {
    background-color: #B9D2E8;
    border-radius: 5px;
    cursor: pointer;
  }
  }
`;

export const SignupButton = styled.div`
  .signup-button {
    display: flex;
    flex-direction: center;
    align-items: center;
    padding: 8px 10.4px;
    margin-left: 5px;
    gap: 10px;
    background-color: ${globalTokens.Button_Blue.value};
    color: ${globalTokens.White.value};
    border-radius: 5px;
    text-decoration: none;

    &:hover {
      background-color: ${globalTokens.Button_Hover_Blue.value};
      border-radius: 5px;
      cursor: pointer;
    }
  }
`;

export const LoginStateButtonContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const LogoutButton = styled.div`
  .logout-button{
    display: flex;
    flex-direction: center;
    align-items: center;
    padding: 8px 10.4px;
    margin-left: 5px;
    gap: 10px;
    background-color: ${globalTokens.Button_Blue.value};
    color: ${globalTokens.White.value};
    border-radius: 5px;
    text-decoration: none;

    &:hover {
      background-color: ${globalTokens.Button_Hover_Blue.value};
      border-radius: 5px;
      cursor: pointer;
  }
  }
`;

export const UserAvatar = styled.img`
  margin-right: 5px;
  width: 32px;
  height: 32px;
  border-radius: 5px;
`