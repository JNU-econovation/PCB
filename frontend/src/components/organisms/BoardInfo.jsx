import { useAtom } from 'jotai';
import { boardAtom } from '../../store/comment';
import { timestampToDate } from '../../utils/convert';
import Tag from '../atoms/Tag';
import Box from '../atoms/Box';
import Text from '../atoms/Text';
import { styled } from 'styled-components';
import { useEffect } from 'react';

const BoardInfo = ({ data }) => {
    const [board, setBoard] = useAtom(boardAtom);

    useEffect(() => {
        const { boardId, title, createdAt, nickname } = data.data.response;
        setBoard({ boardId, title, createdAt, nickname });
    }, [data]);

    return (
        <StyledDiv direction="column" align="start">
            <Text className="title">{board.title}</Text>
            <Box>
                <Text className="wg_600">{board.nickname}</Text>
                <Text>{timestampToDate(board.createdAt)}</Text>
            </Box>
        </StyledDiv>
    );
};

const StyledDiv = styled.div`
    ${({ theme }) => theme.location.flex('column', 'start', 'center')}
    width: 70%;
    margin: ${({ theme }) => theme.margin.base} 0;
`;

export default BoardInfo;
