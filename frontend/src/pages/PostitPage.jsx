import Box from '../components/atoms/Box';
import PostitItem from '../components/molecules/PostitItem';

const PostitPage = () => {
    return (
        <Box direction="column" className="page">
            <PostitItem />
        </Box>
    );
};

export default PostitPage;
