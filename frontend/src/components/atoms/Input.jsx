import { styled } from 'styled-components';

const Input = ({
    id,
    type,
    onChange,
    name,
    value,
    placeholder,
    required = false,
    className = '',
}) => {
    return (
        <StyledInput
            id={id}
            type={type}
            onChange={onChange}
            name={name}
            value={value}
            placeholder={placeholder}
            required={required}
            className={className}
        />
    );
};

const StyledInput = styled.input`
    padding: ${({ theme }) => theme.padding.lg};
    min-width: 20rem;

    border: ${({ theme }) => `${theme.border.width_xl} solid ${theme.color.gray}`};
    border-radius: ${({ theme }) => theme.border.rad_base};
    outline: none;

    &:focus {
        border-color: ${({ theme }) => theme.color.light_blue};
    }

    &.header {
        min-width: 40rem;
        border-width: ${({ theme }) => theme.border.width_base};
        border-radius: ${({ theme }) => theme.border.rad_xxl};
    }
`;

export default Input;