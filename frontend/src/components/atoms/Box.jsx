import { styled } from 'styled-components';

const Box = ({ children, className = '', grow = null }) => {
    return (
        <StyledBox className={className} grow={grow}>
            {children}
        </StyledBox>
    );
};

const StyledBox = styled.div`
    ${({ theme }) => theme.location.flex()};
    gap: 0.5rem;
    flex-grow: ${({ grow }) => grow && grow};
    &.start {
        justify-content: flex-start;
    }

    &.column {
        ${({ theme }) => theme.location.flex('column', 'center', 'center')};
    }
`;

export default Box;
