import { useAtom, useAtomValue } from 'jotai';
import { isLoginAtom } from '../store/login';
import HomeHeader from '../components/molecules/header/HomeHeader';
import InitHeader from '../components/molecules/header/InitHeader';
import PostItemsWrapper from '../components/organisms/PostItemsWrapper';
import Box from '../components/atoms/Box';
import { mainPostListAtom } from '../store/post';
import { useInView } from 'react-intersection-observer';
import { useEffect, useState } from 'react';
import { home } from '../services/api';

const HomePage = () => {
    const isLogin = useAtomValue(isLoginAtom);
    const [mainPostList, setMainPostList] = useAtom(mainPostListAtom);
    const [isLast, setIsLast] = useState(false);
    const { ref, inView, entry } = useInView();

    useEffect(() => {
        fetchInitPostList();
    }, []);

    useEffect(() => {
        if (inView) {
            fetchPostList();
        }
    }, [inView]);

    const fetchInitPostList = async () => {
        try {
            await home({ lastBoardId: '', limit: 9 })
                .then((response) => {
                    setMainPostList((prev) => [...prev, response.content]);
                    setIsLast(response.last);
                })
                .catch((error) => console.error(error));
        } catch (err) {
            console.log(err);
        }
    };

    const fetchPostList = async () => {
        if (!isLast) {
            await home({ lastBoardId: mainPostList[-1].boardId, limit: 9 })
                .then((response) => {
                    setMainPostList((prev) => [...prev, response.content]);
                    setIsLast(response.last);
                })
                .catch((error) => console.error(error));
        }
    };

    return (
        <Box className="column page">
            {isLogin ? <HomeHeader /> : <InitHeader />}
            <PostItemsWrapper />
            {mainPostList.length !== 0 && <Box ref={ref}></Box>}
        </Box>
    );
};

export default HomePage;
