import { styled } from 'styled-components';

const PostitUI = ({ children, className = 'white' }) => {
    return <StyledPostit className={className}>{children}</StyledPostit>;
};

const StyledPostit = styled.div`
    ${({ theme }) => theme.location.flex('column', 'start', 'center')}
    gap: 1rem;
    width: 23rem;
    padding: 1.5rem 2rem;
    border-radius: ${({ theme }) => theme.border.rad_base};
    box-shadow: 0px 16px 32px 0px rgba(0, 0, 0, 0.08), 0px 4px 8px 0px rgba(0, 0, 0, 0.12);

    &.yellow {
        background-color: ${({ theme }) => theme.color.soft_yellow};
    }

    &.blue {
        background-color: ${({ theme }) => theme.color.soft_blue};
    }

    &.white {
        background-color: ${({ theme }) => theme.color.white};
    }

    &.green {
        background-color: ${({ theme }) => theme.color.soft_green};
    }
`;

export default PostitUI;
