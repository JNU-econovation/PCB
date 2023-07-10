import { useAtomValue } from 'jotai';
import { isLoginAtom } from '../store/login';
import HomeHeader from '../components/molecules/header/HomeHeader';
import InitHeader from '../components/molecules/header/InitHeader';
import PostItemsWrapper from '../components/organisms/PostItemsWrapper';
import Box from '../components/atoms/Box';

const HomePage = () => {
    const isLogin = useAtomValue(isLoginAtom);
    return (
        <Box className="column page">
            {isLogin ? <HomeHeader /> : <InitHeader />}
            <PostItemsWrapper />
        </Box>
    );
};

export default HomePage;
