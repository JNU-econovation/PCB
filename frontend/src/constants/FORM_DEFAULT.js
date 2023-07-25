const LOGIN = Object.freeze({
    uid: '',
    pwd: '',
});

const SIGNUP = Object.freeze({
    uid: '',
    isUidCheck: false,
    pwd: '',
    pwdCheck: '',
    nickname: '',
    isNicknameCheck: false,
});

const BOARD = Object.freeze({
    title: '',
    content: '',
    boardTagList: [],
});

const COMMENT = Object.freeze({
    boardId: '',
    content: '',
    position: '',
    color: '',
});

export default {
    LOGIN,
    SIGNUP,
    BOARD,
    COMMENT,
};
