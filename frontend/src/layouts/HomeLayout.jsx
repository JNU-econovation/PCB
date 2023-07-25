import { Outlet } from 'react-router-dom';
import Layout from '../components/atoms/Layout';
import InitHeader from '../components/molecules/header/InitHeader';
import HomeHeader from '../components/molecules/header/HomeHeader';
import { useAtomValue } from 'jotai';
import { sessionIdAtom } from '../store/login';
import Footer from '../components/molecules/Footer';

const HomeLayout = () => {
    const sessionId = useAtomValue(sessionIdAtom);
    return (
        <Layout>
            {!!sessionId ? <HomeHeader /> : <InitHeader />}
            <Outlet />
            <Footer />
        </Layout>
    );
};

export default HomeLayout;
