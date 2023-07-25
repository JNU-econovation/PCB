import { styled } from 'styled-components';

const ColorCircle = ({ className, onClick, children }) => {
    return (
        <StyledCircle className={className} onClick={onClick}>
            {children}
        </StyledCircle>
    );
};

const StyledCircle = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 1.25rem;
    height: 1.25rem;
    border-radius: 50%;
    border: 1px solid ${({ theme }) => theme.color.border_white};

    &.white {
        background-color: ${({ theme }) => theme.color.white};
        border: 1px solid ${({ theme }) => theme.color.border_white};
    }

    &.yellow {
        background-color: ${({ theme }) => theme.color.soft_yellow};
        border: 1px solid ${({ theme }) => theme.color.border_yellow};
    }

    &.green {
        background-color: ${({ theme }) => theme.color.soft_green};
        border: 1px solid ${({ theme }) => theme.color.border_green};
    }

    &.blue {
        background-color: ${({ theme }) => theme.color.soft_blue};
        border: 1px solid ${({ theme }) => theme.color.border_blue};
    }
`;

export default ColorCircle;
