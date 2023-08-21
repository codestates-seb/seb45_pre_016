/* eslint-disable react/react-in-jsx-scope */
import "./App.css";
import { AskQuestions } from "./pages/AskQuestions";
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    // eslint-disable-next-line react/react-in-jsx-scope
    <BrowserRouter>
      <Routes>
        <Route path="ask" element={<AskQuestions />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
