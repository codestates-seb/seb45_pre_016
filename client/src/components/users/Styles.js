import styled from 'styled-components';
import globalTokens from '../../tokens/global.json';

export const UserInfoContainer = styled.div`
  display: flex;
  flex-direction:column;
  justify-content: flex-start;
  margin: 70px 0 322px 0;
  padding: 24px 24px 24px 24px;

  font-family: ${globalTokens.Basic.fontFamily.value};
  font-style: normal;
  font-size: ${globalTokens.fontSize[1].value}px;
  color: ${globalTokens.Gray[800].value};
`;
export const InfoWrap = styled.div`

  display: flex;
  align-items: center;
  width: 100%;

  .user-avatar {
    width: 128px;
    height: 128px;
    border-radius: 6px;
  }

  .username {
    padding-left: 24px;
    font-size:34px;
    color: #242629;
  }
`

export const ActBtn = styled.div`
   padding-top: 24px;

  .actBtn {
    display: flex;
    align-items: flex-start;
    padding: 8px 10.4px;
    background-color: ${globalTokens.Button_Orange.value};
    color: white;
    border-radius: 50px;
    border: none;
    outline: none;
    cursor: pointer;
  }
`;

export const Container = styled.div`
display: flex;
flex-direction: row;
padding-top: 35px;
gap: 45px;
`;

export const AnswersTabContainer = styled.div`
  width: 432px;
  height: 285px;
  
  .answers-tab-title {
    font-size: ${globalTokens.User_h.fontSize.value}px;
    padding-bottom: 10px;
  }
  .ans-tab-list{
    display: flex;
    flex-direction: column;
    width: 432px;
    height: 250px;
    list-style: none;
    border: 1px solid ${globalTokens.Gray[300].value};
    border-radius: 10px;
  }

  li{
    display: flex;
    padding: 12px;
    flex-grow: 1;
    justify-content: center;
    color: ${globalTokens.Blue_Text.value};
    border-bottom: 1px solid ${globalTokens.Gray[300].value};

    &:hover{
      color: ${globalTokens.Blue_Hover_Text.value};
    }
  }

  p{
    padding-left: 10px;
    color: ${globalTokens.Gray[800].value};
  }

  .ans-tab-list > li:last-child{
    border-bottom: none;
  }
`;

export const QuestionsTabContainer = styled.div`
  width: 432px;
  height: 285px;

  .questions-tab-title {
    font-size: ${globalTokens.User_h.fontSize.value}px;
    padding-bottom: 10px;
  }

  .que-tab-list{
    display: flex;
    flex-direction: column;
    width: 432px;
    height: 250px;
    list-style: none;
    border: 1px solid ${globalTokens.Gray[300].value};
    border-radius: 10px;
  }

  li{
    display: flex;
    padding: 12px;
    flex-grow: 1;
    justify-content: center;
    color: ${globalTokens.Blue_Text.value};
    border-bottom: 1px solid ${globalTokens.Gray[300].value};

    &:hover{
      color: ${globalTokens.Blue_Hover_Text.value};
    }
  }

  p{
    padding-left: 10px;
    color: ${globalTokens.Gray[800].value};
  }

  
  .que-tab-list > li:last-child{
    border-bottom: none;
  }
`;