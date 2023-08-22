import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Provider } from 'react-redux'; // 추가
import { GlobalStyles } from '../src/utils/GlobalStyles';
import store from './utils/store'; // Redux 스토어의 경로를 가져옵니다.
import SignUp from './pages/SignUp';
import Login from './pages/Login';
import Main from './pages/Main';

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
          </Routes>
        </div>
      </Router>
    </Provider>
  );
}

export default App;
