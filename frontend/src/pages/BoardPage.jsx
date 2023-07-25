import Box from '../components/atoms/Box';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { boardDelete, boardDetail } from '../services/board';
import BoardWrapper from '../components/organisms/BoardWrapper';
import Button from '../components/atoms/Button';
import Text from '../components/atoms/Text';
import { validateResponse } from '../utils/validateResponse';
import REQUIRED from '../constants/REQUIRED';
import { useQuery } from '@tanstack/react-query';
import { useNavigate } from 'react-router-dom';
import ROUTES from '../constants/ROUTES';

const BoardPage = () => {
    const { boardId } = useParams();
    const navigate = useNavigate();

    const { data, isLoading, error } = useQuery(
        ['boardDetail'],
        () => boardDetail({ boardId: boardId }),
        {
            onSuccess: (data) => {
                console.log(validateResponse(data, REQUIRED.boardDetail));
            },
        }
    );
    const handleDelete = async () => {
        await boardDelete({ boardId }).then((res) => navigate(ROUTES.home));
    };

    return (
        <Box direction="column" justify="start" className="page" gap="5rem">
            <Text></Text>
            {validateResponse(data, REQUIRED.boardDetail) && (
                <>
                    <BoardWrapper info={data.data.response} />
                    <Box>
                        <Button
                            className="xl"
                            onClick={() => navigate(`${ROUTES.postit}/${boardId}`)}
                        >
                            참여하기
                        </Button>
                        <Button className="xl" onClick={handleDelete}>
                            삭제하기
                        </Button>
                    </Box>
                </>
            )}
        </Box>
    );
};

export default BoardPage;
