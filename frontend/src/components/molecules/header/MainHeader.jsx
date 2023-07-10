import { useNavigate } from 'react-router-dom';
import Image from '../../atoms/Image';
import Header from '../../atoms/Header';
import SolidIcon from '../../atoms/SolidIcon';
import Box from '../../atoms/Box';

const MainHeader = () => {
    const navigate = useNavigate();
    return (
        <Header>
            <Box>
                <SolidIcon name="back" size="lg" onClick={() => navigate(-1)} />
            </Box>
            <Box>
                <Image name="logo" onClick={() => navigate('/')} className="logo" />
            </Box>
            <Box>
                <SolidIcon name="user" size="lg" onClick={() => navigate('/mypage')} />
            </Box>
        </Header>
    );
};

export default MainHeader;
