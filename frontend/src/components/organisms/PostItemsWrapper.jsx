import { mainPostListAtom } from '../../store/board';
import PostItem from '../molecules/PostItem';
import { useAtomValue } from 'jotai';
import { styled } from 'styled-components';

const PostItemsWrapper = ({ data }) => {
    return (
        <StyledWrapper>
            {data.length !== 0 &&
                data.map((data) => {
                    return <PostItem key={data.boardId} data={data} />;
                })}
        </StyledWrapper>
    );
};

const StyledWrapper = styled.div`
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    flex-wrap: wrap;
    gap: 5rem;

    max-width: 70rem;

    padding: 1rem 0;

    @media screen and (max-width: 1000px) {
        grid-template-columns: 1fr 1fr;
        //width: 26rem;
    }

    @media screen and (max-width: 600px) {
        grid-template-columns: 1fr;
        //width: 26rem;
    }
`;

export default PostItemsWrapper;
