import { styled } from 'styled-components';
import { commentAtom } from '../../store/comment';
import { useAtom } from 'jotai';
import { DragDropContext, Droppable } from 'react-beautiful-dnd';
import PostitColumn from '../molecules/PostitColumn';
import { useEffect } from 'react';
import { useMutation } from '@tanstack/react-query';
import { commentPositionUpdate } from '../../services/comment';
import { arrangeArray } from '../../utils/arrangeArray';
import PostitCreateItem from '../molecules/PostitCreateItem';

const PostitWrapper = ({ data }) => {
    const [comments, setComments] = useAtom(commentAtom);

    useEffect(() => {
        const { boardCommentList } = data?.data?.response;
        setComments({
            left: arrangeArray(boardCommentList.filter((item) => item.position === 'left')),
            right: arrangeArray(boardCommentList.filter((item) => item.position === 'right')),
        });
    }, [data]);

    const onDragEnd = ({ source, destination }) => {
        // 유효하지 않은 곳으로 drag 시 이벤트 종료
        if (!destination) return;

        const sourceKey = source.droppableId;
        const sourceIdx = source.index;
        const destKey = destination.droppableId;
        const destIdx = destination.index;

        if (sourceKey === destKey && sourceIdx === destIdx) return;

        // comment update 시 보낼 payload
        const updatePayload = { updatePositionList: [] };

        // 깊은 복사
        const items = JSON.parse(JSON.stringify(comments));

        /* position update */
        if (sourceKey !== destKey) {
            items[sourceKey][sourceIdx].position = destKey;
        }

        /* after update 시 필요한 id 값 추출 */
        const moveId = items[sourceKey][sourceIdx].commentId;

        const sourcePrevId = items[sourceKey][sourceIdx - 1]
            ? items[sourceKey][sourceIdx - 1].commentId
            : null;

        const sourceNextId = items[sourceKey][sourceIdx + 1]
            ? items[sourceKey][sourceIdx + 1].commentId
            : -1;

        let destPrevId;
        if (sourceKey === destKey && sourceIdx + 1 === destIdx) {
            destPrevId = sourceNextId;
        } else {
            destPrevId = items[destKey][destIdx - 1] ? items[destKey][destIdx - 1].commentId : null;
        }

        console.log(destPrevId);

        let destNextId;
        if (sourceKey === destKey && sourceIdx + 1 === destIdx) {
            destNextId = items[destKey][destIdx + 1] ? items[destKey][destIdx + 1].commentId : -1;
        } else {
            destNextId = items[destKey][destIdx] ? items[destKey][destIdx].commentId : -1;
        }

        /* after update */
        if (sourcePrevId !== null) {
            // sourcePrev의 after update
            // 출발한 곳이 가장 앞이 아니라면?
            console.log('?');
            items[sourceKey][sourceIdx - 1].after = sourceNextId;
            updatePayload.updatePositionList.push({
                commentId: sourcePrevId,
                after: sourceNextId,
                position: sourceKey,
            });
        }

        // destPrev의 after update
        if (destPrevId !== null) {
            // 도착한 곳이 가장 앞이 아니라면?
            items[destKey][destIdx - 1].after = moveId;
            updatePayload.updatePositionList.push({
                commentId: destPrevId,
                after: moveId,
                position: destKey,
            });
        }

        // move의 after update
        items[sourceKey][sourceIdx].after = destNextId;
        updatePayload.updatePositionList.push({
            commentId: moveId,
            after: destNextId,
            position: destKey,
        });

        /* Array update */
        // drag를 시작한 리스트에서 drag한 아이템 빼기
        const [reorderedItem] = items[sourceKey].splice(sourceIdx, 1);
        // drop 되는 리스트에 알맞는 위치에 아이템 추가
        items[destKey].splice(destIdx, 0, reorderedItem);

        setComments(items);
        console.log(updatePayload);

        mutate(updatePayload, {
            onError: (error) => alert(error),
        });
    };

    const { mutate } = useMutation({
        mutationFn: commentPositionUpdate,
    });

    return (
        <DragDropContext onDragEnd={onDragEnd}>
            <PostitCreateItem position={'left'} />
            <StyledWrapper>
                {Object.keys(comments).map((key) => (
                    <Droppable key={key} droppableId={key}>
                        {(provided) => (
                            <div
                                className="postit-column"
                                ref={provided.innerRef}
                                {...provided.droppableProps}
                            >
                                <PostitColumn
                                    position={key}
                                    comments={comments[key]}
                                    setComments={setComments}
                                />
                                {provided.placeholder}
                            </div>
                        )}
                    </Droppable>
                ))}
            </StyledWrapper>
        </DragDropContext>
    );
};

const StyledWrapper = styled.div`
    height: 100%;
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-gap: 1.5rem;
    align-items: center;
    justify-items: center;
    margin: ${({ theme }) => theme.margin.lg};
    padding: 1.5rem;
    background-color: ${({ theme }) => theme.color.soft_white};
    width: 55rem;
    @media screen and (max-width: 900px) {
        grid-template-columns: 1fr;
        width: 27rem;
    }
`;

export default PostitWrapper;
