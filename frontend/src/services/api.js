import axios from 'axios';

const instance = axios.create({
    baseURL: import.meta.env.VITE_API_URL,
    timeout: 1000 * 3,
    headers: {
        'Content-Type': 'application/json',
    },
});

instance.interceptors.request.use((config) => {
    return config;
});

instance.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {}
);

export const home = (data) => {
    const { lastBoardId, limit } = data;
    return instance.get('./api/main', { params: { lastBoardId: lastBoardId, limit: limit } });
};

export const login = (data) => {
    const { id, password } = data;
    return instance.post('./users/login', { identification: id, pwd: password });
};
