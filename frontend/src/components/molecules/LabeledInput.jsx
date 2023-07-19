import { styled } from 'styled-components';
import Input from '../atoms/Input';
import Label from '../atoms/Label';
import Text from '../atoms/Text';
import Box from '../atoms/Box';

const LabeledInput = ({
    id,
    type,
    value,
    onChange,
    label,
    placeholder,
    required = false,
    errorMessage,
    className = '',
}) => {
    return (
        <Box direction="column" justify="center" align="flex-start" gap="0rem">
            <Label htmlFor={id}>{label}</Label>
            <Input
                id={id}
                type={type}
                onChange={onChange}
                name={id}
                value={value}
                placeholder={placeholder}
                required={required}
                className={className}
            />
            {errorMessage && <Text className="error">{errorMessage}</Text>}
        </Box>
    );
};

export default LabeledInput;
