import { useQuery } from '@tanstack/react-query';
import { useParams } from 'react-router-dom';
import Box from '../components/atoms/Box';
import Heading from '../components/atoms/Heading';
import CreateForm from '../components/organisms/CreateForm';
import REQUIRED from '../constants/REQUIRED';
import { boardDetail, boardUpdate } from '../services/board';
import { validateResponse } from '../utils/validateResponse';

const EditPage = () => {
    const { boardId } = useParams();
    const { data, isLoading, error } = useQuery([`boardDetail-${boardId}`], () =>
        boardDetail({ boardId })
    );

    const handleSubmit = async (e, values) => {
        e.preventDefault();
        if (!values.title) {
            alert('제목을 입력해주세요');
        } else if (!values.content) {
            alert('설명을 입력해주세요');
        } else {
            await boardUpdate({
                boardId: values.boardId,
                title: values.title,
                content: values.content,
                boardTagList: values.boardTagList,
            })
                .then((res) => {
                    navigate(`/board/:${res.data.response.boardId}`);
                })
                .catch((err) => alert(err));
        }
    };

    return (
        <Box direction="column" className="page">
            {validateResponse(data, REQUIRED.boardDetail) && (
                <>
                    <Heading>글 수정</Heading>
                    <CreateForm data={data.data.response} handleSubmit={handleSubmit} />
                </>
            )}
        </Box>
    );
};

export default EditPage;
