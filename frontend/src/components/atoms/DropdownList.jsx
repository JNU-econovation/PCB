import { useSetAtom } from 'jotai';
import { useNavigate } from 'react-router-dom';
import { logout } from '../../services/api';
import { sessionIdAtom, userNicknameAtom } from '../../store/login';
import URL from '../../constants/ROUTES';

const DropdownList = () => {
    const navigate = useNavigate();
    const setSessionId = useSetAtom(sessionIdAtom);
    const setNickname = useSetAtom(userNicknameAtom);
    return (
        <>
            <li onClick={() => navigate('/create')}>새 글쓰기</li>
            <li onClick={() => navigate('/mypage')}>마이페이지</li>
            <li
                onClick={async () => {
                    await logout()
                        .then(() => {
                            setSessionId('');
                            setNickname('');
                            navigate('/');
                        })
                        .catch((err) => alert(err));
                }}
            >
                로그아웃
            </li>
            <li
                onClick={() => {
                    setSessionId('');
                    setNickname('');
                    navigate(URL.home);
                }}
            >
                테스트 로그아웃
            </li>
            <li
                onClick={() => {
                    navigate(`${URL.postit}/1`);
                }}
            >
                포스트잇 페이지
            </li>
        </>
    );
};

export default DropdownList;
