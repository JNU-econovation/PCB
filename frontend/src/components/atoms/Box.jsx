import { styled } from 'styled-components';

const Box = ({
    children,
    className = '',
    grow = null,
    direction = 'row',
    align = 'center',
    justify = 'center',
    gap = '0.5rem',
}) => {
    return (
        <StyledBox
            className={className}
            grow={grow}
            direction={direction}
            align={align}
            justify={justify}
            gap={gap}
        >
            {children}
        </StyledBox>
    );
};

const StyledBox = styled.div`
    ${({ theme, direction, align, justify }) => theme.location.flex(direction, align, justify)};
    gap: ${({ gap }) => gap};
    grow: ${({ grow }) => grow && grow};

    &.logo {
        height: 3rem;
    }
`;

export default Box;
