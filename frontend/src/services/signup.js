import { instance } from './api';

export const signup = (data) => {
    const { uid, pwd, nickname } = data;
    return instance.post('/member/create', { uid: uid, pwd: pwd, nickname: nickname });
};

export const uidCheck = (data) => {
    const { uid } = data;
    return instance.post('/member/uid-check', { uid: uid });
};

export const nicknameCheck = (data) => {
    const { nickname } = data;
    return instance.post('/member/nickname-check', { nickname: nickname });
};
