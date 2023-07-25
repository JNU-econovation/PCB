import axios from 'axios';
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

export const boardUpdate = (data) => {
    const { boardId, title, content, boardTagList } = data;
    return instance.put(`/board/update/${boardId}`, { boardId, title, content, boardTagList });
};

export const boardDelete = (data) => {
    const { boardId } = data;
    return instance.delete(`/board/delete/${boardId}`);
};
