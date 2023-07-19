import { useAtomValue, useSetAtom } from 'jotai';
import PostItemsWrapper from '../components/organisms/PostItemsWrapper';
import Box from '../components/atoms/Box';
import { mainPostListAtom } from '../store/board';
import { useInView } from 'react-intersection-observer';
import { useEffect, useState } from 'react';
import { home } from '../services/api';
import Heading from '../components/atoms/Heading';

const HomePage = () => {
    const setMainPostList = useSetAtom(mainPostListAtom);
    const mainPostList = useAtomValue(mainPostListAtom);
    const [isNext, setIsNext] = useState(true);
    const [ref, inView] = useInView();

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
        <Box direction="column" className="page">
            <PostItemsWrapper />
            {mainPostList.length !== 0 && <div ref={ref}>마지막</div>}
        </Box>
    );
};

export default HomePage;
