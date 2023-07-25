import { instance } from './api';

export const participate = (data) => {
    const { boardId } = data;
    return instance.get(`/board/participate/${boardId}`);
};

export const commentCreate = (data) => {
    const { boardId, content, position, color } = data;
    return instance.post('/comment/create', { boardId, content, position, color });
};

export const commentPositionUpdate = (data) => {
    const { updatePositionList } = data;
    return instance.post('/comment/update/position', { updatePositionList });
};

export const commentContentUpdate = (data) => {
    const { updateContentList } = data;
    return instance.post('/comment/update/content', { updateContentList });
};

export const commentDelete = (data) => {
    console.log(data);
    const { commentId, updatePositionList } = data;
    return instance.post(`/comment/delete/${commentId}`, { updatePositionList });
};
