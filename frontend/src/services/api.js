import axios from 'axios';

export const instance = axios.create({
    baseURL: import.meta.env.VITE_API_URL,
    timeout: 1000 * 3,
    headers: {
        'Content-Type': 'application/json',
    },
    withCredentials: true,
});

instance.interceptors.request.use((config) => {
    return config;
});

instance.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        if (!!error?.response?.data?.error) {
            const err = error.response.data.error;
            if (err.message === 'Please login First') {
                alert('로그인이 필요한 서비스 입니다');
                localStorage.removeItem('sessionId');
            }
        }
        return Promise.reject(error);
    }
);

export const home = (data) => {
    const { lastBoardId, limit } = data;
    return instance.get('./api/main', { params: { lastBoardId: lastBoardId, limit: limit } });
};

export const search = (data) => {
    const { lastBoardId, limit, tag } = data;
    return instance.get('/board/search', {
        params: { lastBoardId: lastBoardId, limit: limit, tag: tag },
    });
};

export const login = (data) => {
    const { uid, pwd } = data;
    return instance.post('./member/login', { uid: uid, pwd: pwd });
};

export const logout = () => {
    return instance.post('./member/logout');
};
