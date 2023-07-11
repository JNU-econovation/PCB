import Text from '../atoms/Text';
import Tag from '../atoms/Tag';
import Box from '../atoms/Box';
import { styled } from 'styled-components';
import { useNavigate } from 'react-router-dom';

const PostItem = ({ data }) => {
    const navigate = useNavigate();
    const { boardId, title, content, boardTagList, createdAt } = data;
    return (
        <StyledPostItem onClick={() => navigate(`/post/:${boardId}`)}>
            <Text className="smallTitle">{title}</Text>
            <Text className="gray">
                {content.length < 200 ? content : `${content.substr(0, 200)} ...`}
            </Text>
            <Box className="start">{boardTagList && boardTagList.map((t) => <Tag>#{t}</Tag>)}</Box>
            <Text>
                {new Date(createdAt).getFullYear()}년 {new Date(createdAt).getMonth() + 1}월{' '}
                {new Date(createdAt).getDate()}일
            </Text>
        </StyledPostItem>
    );
};

const StyledPostItem = styled.div`
    display: grid;
    grid-template-rows: 5.5rem 12rem 2rem 2rem;

    height: 26rem;
    width: 19rem;
    padding: 2rem;

    background-color: ${({ theme }) => theme.color.soft_white};
    border-radius: ${({ theme }) => theme.border.rad_lg};

    transition: transform 0.2s;

    &:hover {
        transform: scale(1.05);
    }
`;

export default PostItem;
