import { useState } from 'react';
import Box from '../components/atoms/Box';
import Heading from '../components/atoms/Heading';
import CreateForm from '../components/organisms/CreateForm';
import FORM_DEFAULT from '../constants/FORM_DEFAULT';
import { boardCreate } from '../services/board';

const CreatePage = () => {
    const [value, setValues] = useState();
    const handleSubmit = async (e, values) => {
        e.preventDefault();
        if (!values.title) {
            alert('제목을 입력해주세요');
        } else if (!values.content) {
            alert('설명을 입력해주세요');
        } else {
            await boardCreate({
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
            <Heading>글 작성</Heading>
            <CreateForm data={FORM_DEFAULT.BOARD} handleSubmit={handleSubmit} />
        </Box>
    );
};

export default CreatePage;
