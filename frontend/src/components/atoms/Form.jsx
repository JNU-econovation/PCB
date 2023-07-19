import { styled } from 'styled-components';

const Form = ({ children, onSubmit }) => {
    return <StyledForm onSubmit={onSubmit}>{children}</StyledForm>;
};

const StyledForm = styled.form`
    ${({ theme }) => theme.location.flex('column', 'flex-start', 'center')};
    gap: 0.75rem;
    margin: ${({ theme }) => theme.margin.xl};
`;
export default Form;
