import styled from 'styled-components';

import globalTokens from '../../tokens/global.json';

export const SidebarContainer = styled.div`
  width: 164px;
  padding-top: 56px;

  font-family: ${globalTokens.Basic.fontFamily.value};
  font-style: normal;
  font-size: ${globalTokens.fontSize[1].value}px;
  color: ${globalTokens.Gray[800].value};
`

export const SidebarMenu = styled.div`
  position: sticky;
  margin-bottom: 8px;
  padding-top: 24px;
  border-right: 1px solid ${globalTokens.Gray[300].value};
  
  width: 164px;
  height: 100%;

  .nav-links{
    padding: 0;
    margin: 0 0 12px;
  }
  
  li{
    list-style: none;
  }

  .Sidebar-menu {
    position: relative;

    .active{
      background-color: ${globalTokens.Gray[100].value};
      border-right: 3px solid ${globalTokens.Header_Orange.value};
      font-weight: ${globalTokens.Side_Click.fontWeight.value};
      color: #242629;
    }
  }

  .home{
    padding: 0 0 0 8px;
  }

  .lightText {
    margin: 16px 0 4px 8px;
    font-size: 11px;
  }

  a {
    display: block;
    text-decoration: none;
    padding: 4px;
    line-height: 26px;
    cursor: pointer;

    :hover{
      color: #242629;
    }
  }
  .Sidebar-menu > a > .Questions, .Tags, .Users, .Companies {
    padding-left: 30px;
  }

  .fa-earth-americas{
    position: absolute;
    top: 10px;
    left: 15px;
  }
`;
