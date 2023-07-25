import { styled } from 'styled-components';

const Form = ({
    children,
    onSubmit,
    onKeyPress = function () {
        return;
    },
}) => {
    return (
        <StyledForm onSubmit={onSubmit} onKeyPress={onKeyPress}>
            {children}
        </StyledForm>
    );
};

const StyledForm = styled.form`
    ${({ theme }) => theme.location.flex('column', 'center', 'center')};
    gap: 0.75rem;
    margin: ${({ theme }) => theme.margin.xl};
`;
export default Form;
