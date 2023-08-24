import React from 'react';

import Header from '../components/Header/Header';
import Sidebar from '../components/Sidebar/Sidebar';
import Footer from '../components/Footer/Footer';
import Profile from '../components/users/Profile';

const UserInfo = () => {
  return (
    <div>
        <Header />
				<div style={{display:'flex', justifyContent:'center'}}>
        <Sidebar />
        <Profile />
        </div>
        <Footer />
    </div>
  );
};

export default UserInfo;

