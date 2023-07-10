import { styled } from 'styled-components';

const Text = ({ children, className = '' }) => {
    return <StyledMessage className={className}>{children}</StyledMessage>;
};

const StyledMessage = styled.p`
    margin: ${({ theme }) => theme.margin.small} 0;
    font-size: ${({ theme }) => theme.fontSize.base};
    font-weight: 400;
    color: ${({ theme }) => theme.color.black};

    &.error {
        color: ${({ theme }) => theme.color.red};
    }

    &.gray {
        color: ${({ theme }) => theme.color.dark_gray};
    }

    &.smallTitle {
        font-size: ${({ theme }) => theme.fontSize.xl};
        font-weight: 600;
    }

    &.title {
        font-size: ${({ theme }) => theme.fontSize.title};
        font-weight: 600;
    }
`;

export default Text;
