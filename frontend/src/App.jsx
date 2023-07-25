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
import { useAtomValue } from 'jotai';
import { sessionIdAtom } from './store/login';
import ROUTES from './constants/ROUTES';
import EditPage from './pages/EditPage';
import { Navigate } from 'react-router-dom';

const PrivateRoute = ({ component: Component }) => {
    const sessionId = JSON.parse(localStorage.getItem('sessionId'));
    return !!sessionId ? (
        Component
    ) : (
        <Navigate to={ROUTES.login} {...alert('로그인이 필요합니다.')} />
    );
};

const PublicRoute = ({ component: Component }) => {
    const sessionId = JSON.parse(localStorage.getItem('sessionId'));
    console.log(!!sessionId);
    return !!sessionId ? (
        <Navigate to={ROUTES.home} {...alert('이미 로그인 되었습니다.')} />
    ) : (
        Component
    );
};

const PrivateRouter = () => {
    return (
        <>
            <Route element={<HomeLayout />}>
                <Route path={ROUTES.home} element={<PrivateRoute component={<HomePage />} />} />
            </Route>
            <Route element={<MainLayout />}>
                <Route path={ROUTES.mypage} element={<PrivateRoute component={<MyPage />} />} />
                <Route
                    path={`${ROUTES.board}/:boardId`}
                    element={<PrivateRoute component={<BoardPage />} />}
                />
                <Route path={ROUTES.create} element={<PrivateRoute component={<CreatePage />} />} />
                <Route
                    path={`${ROUTES.postit}/:boardId`}
                    element={<PrivateRoute component={<PostitPage />} />}
                />
                <Route
                    path={`${ROUTES.edit}/:boardId`}
                    element={<PrivateRoute component={<EditPage />} />}
                />
                <Route
                    path={`${ROUTES.search}/:tag`}
                    element={<PrivateRoute component={<EditPage />} />}
                />
            </Route>
        </>
    );
};

const PublicRouter = () => {
    return (
        <>
            <Route element={<HomeLayout />}>
                <Route path={ROUTES.login} element={<PublicRoute component={<LoginPage />} />} />
                <Route path={ROUTES.signup} element={<PublicRoute component={<SignupPage />} />} />
            </Route>
        </>
    );
};

function AppRouter() {
    return (
        <Router basename={'/pcb/'}>
            <Routes>
                {PrivateRouter()}
                {PublicRouter()}
            </Routes>
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
