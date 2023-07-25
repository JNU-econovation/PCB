import { styled } from 'styled-components';

const Text = ({ children, className = '' }) => {
    return <StyledMessage className={className}>{children}</StyledMessage>;
};

const StyledMessage = styled.p`
    margin: ${({ theme }) => theme.margin.small} 0;
    font-size: ${({ theme }) => theme.fontSize.base};
    font-weight: 400;
    color: ${({ theme }) => theme.color.black};
    max-width: max-content;

    &.small {
        font-size: ${({ theme }) => theme.fontSize.small};
    }

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

    &.wg_600 {
        font-weight: 600;
    }

    &.dropdown {
        ${({ theme }) => theme.location.flex()}
        gap: 1rem;
    }
`;

export default Text;
