import Textarea from '../atoms/Textarea';
import Text from '../atoms/Text';
import Box from '../atoms/Box';
import { styled } from 'styled-components';
import ColorCircle from '../atoms/ColorCircle';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useMutation } from '@tanstack/react-query';
import { faFloppyDisk } from '@fortawesome/free-regular-svg-icons';
import { faMinusSquare } from '@fortawesome/free-regular-svg-icons';
import { useEffect, useRef, useState } from 'react';
import { commentContentUpdate } from '../../services/comment';

const PostitEditItem = ({ comment, setComments, position, setOnEdit }) => {
    const colors = ['white', 'yellow', 'green', 'blue'];
    const [payload, usePayload] = useState({});
    const initState = useRef({});
    const { mutate } = useMutation({
        mutationFn: commentContentUpdate,
    });

    useEffect(() => {
        initState.current = comment;
    }, []);

    const handleChangeColor = (color) => {
        setComments((prev) => ({
            ...prev,
            [position]: prev[position].map((com) =>
                com.commentId === comment.commentId ? { ...com, color: color } : com
            ),
        }));
    };

    const handleChangeContent = (e) => {
        setComments((prev) => ({
            ...prev,
            [position]: prev[position].map((com) =>
                com.commentId === comment.commentId ? { ...com, content: e.target.value } : com
            ),
        }));
    };

    const handleSave = () => {
        mutate(
            {
                updateContentList: [
                    {
                        commentId: comment.commentId,
                        color: comment.color,
                        content: comment.content,
                    },
                ],
            },
            {
                onSuccess: () => {
                    alert('저장되었습니다.');
                    setOnEdit(false);
                },
                onError: () => {
                    alert('저장에 실패했습니다');
                    setComments((prev) => ({
                        ...prev,
                        [position]: prev[position].map((com) =>
                            com.commentId === comment.commentId ? initState.current : com
                        ),
                    }));
                    setOnEdit(false);
                },
            }
        );
    };
    const handleCancel = () => {
        setComments((prev) => ({
            ...prev,
            [position]: prev[position].map((com) =>
                com.commentId === comment.commentId ? initState.current : com
            ),
        }));
        setOnEdit(false);
    };

    return (
        <>
            <StyledDiv>
                <Textarea
                    id={comment.commentId}
                    rows={5}
                    cols={10}
                    onChange={handleChangeContent}
                    value={comment.content}
                    className="postit"
                ></Textarea>
            </StyledDiv>
            <Box width="95%" justify="space-between">
                <Box>
                    <Text className="small">{comment.nickname}</Text>
                    {colors.map((color) => {
                        if (color === comment.color) {
                            return (
                                <ColorCircle
                                    className={color}
                                    onClick={() => handleChangeColor(color)}
                                >
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        width="13"
                                        height="12"
                                        viewBox="0 0 13 12"
                                        fill="none"
                                    >
                                        <path
                                            d="M10.2 3L4.69995 8.5L2.19995 6"
                                            stroke="#BEB496"
                                            strokeLinecap="round"
                                            strokeLinejoin="round"
                                        />
                                    </svg>
                                </ColorCircle>
                            );
                        }
                        return (
                            <ColorCircle
                                className={color}
                                onClick={() => handleChangeColor(color)}
                            />
                        );
                    })}
                </Box>
                <Box gap="1rem">
                    <FontAwesomeIcon icon={faFloppyDisk} size={'lg'} onClick={handleSave} />
                    <FontAwesomeIcon icon={faMinusSquare} size={'lg'} onClick={handleCancel} />
                </Box>
            </Box>
        </>
    );
};

const StyledDiv = styled.div`
    display: flex;
    align-items: flex-start;
    justify-content: start;
    width: 100%;
    height: 8rem;

    overflow-y: scroll;
    visibility: hidden;

    &::-webkit-scrollbar {
        width: 0.5rem;
    }

    &::-webkit-scrollbar-thumb {
        height: 30%;
        background: ${({ theme }) => theme.color.scroll};

        border-radius: ${({ theme }) => theme.border.rad_base};
    }

    &::-webkit-scrollbar-track {
        background: ${({ theme }) => theme.color.scroll_background}; /*스크롤바 뒷 배경 색상*/
    }

    & > p,
    & > textarea,
    &:hover,
    &:focus {
        visibility: visible;
    }
`;

export default PostitEditItem;
