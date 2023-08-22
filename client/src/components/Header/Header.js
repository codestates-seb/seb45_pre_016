import React from 'react';
// import { useState, useEffect } from 'react';


import { HeaderContainer, LogoContainer,  Nav, NavItem, LoginButton, SignupButton, SearchBox, InputContainer, ButtonContainer, LoginStateButtonContainer, LogoutButton, UserAvatar } from './styles';
import { Link } from 'react-router-dom';

const Header = () => {
  const userId = localStorage.getItem('userId') 
  // const [userInfo, setUserInfo] = useState(null); // 유저정보상태추가

  // useEffect(() => {
  //   if (isLogin) {
  //     axios.get('http://localhost:8080/users/mypage/1')
  //     .then(response => {
  //       setUserInfo(response.data);
  //     })
  //     .catch(error => {
  //       console.error('Error fetching user info:', error);
  //     });
  //   }
  // }, [isLogin]);
  
  return (
    <HeaderContainer>
      
      <LogoContainer>
        <Link to = "/">
        <img src='https://stackoverflow.design/assets/img/logos/so/logo-stackoverflow.svg' alt='logo' />
        </Link>
      </LogoContainer>
    
      <Nav>
        <NavItem><a href='https://stackoverflow.co/'>About</a> </NavItem>
        <NavItem>Products</NavItem>
        <NavItem><a href='https://stackoverflow.co/teams/'>For Team</a></NavItem>
      </Nav>

      <InputContainer>
        <i className="fa-solid fa-magnifying-glass search-icon"></i>
        <SearchBox placeholder='Search...'></SearchBox>
      </InputContainer>

      {userId ? (
      <LoginStateButtonContainer>
        <UserAvatar src='https://i.pinimg.com/564x/18/b4/69/18b4699032c3019658996090bbe54d3f.jpg' 
        alt='useravatar' />
        <LogoutButton>
          <Link to="/" className='logout-button' onClick={() => localStorage.clear()}>
            Logout
          </Link>
         </LogoutButton>
        </LoginStateButtonContainer>) 
      : (
        <ButtonContainer>
          <LoginButton>
            <Link to="/login" className='login-button'>
              Log in
            </Link>
          </LoginButton>
          <SignupButton>
            <Link to="/signup" className='signup-button'>
            Sign up
            </Link>
          </SignupButton>
        </ButtonContainer>
          ) 
        }

    </HeaderContainer>
  );
};

export default Header;