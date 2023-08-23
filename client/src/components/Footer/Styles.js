import styled from 'styled-components';

import globalTokens from '../../tokens/global.json';

export const FooterContainer = styled.footer`
  display: flex;
  flex-direction: row;
  justify-content: center;
  padding: 0px;
  position: fixed;
  bottom: 0;
  width: 100%;
  height: 322px;

  background-color: #242629;
  color: ${globalTokens.Gray[400].value};

  font-family: ${globalTokens.Basic.fontFamily.value};
  font-size: ${globalTokens.Basic.fontSize.value}px;
  

  .footer {
    display: flex;
    width: 1240px;
    margin: 0 208px 0 208px;
    padding: 32px 12px 12px 12px;
  }

  .footer-nav {
    display: flex;
    flex-direction: row;
    flex: 1 0 auto;
    margin-left: 10px;
    padding-bottom: 24px;
  }

  .footer-column{
    display: flex;
    flex-direction: column;
    flex: 1 0 auto;
    flex-grow: 1;
    padding: 0 12px 24px 0;
  }

  .footer-row {
    display: flex;
    flex-direction: row;
  }

  h5 {
    font-weight: ${globalTokens.Side_Click.fontWeight.value};
    margin-bottom: 12px;
  }

  a {
    color: ${globalTokens.Gray[400].value};
    text-decoration: none;
    cursor: pointer;
  }

  li {
    list-style: none;
    padding: 4px 0 4px 0;
  }

  .footer-copyright {
      display: flex;
      flex-direction: column;
      font-size: 11px;
      > ul {
        display: flex;
        :first-child{
            margin: 0;
          }
        > li {
          padding: 4px 0;
          margin-left: 12px;
          font-size: 11px;
          
        }
      }
      > p {
        margin-top: auto;
        margin-bottom: 24px;
      }
  }
`;

export const FooterLogo = styled.div`
  display: flex;
  margin: -15px 20px 0 0;
  width: 35px;
  height: 37px;
`;