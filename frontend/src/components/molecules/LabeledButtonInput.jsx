import { styled } from 'styled-components';
import Label from '../atoms/Label';
import Box from '../atoms/Box';
import Input from '../atoms/Input';
import Button from '../atoms/Button';
import Text from '../atoms/Text';

const LabeledButtonInput = ({
    id,
    type,
    label,
    placeholder,
    required = false,
    value,
    onChange,
    errorMessage,
    btn = '',
    onClick,
}) => {
    return (
        <Box direction="column" justify="center" align="flex-start" gap="0rem">
            <Label htmlFor={id}>{label}</Label>
            <StyledDiv id={id}>
                <Input
                    id={id}
                    type={type}
                    onChange={onChange}
                    name={id}
                    value={value}
                    placeholder={placeholder}
                    required={required}
                    className="tag"
                />
                <Button className="xs" onClick={onClick}>
                    {btn}
                </Button>
            </StyledDiv>
            {errorMessage && <Text className="error">{errorMessage}</Text>}
        </Box>
    );
};

const StyledDiv = styled.div`
    ${({ theme }) => theme.location.flex('row', 'center', 'flex-start')}
    gap: 1rem;
    padding: ${({ theme }) => theme.padding.lg};
    width: 30rem;

    border: ${({ theme }) => `${theme.border.width_xl} solid ${theme.color.gray}`};
    border-radius: ${({ theme }) => theme.border.rad_base};
    outline: none;

    overflow-x: hidden;
    overflow-y: hidden;
    &:focus {
        border-color: ${({ theme }) => theme.color.light_blue};
    }
`;

export default LabeledButtonInput;
