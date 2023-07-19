import { Outlet } from 'react-router-dom';
import Layout from '../components/atoms/Layout';
import MainHeader from '../components/molecules/header/MainHeader';
import Footer from '../components/molecules/Footer';

const MainLayout = () => {
    return (
        <Layout>
            <MainHeader />
            <Outlet />
            <Footer />
        </Layout>
    );
};

export default MainLayout;
