import Textarea from '../atoms/Textarea';
import Box from '../atoms/Box';
import { styled } from 'styled-components';
import ColorCircle from '../atoms/ColorCircle';
import { useMutation } from '@tanstack/react-query';
import { useEffect, useRef, useState } from 'react';
import { commentCreate } from '../../services/comment';
import FORM_DEFAULT from '../../constants/FORM_DEFAULT';
import { useParams } from 'react-router-dom';
import { AiOutlineSend } from 'react-icons/ai';

const PostitCreateItem = ({ position }) => {
    const { boardId } = useParams();
    const colors = ['white', 'yellow', 'green', 'blue'];
    const [values, setValues] = useState(FORM_DEFAULT.COMMENT);
    const { mutate } = useMutation({
        mutationFn: commentCreate,
    });

    useEffect(() => {
        setValues((prev) => ({ ...prev, boardId: boardId, position: position, color: 'white' }));
    }, []);

    const handleChangeColor = (color) => {
        setValues((prev) => ({
            ...prev,
            color: color,
        }));
    };

    const handleChangeContent = (e) => {
        setValues((prev) => ({
            ...prev,
            content: e.target.value,
        }));
    };

    const handleSave = () => {
        mutate(values, {
            onSuccess: () => {
                setValues((prev) => ({
                    ...prev,
                    content: '',
                }));
                alert('댓글이 정상적으로 작성됐습니다.');
            },
            onError: () => {
                alert('댓글 작성에 실패했습니다');
            },
        });
    };

    return (
        <>
            <StyledDiv>
                <Textarea
                    id={values.boardId}
                    rows={5}
                    cols={10}
                    onChange={handleChangeContent}
                    value={values.content}
                    className={`postit ${values.color}`}
                ></Textarea>
                <Box width="95%" justify="space-between">
                    <Box>
                        {colors.map((color, idx) => {
                            if (color === values.color) {
                                return (
                                    <ColorCircle
                                        key={idx}
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
                    <AiOutlineSend onClick={handleSave} size={'1.25rem'} />
                </Box>
            </StyledDiv>
        </>
    );
};

const StyledDiv = styled.div`
    display: flex;
    gap: 1rem;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 70%;
    margin: ${({ theme }) => theme.margin.small} 0;

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
`;

export default PostitCreateItem;
