import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import SignUp from './pages/SignUp'; // 경로는 실제 파일의 위치에 따라 조정해주세요.
import Login from './pages/Login';

function App() {
  return (
    <Router>
      <div className="App">
        {/* 다른 컴포넌트들을 렌더링하는 코드 */}
        <Routes>
          <Route path="/signup" element={<SignUp />} />
          <Route path="/login" element={<Login />} />
          {/* 다른 Route들을 추가할 수 있습니다. */}
        </Routes>
      </div>
    </Router>
  );
}

export default App;
