import { useQuery } from '@tanstack/react-query';
import { useParams } from 'react-router-dom';
import PostItemsWrapper from '../components/organisms/PostItemsWrapper';
import { search } from '../services/api';

const SearchPage = () => {
    const { tag } = useParams();
    const [lastBoardId, setLastBoardId] = useState('');

    const { data, isLoading, error } = useQuery(
        ['searchBoard'],
        () => search({ lastBoardId: lastBoardId, limit: 9, tag: tag }),
        {
            onSuccess: (data) => {
                const content = data?.data?.response?.content;
                if (content) setLastBoardId(content[content.length - 1].boardId);
            },
        }
    );

    return (
        <Box direction="column" className="page">
            <PostItemsWrapper />
            {}
        </Box>
    );
};

export default SearchPage;
