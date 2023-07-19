export const timestampToDate = (createdAt) => {
    return `${new Date(createdAt).getFullYear()}년 ${
        new Date(createdAt).getMonth() + 1
    }월 ${new Date(createdAt).getDate()}일`;
};
