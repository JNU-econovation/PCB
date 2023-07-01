import styled from "styled-components"
import SkeletonItem from "../common/skeletonItem";

const Wrapper = styled.ul`
    ...
`;

const Post = styled.ul`
    margin: 32px;
    border: 1px solid #000;
`;

const Title = styled(SkeletonItem)`
    margin: 5px;
`;

const Content = styled(SkeletonItem)`
    margin: 5px;
`;

const Tags = styled(SkeletonItem)`
    margin: 5px;
`;

const Date = styled(SkeletonItem)`
    margin: 5px;
`;

const PostItemSkeleton = () => {
    return(<Wrapper>
        {new Array(6).fill('').map((_, i) => (
            <Post key={i}>
                <Title />
                <Content />
                <Tags />
                <Date />
            </Post>
        ))}
    </Wrapper>)
}

export default PostItemSkeleton;