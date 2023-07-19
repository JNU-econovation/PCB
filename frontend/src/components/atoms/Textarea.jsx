import { styled } from 'styled-components';

const Textarea = ({ id, rows, cols, onChange, value }) => {
    return (
        <StyledTextarea
            id={id}
            name={id}
            rows={rows}
            cols={cols}
            onChange={onChange}
            value={value}
        />
    );
};

const StyledTextarea = styled.textarea`
    padding: ${({ theme }) => theme.padding.lg};
    min-width: 20rem;

    border: ${({ theme }) => `${theme.border.width_xl} solid ${theme.color.gray}`};
    border-radius: ${({ theme }) => theme.border.rad_base};
    outline: none;
    resize: none;
`;

export default Textarea;
