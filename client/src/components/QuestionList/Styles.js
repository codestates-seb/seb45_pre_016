import styled from 'styled-components';
import globalTokens from '../../tokens/global.json';

/*QuestionList*/


export const QuestionListPage = styled.div`

  display: flex;
  justify-content: center;
  padding-top: 54px;
  margin-bottom: 322px;
  height: 100%;

  font-family: ${globalTokens.Basic.fontFamily.value};
  font-style: normal;
  font-size: ${globalTokens.fontSize[1].value}px;
  color: ${globalTokens.Gray[800].value};

`;

export const QuestionListContainer = styled.div`
  display: flex;
  flex-direction: column;
  /* border-left: 1px solid ${globalTokens.Gray[300].value}; */
`;



/*QuestionListHeader*/

export const QuestionListHead = styled.div`

    padding: 24px;
    width: 100%;
    border-bottom: 1px solid ${globalTokens.Gray[300].value};
    display: flex;
    flex-direction: column;
    gap: 20px;


.-list--header-title,
.-list--header-sub {
    display: flex;
    align-items: center;
    justify-content: space-between;
    color: #242629;
}

.q-heading{
  font-size: 27px;
  font-weight: 400;
}

.sub-left > span {
    font-size: 15px;
    font-weight: ${globalTokens.Basic.fontWeight.value};
}

.sub-right {
    display: flex;
    gap: 20px;
}

.question-navigation > ul {
    display: flex;
    border: 1px solid ${globalTokens.Gray[400].value};
    border-radius: 5px;
    height: 42px;
    margin: 0;
    list-style: none;
    align-items: center;
}

.question-navigation > ul li {
    font-size: ${globalTokens.fontSize[0].value}px;
    padding: 10px;
    border-right: 1px solid ${globalTokens.Gray[400].value};
    cursor: pointer;
}

.question-navigation > ul li:first-child {
    height: 40px;
    background-color: ${globalTokens.Gray[200].value};
    border-radius: 5px 0 0 5px;
}

.question-navigation > ul li:last-child{
  border-right: none;
}
`;

export const AskButton = styled.div`
    #askBtn {
      width: 130px;
      height: 40px;
      margin-left: 4px;
      border: none;
      background-color: ${globalTokens.Button_Blue.value};
      color: ${globalTokens.White.value};
      border-radius: 6px;

      font-weight: 400;
    }

    #askBtn:hover {
      background-color: ${globalTokens.Button_Hover_Blue.value};
      cursor: pointer;
    }

    #askBtn a {
      text-decoration: none;
      color: white;
    }
`;

export const FilterBtn = styled.button`
    width: 75px;
    height: 42px;
    border: none;
    background-color: #E3ECF3;
    color: #477399;
    border-radius: 6px;
    text-decoration: none;
    outline: none;
    border: none;

    & a {
    text-decoration: none;
    color: #477399;
}
.filter-icon {
    margin: 4px;
}

&:hover {
    background-color: #B9D2E8;
    cursor: pointer;
}

`;




/* QuestionListContents */
export const ListItem = styled.div`
 
    /* padding: 16px; */
    width: 100%;
    /* border: 0.3px solid black; */
    display: flex;
    flex-direction: column;
    gap: 2px;


.-list-items {
    display: flex;
    align-items: center;
    height: 135px;
    padding: 16px;
    border-bottom: 1px solid ${globalTokens.Gray[300].value};
    gap: 16px;
}

.-list-item--left,
.-list-item--right {
    display: flex;
    flex-direction: column;
    height: 78px;
}

.preview-votes,
.preview-answers,
.preview-views {
    text-align: right;
    margin-bottom: 5px;
    color: ${globalTokens.Gray[800].value};
}

.-list-item--left {
    width: 164px;
    padding-left: 80px;
}

/* item.view_count가 있으면 폰트 굵게 */
.preview-bolder {
    font-weight: bold;
}

.preview-votes {
    color: #242629;
}

.-list-item--right {
  width: 888px;
}

.-item--right--preview{
  padding-top: 10px;
}

.-item--right--username {
  padding-top: 15px;
  color: ${globalTokens.Gray[700].value};
  font-size: ${globalTokens.Tiny_Text.fontSize.value};
  text-align: right;

}

.selection {
    border-radius: 6px;
    background-color: green;
    color: white;
    padding: 3px;
}

.fa-check {
    margin-right: 8px;
}

.preview-views--orange {
    color: ${globalTokens.Gray[800].value};
}
`;

export const QuestionTitle = styled.a`
.-item--right--title{
  color: ${globalTokens.Blue_Text.value};
  font-size: ${globalTokens.Q_Title.fontSize.value}px;
  text-decoration: none;
}

  :hover {
  color: ${globalTokens.Blue_Hover_Text.value};
}
`;