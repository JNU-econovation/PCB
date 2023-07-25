export const timestampToDate = (createdAt) => {
    return `${new Date(createdAt * 1000).getFullYear()}년 ${
        new Date(createdAt * 1000).getMonth() + 1
    }월 ${new Date(createdAt * 1000).getDate()}일`;
};
