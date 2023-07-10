import { mainPostListAtom } from '../../store/post';
import PostItem from '../molecules/PostItem';
import { useAtomValue } from 'jotai';
import { styled } from 'styled-components';

const PostItemsWrapper = () => {
    const mainPostList = useAtomValue(mainPostListAtom);

    return (
        <StyledWrapper>
            {mainPostList.map((data) => (
                <PostItem key={data.boardId} data={data} />
            ))}
        </StyledWrapper>
    );
};

const StyledWrapper = styled.div`
    ${({ theme }) => theme.location.flex('row', 'flex-start', 'space-around')}
    flex-wrap: wrap;
    gap: 5rem;

    max-width: 70rem;

    padding: 1rem 0;
`;

export default PostItemsWrapper;
