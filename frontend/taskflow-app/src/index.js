import React from 'react';
import ReactDOM from 'react-dom/client';
import { createBrowserRouter, RouterProvider, Routes, Route } from "react-router-dom";
import { Navigate } from "react-router-dom";
import { createTheme, ThemeProvider } from '@mui/material/styles';
import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import Login from './screens/Login.js'
import Register from './screens/Register.js'
import Tasks from './screens/Tasks.js';
import { CssBaseline } from '@mui/material';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Navigate to="/auth/login" replace />,
  },
  {
    path:'/auth/login',
    element: <Login/>
  },
  {
    path:'/auth/register',
    element: <Register/>
  },
  {
    path:'/tasks',
    element: <Tasks/>
  }

]);

const darkTheme = createTheme({
  palette:{
    mode:'dark',
  }
});

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    {/* <Login /> */}
    <ThemeProvider theme={darkTheme}>
      <CssBaseline/>
      <RouterProvider router={router}/>
    </ThemeProvider>
    
  </React.StrictMode>
  
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
