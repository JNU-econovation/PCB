import { faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useAtomValue } from 'jotai';
import { userNicknameAtom } from '../../store/login';
import Text from '../atoms/Text';
import Box from '../atoms/Box';
import PostitUI from '../atoms/PostitUI';
import { styled } from 'styled-components';
import { useEffect, useRef, useState } from 'react';
import PostitEditItem from './PostitEditItem';
import { useMutation, useQuery } from '@tanstack/react-query';
import { commentDelete } from '../../services/comment';

const PostitItem = ({ comment, prev, setComments, position, isEdit = false }) => {
    const [onEdit, setOnEdit] = useState(isEdit);
    const userNickname = useAtomValue(userNicknameAtom);
    const deletePayload = useRef({ commentId: null, updatePositionList: [] });

    useEffect(() => {
        deletePayload.current.commentId = comment.commentId;
    }, []);

    const { mutate } = useMutation({
        mutationFn: commentDelete,
    });

    const handleEdit = () => {
        setOnEdit(true);
    };

    const handleDelete = () => {
        console.log(deletePayload);
        if (prev !== -1) {
            deletePayload.current.updatePositionList.push({
                commentId: prev,
                position: comment.position,
                after: comment.after,
            });
        }

        mutate(deletePayload.current, {
            onSuccess: () => {
                alert('정상적으로 삭제되었습니다.');
            },
            onError: () => {
                alert('정상적으로 삭제되지 않았습니다.');
            },
        });
    };

    return (
        <PostitUI className={comment.color}>
            {!onEdit ? (
                <>
                    <StyledDiv justify="start" align="start">
                        <Text className="small">{comment.content}</Text>
                    </StyledDiv>
                    <Box justify="space-between" width="98%">
                        <Text className="small">{comment.nickname}</Text>
                        {userNickname === comment.nickname && (
                            <Box justify="space-between" width="15%">
                                <FontAwesomeIcon
                                    icon={faPenToSquare}
                                    onClick={handleEdit}
                                ></FontAwesomeIcon>
                                <FontAwesomeIcon
                                    icon={faTrash}
                                    onClick={handleDelete}
                                ></FontAwesomeIcon>
                            </Box>
                        )}
                    </Box>
                </>
            ) : (
                <PostitEditItem
                    comment={comment}
                    setComments={setComments}
                    position={position}
                    setOnEdit={setOnEdit}
                />
            )}
        </PostitUI>
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

export default PostitItem;
