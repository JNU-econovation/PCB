import { styled } from 'styled-components';
import SolidIcon from '../../atoms/SolidIcon';
import Box from '../../atoms/Box';
import { useNavigate } from 'react-router-dom';
import Header from '../../atoms/Header';
import Input from '../../atoms/Input';
import { useState } from 'react';
import Dropdown from '../Dropdown';
import Form from '../../atoms/Form';

const HomeHeader = () => {
    let navigate = useNavigate();
    const [view, setView] = useState(false);
    return (
        <Header>
            <Box>
                <SolidIcon name="back" size="lg" onClick={() => navigate(-1)} />
            </Box>
            <HeaderForm onSubmit={() => {}}>
                <Input className="header" />
            </HeaderForm>
            <Dropdown view={view} setView={setView} />
        </Header>
    );
};

const HeaderForm = styled.form`
    ${({ theme }) => theme.location.flex()}
    width: 100%;
`;

export default HomeHeader;
