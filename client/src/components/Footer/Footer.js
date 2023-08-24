import React from 'react';
// import { useState, useEffect } from 'react';

import { FooterContainer,FooterLogo } from './Styles';

const Footer = () => {
  
  return (
    <FooterContainer>
        <div className="footer">
      <FooterLogo>
        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Stack_Overflow_icon.svg/1024px-Stack_Overflow_icon.svg.png?20190716190036"
        alt="Footer Logo" />
      </FooterLogo>

        <nav className="footer-nav">

          <div className="footer-column">
            <h5>
           STACK OVERFLOW
            </h5>
            <ul>
              <li>
                Questions
              </li>
              <li>
                <a href="https://stackoverflow.com/help">Help</a>
              </li>
            </ul>
          </div>

          <div className="footer-column">
            <h5>PRODUCTS</h5>
            <ul>
              <li>
                <a href="https://stackoverflow.com/teams">Teams</a>
              </li>
              <li>
                <a href="https://stackoverflow.com/advertising">Advertising</a>
              </li>
              <li>
                <a href="https://stackoverflow.com/collectives">Collectives</a>
              </li>
              <li>
                <a href="https://stackoverflow.com/talent">Talent</a>
              </li>
            </ul>
          </div>

          <div className="footer-column">
            <h5>
              <a href="https://stackoverflow.co/">COMPANY</a>
            </h5>
            <ul>
              <li>
                <a href="https://stackoverflow.co/">About</a>
              </li>
              <li>
                <a href="https://stackoverflow.co/company/press">Press</a>
              </li>
              <li>
                <a href="https://stackoverflow.co/company/work-here">Work Here</a>
              </li>
              <li>
                <a href="https://stackoverflow.com/legal/terms-of-service">Legal</a>
              </li>
              <li>
                <a href="https://stackoverflow.com/legal/privacy-policy">Privacy Policy</a>
              </li>
              <li>
                <a href="https://stackoverflow.com/legal/terms-of-service">Terms of Service</a>
              </li>
              <li>
                <a href="https://stackoverflow.co/company/contact">Contact Us</a>
              </li>
              <li>Cookie Settings</li>
              <li>
                <a href="https://stackoverflow.com/legal/cookie-policy">Cookie Policy</a>
              </li>
            </ul>
          </div>

          <div className="footer-column">
            <h5>
              <a href="https://stackexchange.com/">STACK EXCHANGE NETWORK</a>
            </h5>
            <ul>
              <li>
                <a href="https://stackexchange.com/sites#technology">Technology</a>
              </li>
              <li>
                <a href="https://stackexchange.com/sites#culturerecreation">Culture & recreation</a>
              </li>
              <li>
                <a href="https://stackexchange.com/sites#lifearts">Life & arts</a>
              </li>
              <li>
                <a href="https://stackexchange.com/sites#science">Science</a>
              </li>
              <li>
                <a href="https://stackexchange.com/sites#professional">Professional</a>
              </li>
              <li>
                <a href="https://stackexchange.com/sites#business">Business</a>
              </li>
              <li></li>
              <li>
                <a href="https://api.stackexchange.com/">API</a>
              </li>
              <li>
                <a href="https://data.stackexchange.com/">Data</a>
              </li>
            </ul>
          </div>
        </nav>

        <div className="footer-copyright">
          <ul>
            <li>
              <a href="https://stackoverflow.blog/?blb=1/">Blog</a>
            </li>
            <li>
              <a href="https://www.facebook.com/officialstackoverflow/">Facebook</a>
            </li>
            <li>
              <a href="https://twitter.com/stackoverflow">Twitter</a>
            </li>
            <li>
              <a href="https://linkedin.com/company/stack-overflow">LinkedIn</a>
            </li>
            <li>
              <a href="https://www.instagram.com/thestackoverflow">Instagram</a>
            </li>
          </ul>
          <p>
            Site design / logo © 2023 Stack Exchange Inc; user <br></br>contributions
            licensed under CC BY-SA.<br></br>
            <span>rev 2023.8.15.43579</span>
          </p>
          </div>
          </div>
    </FooterContainer>
  );
};

export default Footer;