import { instance } from './api';

export const boardCreate = (data) => {
    const { title, content, boardTagList } = data;
    return instance.post('/board/create', {
        title: title,
        content: content,
        boardTagList: boardTagList,
    });
};

export const boardDetail = (data) => {
    const { boardId } = data;
    return instance.get(`/board/detail/${boardId}`);
};
