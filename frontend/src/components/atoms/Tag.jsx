import { styled } from 'styled-components';

const Tag = ({ children }) => {
    return <StyledTag>{children}</StyledTag>;
};

const StyledTag = styled.p`
    margin-right: ${({ theme }) => theme.margin.sm}
    font-size: ${({ theme }) => theme.fontSize.base};
    color: ${({ theme }) => theme.color.blue};
`;

export default Tag;
