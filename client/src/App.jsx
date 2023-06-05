import React from 'react';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import { ThemeProvider } from 'styled-components';
import theme from './styles/theme';

import './App.css';
import CreatePage from './pages/Create/createPage';
import EditPage from './pages/Edit/editPage';
import HomePage from './pages/Home/homePage';
import ListPage from './pages/List/listPage';
import MyPage from './pages/Mypage/myPage';
import PostPage from './pages/Post/postPage';
import PostItPage from './pages/PostIt/postItPage';
import GlobalStyle from './styles/GlobalStyle';

function App() {

  return (
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <Router>
      <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/create" element={<CreatePage />} />
      <Route path="/mypage" element={<MyPage />} />
      <Route path="/list:id" element={<ListPage />} />
      <Route path="/postit:id" element={<PostItPage />} />
      <Route path="/post:id" element={<PostPage />} />
      <Route path="/edit:id" element={<EditPage />} />
    </Routes>
    </Router>
    </ThemeProvider>
    
  );
}

export default App;
