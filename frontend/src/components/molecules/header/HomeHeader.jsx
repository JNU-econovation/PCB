import { styled } from 'styled-components';
import SolidIcon from '../../atoms/SolidIcon';
import Box from '../../atoms/Box';
import Button from '../../atoms/Button';
import { useNavigate } from 'react-router-dom';
import Header from '../../atoms/Header';

const HomeHeader = () => {
    let navigate = useNavigate();
    return (
        <Header>
            <Box>
                <SolidIcon name="back" size="lg" onClick={() => navigate(-1)} />
            </Box>
            <Box>
                <Input className="grow1" />
            </Box>
            <Box>
                <Button className="white" onClick={() => navigate('/create')}>
                    새 글 작성
                </Button>
                <SolidIcon name="user" size="lg" onClick={() => navigate('/mypage')} />
            </Box>
        </Header>
    );
};

export default HomeHeader;
