import Box from '../components/atoms/Box';
import MainHeader from '../components/molecules/header/MainHeader';
import LoginForm from '../components/organisms/LoginForm';
import { login, logout } from '../services/api';

const LoginPage = () => {
    return (
        <Box direction="column" className="page">
            <LoginForm />
        </Box>
    );
};

export default LoginPage;
