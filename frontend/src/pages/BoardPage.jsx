import Box from '../components/atoms/Box';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { boardDetail } from '../services/board';
import BoardWrapper from '../components/organisms/BoardWrapper';
import Button from '../components/atoms/Button';
import Text from '../components/atoms/Text';

const BoardPage = () => {
    const { boardId } = useParams();
    const [values, setValues] = useState({
        title: '제목입니다',
        content:
            '안녕하십니까 어쭈구 저쭈구 입니다. 잘 부탁드립니다. 이건 board page 입니다. 고구마입니다. 감자도 될 수 있습니다 어쭈구 저쭈구 입니다',
        createAt: 1684850580,
        creator: '또리',
        boardTagList: ['태그1', '강아지', '고구마', '감자'],
    });

    useEffect(() => {
        fetchData();
    }, []);
    const fetchData = async () => {
        await boardDetail({ boardId: boardId })
            .then((res) => setValues(res.data.response))
            .catch((err) => alert(err));
    };
    return (
        <Box direction="column" justify="start" className="page" gap="5rem">
            <Text></Text>
            <BoardWrapper info={values} />
            <Button className="xl">참여하기</Button>
        </Box>
    );
};

export default BoardPage;
