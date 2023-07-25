import Box from '../components/atoms/Box';
import PostitWrapper from '../components/organisms/PostitWrapper';
import BoardInfo from '../components/organisms/BoardInfo';
import { useQuery } from '@tanstack/react-query';
import { participate } from '../services/comment';
import { useParams } from 'react-router-dom';
import Text from '../components/atoms/Text';
import { validateResponse } from '../utils/validateResponse';
import REQUIRED from '../constants/REQUIRED';

const data = {
    data: {
        success: true,
        response: {
            boardId: 1,
            title: 'Postit Page Not Connecting',
            boardCommentList: [
                {
                    commentId: 2,
                    position: 'right',
                    after: 4,
                    color: 'white',
                    content:
                        '언론·출판에 대한 허가나 검열과 집회·결사에 대한 허가는 인정되지 아니한다. 대통령은 내우·외환·천재·지변 또는 중대한 재정·경제상의 위기에 있어서 국가의 안전보장 또는 공공의 안녕질서를 유지하기 위하여 긴급한 조치가 필요하고 국회의 집회를 기다릴 여유가 없을 때에 한하여 최소한으로 필요한 재정·경제상의 처분을 하거나 이에 관하여 법률의 효력을 가지는 명령을 발할 수 있다.',
                    nickname: '또리',
                },
                {
                    commentId: 3,
                    position: 'left',
                    after: 5,
                    color: 'yellow',
                    content:
                        '군인·군무원·경찰공무원 기타 법률이 정하는 자가 전투·훈련등 직무집행과 관련하여 받은 손해에 대하여는 법률이 정하는 보상외에 국가 또는 공공단체에 공무원의 직무상 불법행위로 인한 배상은 청구할 수 없다.',
                    nickname: '이든',
                },
                {
                    commentId: 4,
                    position: 'right',
                    after: 6,
                    color: 'green',
                    content:
                        '국회는 법률에 저촉되지 아니하는 범위안에서 의사와 내부규율에 관한 규칙을 제정할 수 있다. 국토와 자원은 국가의 보호를 받으며, 국가는 그 균형있는 개발과 이용을 위하여 필요한 계획을 수립한다.',
                    nickname: '바다1',
                },
                {
                    commentId: 5,
                    position: 'left',
                    after: -1,
                    color: 'white',
                    content:
                        '헌법재판소 재판관의 임기는 6년으로 하며, 법률이 정하는 바에 의하여 연임할 수 있다. 국무위원은 국무총리의 제청으로 대통령이 임명한다. 국가는 주택개발정책등을 통하여 모든 국민이 쾌적한 주거생활을 할 수 있도록 노력하여야 한다.',
                    nickname: '만두',
                },
                {
                    commentId: 6,
                    position: 'right',
                    after: -1,
                    color: 'yellow',
                    content:
                        '연소자의 근로는 특별한 보호를 받는다. 행정각부의 장은 국무위원 중에서 국무총리의 제청으로 대통령이 임명한다. 제안된 헌법개정안은 대통령이 20일 이상의 기간 이를 공고하여야 한다.',
                    nickname: '루피',
                },
            ],
            createdAt: 1689893654,
            nickname: '이진혁11',
        },
        error: null,
    },
};

const PostitPage = () => {
    const { boardId } = useParams();

    const { data, isLoading, error } = useQuery(['postit'], () => participate({ boardId }), {
        onSuccess: (data) => {},
        onError: () => {
            alert(error);
        },
    });

    return (
        <Box direction="column" className="page">
            {/* {isLoading && <h1>로딩</h1>}
            {error && <Text>{error}</Text>} */}
            {validateResponse(data, REQUIRED.participate) && (
                <>
                    <BoardInfo data={data} />
                    <PostitWrapper data={data} />
                </>
            )}
        </Box>
    );
};

export default PostitPage;
