import { styled } from 'styled-components';

const SubmitButton = ({ children, className = '' }) => {
    return (
        <StyledButton type="submit" className={className}>
            {children}
        </StyledButton>
    );
};

const StyledButton = styled.button`
    padding: ${({ theme }) => theme.padding.base};
    min-width: 16rem;
    margin: ${({ theme }) => theme.margin.base};

    /* Layout */

    font-size: ${({ theme }) => theme.fontSize.base};

    border: 2px solid ${({ theme }) => theme.color.blue};
    border-radius: ${({ theme }) => theme.border.rad_base};

    background-color: ${({ theme }) => theme.color.blue};
    color: ${({ theme }) => theme.color.white};

    &:hover {
        border-color: ${({ theme }) => theme.color.light_blue};
        background-color: ${({ theme }) => theme.color.light_blue};
    }

    &.white {
        border-color: ${({ theme }) => theme.color.green};
        background-color: ${({ theme }) => theme.color.white};
        color: ${({ theme }) => theme.color.green};
    }

    &.white:hover {
        border-color: ${({ theme }) => theme.color.blue};
        background-color: ${({ theme }) => theme.color.blue};
        color: ${({ theme }) => theme.color.white};
    }

    &.small {
        padding: ${({ theme }) => theme.padding.small};
        min-width: 8rem;
        font-size: ${({ theme }) => theme.fontSize.small};
    }

    &.large {
        min-width: 28rem;
    }
`;

export default SubmitButton;
