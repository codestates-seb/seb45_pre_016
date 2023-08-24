import React from 'react';

import Header from '../components/Header/Header';
import Sidebar from '../components/Sidebar/Sidebar';
import Footer from '../components/Footer/Footer';
import QuestionList from '../components/QuestionList/QuestionList';

const Main = () => {
  return (
    <div>
        <Header />
				<div style={{display:'flex', justifyContent:'center'}}>
        <Sidebar />
        <QuestionList />
        </div>
        <Footer />
    </div>
  );
};

export default Main;

