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
    const { updatePostionList } = data;
    return instance.put('/comment/update/position', { updatePostionList });
};

export const commentContentUpdate = (data) => {
    const { updateContentList } = data;
    return instance.put('/comment/update/content', { updateContentList });
};

export const commentDelete = (data) => {
    const { commentId, parentID } = data;
    return instance.delete('/comment', { params: { commentId, parentID } });
};
