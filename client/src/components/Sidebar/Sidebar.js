import React from 'react';

import { useState } from 'react';
import { SidebarContainer, SidebarMenu } from './Styles';

const Sidebar = () => {
  const [active, setActive] = useState('Home');

    return (
        <SidebarContainer>
          <SidebarMenu>
            <nav>
            <ol className='nav-links'>
              <li className='Sidebar-menu'>
                <a className={'Home' === active ? 'active' : ''}
              onClick={() => setActive('Home')}>
                <div className='home'>Home</div>
                </a>
              </li>

              <li className='nav-List'>
                <ol>
                  <li className='lightText'>
                    PUBLIC
                  </li>

                  <li className='Sidebar-menu'>
                    <a className={'Questions' === active ? 'active' : ''}
              onClick={() => setActive('Questions')}>
                
                    <i className="fa-solid fa-earth-americas"></i>
                    <span className='Questions'>
                      Questions
                    </span>
                    </a>
                  </li>

                  <li className='Sidebar-menu'>
                    <a className={'Tags' === active ? 'active' : ''}
              onClick={() => setActive('Tags')}>
                    <div className='Tags'>Tags</div>
                    </a>
                  </li>

                  <li className='Sidebar-menu'>
                    <a className={'Users' === active ? 'active' : ''}
              onClick={() => setActive('Users')}>
                    <div className='Users'>Users</div>
                    </a>
                  </li>

                  <li className='Sidebar-menu'>
                    <a className={'Companies' === active ? 'active' : ''}
              onClick={() => setActive('Companies')}>
                    <div className='Companies' >Companies</div>
                    </a>
                  </li>

                  </ol>
                </li>
              </ol>
            </nav>
          </SidebarMenu>
        </SidebarContainer>
    );
};

export default Sidebar;