import Box from '../atoms/Box';
import Text from '../atoms/Text';
import Tag from '../atoms/Tag';
import { timestampToDate } from '../../utils/convert';
import { styled } from 'styled-components';

const BoardWrapper = ({ info }) => {
    return (
        <StyledWrapper>
            <Text className={'title'}>{info.title}</Text>
            <Box>
                <Text className="gray">작성자 : {info.nickname}</Text>
                <Text>/</Text>
                <Text className="gray">작성일자 : {timestampToDate(info.createdAt)}</Text>
            </Box>
            <Text>{info.content}</Text>
            <Box>
                {info.boardTagList.map((tag) => (
                    <Tag>{tag}</Tag>
                ))}
            </Box>
        </StyledWrapper>
    );
};

const StyledWrapper = styled.div`
    margin-top: ${({ theme }) => theme.margin.xxl};
    ${({ theme }) => theme.location.flex('column', 'start', 'center')}
    gap: 1rem;
    width: 34rem;
`;

export default BoardWrapper;
