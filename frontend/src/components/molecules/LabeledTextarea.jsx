import Box from '../atoms/Box';
import Textarea from '../atoms/Textarea';
import Label from '../atoms/Label';

const LabeledTextarea = ({ id, label, onChange, value, rows, cols }) => {
    return (
        <Box direction="column" justify="center" align="flex-start" gap="0rem">
            <Label htmlFor={id}>{label}</Label>
            <Textarea id={id} onChange={onChange} name={id} value={value} rows={rows} cols={cols} />
        </Box>
    );
};

export default LabeledTextarea;
