const LOGIN = Object.freeze([
    {
        id: 'uid',
        label: '아이디',
        type: 'text',
        placeholder: '아이디를 입력하세요',
        validation: {
            required: { message: '아이디를 입력해 주세요' },
        },
    },
    {
        id: 'pwd',
        label: '비밀번호',
        type: 'password',
        placeholder: '비밀번호를 입력하세요',
        validation: {
            required: { message: '비밀번호를 입력해 주세요' },
        },
    },
]);

const SIGNUP = Object.freeze([
    {
        id: 'uid',
        label: '아이디',
        type: 'text',
        placeholder: '숫자와 영어가 포함된 5~20자를 입력하세요',
        validation: {
            required: { message: '아이디를 입력하세요' },
            pattern: {
                value: /^(?=.*[a-zA-Z])(?=.*[0-9]).{5,20}$/,
                message: '숫자와 영어가 포함된 5~20자를 입력하세요',
            },
            checkUrl: {
                message: '사용 불가능한 아이디 입니다.',
            },
        },
    },
    {
        id: 'pwd',
        label: '비밀번호',
        type: 'password',
        placeholder: '숫자와 영어, 특수문자가 포함된 8~20자를 입력하세요',
        validation: {
            required: { message: '비밀번호를 입력하세요' },
            pattern: {
                value: /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/,
                message: '숫자와 영어, 특수문자가 포함된 8~20자를 입력하세요',
            },
        },
    },
    {
        id: 'pwdCheck',
        label: '비밀번호 확인',
        type: 'password',
        placeholder: '비밀번호를 다시 입력하세요',
        validation: {
            required: { message: '비밀번호를 다시 입력하세요' },
            mismatch: { message: '비밀번호가 일치하지 않습니다.' },
        },
    },
    {
        id: 'nickname',
        label: '닉네임',
        type: 'text',
        placeholder: '숫자와 영어, 한글로 이루어진 1~10자를 입력하세요',
        validation: {
            required: { message: '닉네임을 입력하세요' },
            pattern: {
                value: /^[a-zA-Zㄱ-힣\d]{1,10}$/,
                message: '숫자와 영어, 한글로 이루어진 1~10자를 입력하세요',
            },
            checkUrl: {
                message: '사용 불가능한 닉네임 입니다.',
            },
        },
    },
]);

const BOARD = Object.freeze([
    {
        id: 'title',
        label: '제목',
        type: 'text',
        placeholder: '',
    },
    {
        id: 'content',
        label: '설명',
        rows: 15,
        cols: 60,
    },
    { id: 'tag', label: '태그', type: 'text', placeholder: '' },
]);

export default {
    LOGIN,
    SIGNUP,
    BOARD,
};
