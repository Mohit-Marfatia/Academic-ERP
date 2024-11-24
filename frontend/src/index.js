import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';

import LoginScreen from "./pages/login/Login"
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    {/* <App /> */}
    <LoginScreen />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
