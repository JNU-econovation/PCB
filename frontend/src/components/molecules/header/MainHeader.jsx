import { useNavigate } from 'react-router-dom';
import Image from '../../atoms/Image';
import Header from '../../atoms/Header';
import SolidIcon from '../../atoms/SolidIcon';
import Box from '../../atoms/Box';
import Dropdown from '../Dropdown';
import { useState } from 'react';

const MainHeader = () => {
    const [view, setView] = useState(false);
    const navigate = useNavigate();
    return (
        <Header>
            <Box>
                <SolidIcon name="back" size="lg" onClick={() => navigate(-1)} />
            </Box>
            <Box className="logo">
                <Image name="logo" onClick={() => navigate('/')} className="logo" />
            </Box>
            <Dropdown view={view} setView={setView} />
        </Header>
    );
};

export default MainHeader;
