import { styled } from 'styled-components';
import SolidIcon from '../../atoms/SolidIcon';
import Image from '../../atoms/Image';
import Box from '../../atoms/Box';
import Button from '../../atoms/Button';
import { useNavigate } from 'react-router-dom';
import Header from '../../atoms/Header';

const InitHeader = () => {
    let navigate = useNavigate();
    return (
        <Header>
            <Box>
                <SolidIcon name="back" className="grow1" size="lg" onClick={() => navigate(-1)} />
            </Box>
            <Box>
                <Image name="logo" onClick={() => navigate('/')} className="logo" />
            </Box>
            <Box>
                <Button className="white small" onClick={() => navigate('/signup')}>
                    회원가입
                </Button>
                <Button className="small" onClick={() => navigate('/login')}>
                    로그인
                </Button>
            </Box>
        </Header>
    );
};

export default InitHeader;
