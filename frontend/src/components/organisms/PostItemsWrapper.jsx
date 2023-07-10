import { mainPostListAtom } from '../../store/post';
import PostItem from '../molecules/PostItem';
import { useAtom } from 'jotai';
import { styled } from 'styled-components';

const PostItemsWrapper = () => {
    const [mainPostList, setMainPostList] = useAtom(mainPostListAtom);
    return (
        <StyledWrapper>
            {mainPostList.map((data) => (
                <PostItem key={data.id} data={data} />
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
