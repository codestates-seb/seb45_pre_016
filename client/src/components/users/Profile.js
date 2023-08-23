import React from 'react';
import { UserInfoContainer, InfoWrap, ActBtn, Container, AnswersTabContainer, QuestionsTabContainer } from './Styles';
// import { useState } from 'react-router-dom';

const Profile = () => {
    // const [username, setUsername] = useState('');
    return (
      <UserInfoContainer>
        <InfoWrap>
            <img src='https://i.pinimg.com/564x/18/b4/69/18b4699032c3019658996090bbe54d3f.jpg'alt='useravatar' className='user-avatar' />
            <span className='username'>UserName</span>
        </InfoWrap>
        <ActBtn>
            <button className='actBtn'>Activity</button>
        </ActBtn>

        <Container>
        <AnswersTabContainer>
            <div className='answers-tab-title'>Answers</div>
            <ul className='ans-tab-list'>
                <li>How do I remove a submodule?
                    <p>2023-08-22</p>
                </li>
                <li>답변 2
                    <p>modified_At</p>
                </li>
                <li>답변 3
                    <p>modified_At</p>
                </li>
                <li>답변 4
                    <p>modified_At</p>
                </li>
                <li>답변 5
                    <p>modified_At</p>
                </li>
            </ul>
        </AnswersTabContainer>
        <QuestionsTabContainer>
        <div className='questions-tab-title'>Questions</div>
            <ul className='que-tab-list'>
              <li>질문 1
                <p>modified_At</p>
              </li>
              <li>질문 2
                  <p>modified_At</p>
              </li>
              <li>질문 3
                <p>modified_At</p>
              </li>
              <li>질문 4
                <p>modified_At</p>
              </li>
              <li>질문 5
                <p>modified_At</p>
              </li>
            </ul>
        </QuestionsTabContainer>
        
        
        </Container>
      </UserInfoContainer>
    );
  };
  
  export default Profile;