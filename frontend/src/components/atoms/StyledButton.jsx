import { styled } from 'styled-components';

const StyledButton = styled.button`
    padding: ${({ theme }) => theme.padding.base};
    min-width: 16rem;

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

    &:focus {
        outline: 0;
    }

    &.white {
        border-color: ${({ theme }) => theme.color.blue};
        background-color: ${({ theme }) => theme.color.white};
        color: ${({ theme }) => theme.color.blue};
    }

    &.white:hover {
        border-color: ${({ theme }) => theme.color.blue};
        background-color: ${({ theme }) => theme.color.blue};
        color: ${({ theme }) => theme.color.white};
    }

    &.check {
        padding: ${({ theme }) => theme.padding.lg};
        min-width: 6.5rem;
        margin-top: 1.9rem;
        font-size: ${({ theme }) => theme.fontSize.small};
    }

    &.small {
        padding: ${({ theme }) => theme.padding.small};
        min-width: 6.5rem;
        font-size: ${({ theme }) => theme.fontSize.small};
    }

    &.lg {
        min-width: 20rem;
        margin-top: ${({ theme }) => theme.margin.xxxl};
    }

    &.xl {
        min-width: 33.5rem;
        margin-top: ${({ theme }) => theme.margin.xl};
    }

    &.tag {
        min-width: auto;
        font-size: 0.5rem;
        padding: 0;
        margin: 0;
        margin-left: ${({ theme }) => theme.margin.small};
        border: none;
        border-radius: 0;
        background-color: ${({ theme }) => theme.color.light_blue};
        color: ${({ theme }) => theme.color.black};
    }
`;

export default StyledButton;
