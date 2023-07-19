import { faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useAtomValue } from 'jotai';
import { commentAtom } from '../../store/comment';
import { userNicknameAtom } from '../../store/login';
import Text from '../atoms/Text';
import Box from '../atoms/Box';
import PostitUI from '../atoms/PostitUI';
import PostitWrapper from '../organisms/PostitWrapper';
import { styled } from 'styled-components';

const PostitItem = () => {
    const userNickname = useAtomValue(userNicknameAtom);
    const comments = useAtomValue(commentAtom);
    return (
        <PostitWrapper>
            {comments.map((comment) => {
                return (
                    <PostitUI className={comment.color}>
                        <StyledDiv justify="start" align="start">
                            <Text className="small">{comment.content}</Text>
                        </StyledDiv>
                        <Box justify="space-between">
                            <Text className="small">{comment.nickname}</Text>
                            {userNickname === comment.nickname && (
                                <Box>
                                    <FontAwesomeIcon
                                        icon={faPenToSquare}
                                        size="sm"
                                    ></FontAwesomeIcon>
                                    <FontAwesomeIcon icon={faTrash} size="sm"></FontAwesomeIcon>
                                </Box>
                            )}
                        </Box>
                    </PostitUI>
                );
            })}
        </PostitWrapper>
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
    &:hover,
    &:focus {
        visibility: visible;
    }
`;

export default PostitItem;
