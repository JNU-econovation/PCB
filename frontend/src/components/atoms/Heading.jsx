import { styled } from 'styled-components';

const Heading = ({ children }) => {
    return <StyledHeading>{children}</StyledHeading>;
};

const StyledHeading = styled.h1`
    margin: ${({ theme }) => theme.margin.xl};
    font-size: ${({ theme }) => theme.fontSize.heading};
    font-weight: 700;
`;

export default Heading;
