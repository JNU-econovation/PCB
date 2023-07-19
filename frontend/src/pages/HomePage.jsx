import { useAtom, useAtomValue, useSetAtom } from 'jotai';
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
    const setMainPostList = useSetAtom(mainPostListAtom);
    const mainPostList = useAtomValue(mainPostListAtom);
    const [isNext, setIsNext] = useState(true);
    const [ref, inView] = useInView();

    useEffect(() => {
        fetchInitPostList();
    }, []);

    useEffect(() => {
        //console.log(mainPostList);
    }, [mainPostList]);

    useEffect(() => {
        if (inView) {
            console.log('마지막');
            fetchPostList();
        }
    }, [inView]);

    const fetchInitPostList = async () => {
        try {
            await home({ lastBoardId: '', limit: 9 })
                .then((response) => {
                    setMainPostList((prev) => [
                        ...prev,
                        ...response.data.content.filter((v) => v !== undefined),
                    ]);
                    setIsNext(response.data.last);
                })
                .catch((error) => console.error(error));
        } catch (err) {
            console.log(err);
        }
    };

    const fetchPostList = async () => {
        if (!isNext) {
            console.log(mainPostList[mainPostList.length - 1]);
            await home({ lastBoardId: mainPostList[mainPostList.length - 1].boardId, limit: 9 })
                .then((response) => {
                    setMainPostList((prev) => [
                        ...prev,
                        ...response.data.content.filter((v) => v !== undefined),
                    ]);
                    setIsLast(response.data.last);
                })
                .catch((error) => console.error(error));
        }
    };

    return (
        <Box className="column page">
            {isLogin ? <HomeHeader /> : <InitHeader />}
            <PostItemsWrapper />
            {mainPostList.length !== 0 && <div ref={ref}>마지막</div>}
        </Box>
    );
};

export default HomePage;
