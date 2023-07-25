import { styled } from 'styled-components';
import { Draggable } from 'react-beautiful-dnd';
import PostitItem from './PostitItem';
import PostitEditItem from './PostitEditItem';

const PostitColumn = ({ comments, setComments, position }) => {
    return (
        <>
            {comments.map((comment, index) => (
                <Draggable
                    key={`${comment.commentId}`}
                    draggableId={`${comment.commentId}`}
                    index={index}
                >
                    {(provided) => (
                        <div
                            ref={provided.innerRef}
                            {...provided.draggableProps}
                            {...provided.dragHandleProps}
                        >
                            <PostitItem
                                comment={comment}
                                setComments={setComments}
                                position={position}
                            ></PostitItem>
                        </div>
                    )}
                </Draggable>
            ))}
        </>
    );
};

const StyledButton = styled.button`
    padding: ${({ theme }) => theme.padding.lg};
    width: 23rem;

    /* Layout */

    font-size: ${({ theme }) => theme.fontSize.lg};
    font-weight: 600;
    border-radius: ${({ theme }) => theme.border.rad_base};

    background-color: ${({ theme }) => theme.color.white};
    color: ${({ theme }) => theme.color.black};
    box-shadow: 0px 16px 32px 0px rgba(0, 0, 0, 0.08), 0px 4px 8px 0px rgba(0, 0, 0, 0.12);
    outline: none;

    &:active {
        background-color: ${({ theme }) => theme.color.light_gray};
        border-color: ${({ theme }) => theme.color.light_gray};
    }

    &:hover {
        background-color: ${({ theme }) => theme.color.white};
        border-color: ${({ theme }) => theme.color.white};
    }
`;

export default PostitColumn;
