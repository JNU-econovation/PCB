import { styled } from 'styled-components';
import { commentAtom } from '../../store/comment';
import { useAtom } from 'jotai';
import { DragDropContext, Draggable, Droppable } from 'react-beautiful-dnd';

const PostitWrapper = ({ children }) => {
    const [postitData, setPostitData] = useAtom(commentAtom);

    const handlePostitChange = ({ source, destination }) => {
        // 유효하지 않은 곳으로 drag 시 이벤트 종료
        if (!destination) return;
        console.log('handlePostitChange!!!');
        // 출발지와 목적지의 status 저장
        const sourceKey = source.droppableId;
        const destinationKey = destination.droppableId;

        // 깊은 복사
        const items = JSON.parse(JSON.stringify(postitData));
        // drag를 시작한 리스트에서 drag한 아이템 빼기
        const [reorderedItem] = items[sourceKey].splice(source.index, 1);
        // drop 되는 리스트에 알맞는 위치에 아이템 추가
        items[destinationKey].splice(destination.index, 0, reorderedItem);
        setPostitData(items);
    };

    return <StyledWrapper>{children}</StyledWrapper>;
};

const StyledWrapper = styled.div`
    display: grid;
    grid-template-columns: repeat(2, 24rem);
    grid-gap: 1.5rem;
    align-items: center;
    justify-items: center;
    margin: ${({ theme }) => theme.margin.lg};
    padding: ${({ theme }) => theme.padding.xxxl} ${({ theme }) => theme.padding.lg};
    background-color: ${({ theme }) => theme.color.soft_white};
    @media screen and (max-width: 900px) {
        grid-template-columns: repeat(1, 1fr);
    }
`;

export default PostitWrapper;
