import { styled } from 'styled-components';
import logo from '../../assets/pcb.png';
import Box from './Box';

const imgs = {
    logo: logo,
};

const Image = ({ name, onClick = () => {}, className = '' }) => {
    return <StyledImage src={imgs[name]} onClick={onClick} className={className} />;
};

const StyledImage = styled.img`
    height: 1.5rem;
    .logo {
        height: 1.5rem;
        width: ${1.5 * (96 / 29)}rem;
    }
`;

export default Image;
