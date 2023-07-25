import { styled } from 'styled-components';

const Box = ({
    children,
    className = '',
    grow = null,
    direction = 'row',
    align = 'center',
    justify = 'center',
    gap = '0.5rem',
    width = 'auto',
}) => {
    return (
        <StyledBox
            className={className}
            grow={grow}
            direction={direction}
            align={align}
            justify={justify}
            gap={gap}
            width={width}
        >
            {children}
        </StyledBox>
    );
};

const StyledBox = styled.div`
    ${({ theme, direction, align, justify }) => theme.location.flex(direction, align, justify)};
    gap: ${({ gap }) => gap};
    grow: ${({ grow }) => grow && grow};
    width: ${({ width }) => width};
    &.logo {
        height: 3rem;
    }
`;

export default Box;
