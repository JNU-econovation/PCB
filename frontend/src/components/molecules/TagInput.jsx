import { styled } from 'styled-components';
import Label from '../atoms/Label';
import Box from '../atoms/Box';
import Input from '../atoms/Input';
import Button from '../atoms/Button';
import Text from '../atoms/Text';

const TagInput = ({
    tagList,
    setValues,
    tag,
    setTag,
    id,
    type,
    label,
    placeholder,
    required = false,
}) => {
    const deleteTagItem = (e) => {
        const deleteTagItem = e.target.parentElement.firstChild.innerText;
        const filteredTagList = tagList.filter((tagItem) => tagItem !== deleteTagItem);
        setValues((prev) => ({ ...prev, boardTagList: filteredTagList }));
    };
    return (
        <Box direction="column" justify="center" align="flex-start" gap="0rem">
            <Label htmlFor={id}>{label}</Label>
            <StyledDiv id={id}>
                {tagList.map((tagItem, index) => (
                    <TagItem key={index}>
                        <Text className="small">{tagItem}</Text>
                        <Button onClick={deleteTagItem} className="tag">
                            X
                        </Button>
                    </TagItem>
                ))}
                <Input
                    id={id}
                    type={type}
                    onChange={(e) => setTag(e.target.value)}
                    name={id}
                    value={tag}
                    placeholder={placeholder}
                    required={required}
                    className="tag"
                />
            </StyledDiv>
        </Box>
    );
};

const StyledDiv = styled.div`
    ${({ theme }) => theme.location.flex('row', 'center', 'flex-start')}
    gap: 1rem;
    padding: ${({ theme }) => theme.padding.lg};
    width: 33.5rem;

    border: ${({ theme }) => `${theme.border.width_xl} solid ${theme.color.gray}`};
    border-radius: ${({ theme }) => theme.border.rad_base};
    outline: none;

    overflow-x: hidden;
    overflow-y: hidden;
    &:focus {
        border-color: ${({ theme }) => theme.color.light_blue};
    }
`;

const TagItem = styled.div`
    ${({ theme }) => theme.location.flex('row', 'center', 'space-between')}
    padding: ${({ theme }) => theme.padding.small};
    background-color: ${({ theme }) => theme.color.light_blue};
    border-radius: ${({ theme }) => theme.border.rad_sm};
    color: ${({ theme }) => theme.color.black};
    font-size: ${({ theme }) => theme.fontSize.small};
    width: max-content;
    height: 1.5rem;
`;

export default TagInput;
