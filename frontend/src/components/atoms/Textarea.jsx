import { styled } from 'styled-components';

const Textarea = ({ id, rows, cols, onChange, value, className = '' }) => {
    return (
        <StyledTextarea
            id={id}
            name={id}
            rows={rows}
            cols={cols}
            onChange={onChange}
            value={value}
            className={className}
        />
    );
};

const StyledTextarea = styled.textarea`
    padding: ${({ theme }) => theme.padding.lg};
    width: 100%;

    border: ${({ theme }) => `${theme.border.width_xl} solid ${theme.color.gray}`};
    border-radius: ${({ theme }) => theme.border.rad_base};
    outline: none;
    resize: none;

    &.postit {
        height: 100%;
        border: ${({ theme }) => `1.5px solid ${theme.color.border_white}`};
        font-size: ${({ theme }) => theme.fontSize.small};
    }
`;

export default Textarea;
