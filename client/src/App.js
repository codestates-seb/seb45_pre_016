import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Provider } from 'react-redux'; // 추가
import { GlobalStyles } from '../src/utils/GlobalStyles';
import store from './utils/store'; // Redux 스토어의 경로를 가져옵니다.
import SignUp from './pages/SignUp';
import Login from './pages/Login';
import Main from './pages/Main';
import AskQuestions from './pages/AskQuestions';
import QuestionDetail from './pages/QuestionDetails';
import UserInfo from './pages/UserInfo';
import AnswerUpdate from './pages/AnswerUpdate';

function App() {
  
  return (
    <Provider store={store}> {/* 추가 */}
      <Router>
        <div className="App">
        <GlobalStyles />
          <Routes>
            <Route path='/' element={<Main/>} />
            <Route path="/signup" element={<SignUp />} />
            <Route path="/login" element={<Login />} />
            <Route path="/questions/ask" element={<AskQuestions />}></Route>
            <Route path="/questions/detail" element={<QuestionDetail />}></Route>
            <Route path="/questions/detail/answer/update" element={<AnswerUpdate />}></Route>
            <Route path="/userinfo" element={<UserInfo />}></Route>
          </Routes>
        </div>
      </Router>
    </Provider>
  );
}

export default App;