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
import PostitPage from './pages/PostitPage';
import { useAtom, useAtomValue } from 'jotai';
import { isLoginAtom, sessionIdAtom } from './store/login';
import { useEffect } from 'react';
import ROUTES from './constants/ROUTES';
import { useNavigate } from 'react-router-dom';
import EditPage from './pages/EditPage';

const PrivateRouter = () => {
    return (
        <>
            <Route element={<HomeLayout />}>
                <Route path={ROUTES.home} element={<HomePage />} />
            </Route>
            <Route element={<MainLayout />}>
                <Route path={ROUTES.mypage} element={<MyPage />} />
                <Route path={`${ROUTES.board}/:boardId`} element={<BoardPage />} />
                <Route path={ROUTES.create} element={<CreatePage />} />
                <Route path={`${ROUTES.postit}/:boardId`} element={<PostitPage />} />
                <Route path={`${ROUTES.edit}/:boardId`} element={<EditPage />} />
                <Route path={`${ROUTES.search}/:keyword`} element={<EditPage />} />
            </Route>
        </>
    );
};

const PublicRouter = () => {
    return (
        <>
            <Route element={<HomeLayout />}>
                <Route path={ROUTES.login} element={<LoginPage />} />
                <Route path={ROUTES.signup} element={<SignupPage />} />
                <Route path={ROUTES.home} element={<HomePage />} />
            </Route>
        </>
    );
};

function AppRouter() {
    const [isLogin, setIsLogin] = useAtom(isLoginAtom);
    const sessionId = useAtomValue(sessionIdAtom);

    useEffect(() => {
        if (!sessionId) {
            setIsLogin(false);
            return;
        }
        setIsLogin(true);
    }, [sessionId]);

    return (
        <Router basename={'/pcb/'}>
            <Routes>{isLogin ? PrivateRouter() : PublicRouter()}</Routes>
        </Router>
    );
}

function App() {
    return (
        <ThemeProvider theme={theme}>
            <GlobalStyle />
            <AppRouter />
        </ThemeProvider>
    );
}

export default App;
