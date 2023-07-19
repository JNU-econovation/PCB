import { styled } from 'styled-components';

const Header = ({ children }) => {
    return <StyledHeader>{children}</StyledHeader>;
};

const StyledHeader = styled.header`
    padding: ${({ theme }) => theme.padding.small};
    margin-bottom: ${({ theme }) => theme.margin.xxl};
    position: sticky;
    top: 0;
    width: 100%;
    min-height: 5rem;
    max-height: 5rem;
    z-index: 100;
    display: grid;
    grid-template-columns: 1fr 1.5fr 1fr;
    justify-items: center;
    align-content: center;

    background-color: ${({ theme }) => theme.color.white};
    border-bottom: 1px solid ${({ theme }) => theme.color.light_gray};
`;

export default Header;
