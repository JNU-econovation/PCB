import { Outlet } from 'react-router-dom';
import Layout from '../components/atoms/Layout';
import InitHeader from '../components/molecules/header/InitHeader';
import HomeHeader from '../components/molecules/header/HomeHeader';
import { useAtomValue } from 'jotai';
import { isLoginAtom } from '../store/login';
import Footer from '../components/molecules/Footer';

const HomeLayout = () => {
    const isLogin = useAtomValue(isLoginAtom);
    return (
        <Layout>
            {isLogin ? <HomeHeader /> : <InitHeader />}
            <Outlet />
            <Footer />
        </Layout>
    );
};

export default HomeLayout;
