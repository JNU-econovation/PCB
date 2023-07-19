import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { ThemeProvider } from 'styled-components';
import GlobalStyle from './styles/GlobalStyle';
import theme from './styles/theme';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import SignupPage from './pages/SignupPage';
import MyPage from './pages/MyPage';
import BoardPage from './pages/BoardPage';
import HomeLayout from './layouts/HomeLayout';
import MainLayout from './layouts/MainLayout';
import CreatePage from './pages/CreatePage';
import PostitItem from './components/molecules/PostitItem';

function App() {
    return (
        <ThemeProvider theme={theme}>
            <GlobalStyle />
            <Router>
                <Routes>
                    <Route element={<HomeLayout />}>
                        <Route path="/login" element={<LoginPage />} />
                        <Route path="/signup" element={<SignupPage />} />
                        <Route path="/" element={<HomePage />} />
                    </Route>
                    <Route element={<MainLayout />}>
                        <Route path="/mypage" element={<MyPage />} />
                        <Route path="/board/:boardId" element={<BoardPage />} />
                        <Route path="/create" element={<CreatePage />} />
                        <Route path="/postit/:boardId" element={<PostitItem />} />
                    </Route>
                </Routes>
            </Router>
        </ThemeProvider>
    );
}

export default App;
