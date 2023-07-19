import { useSetAtom } from 'jotai';
import { useNavigate } from 'react-router-dom';
import { logout } from '../../services/api';
import { isLoginAtom } from '../../store/login';

const DropdownList = () => {
    const navigate = useNavigate();
    const setIsLogin = useSetAtom(isLoginAtom);
    return (
        <>
            <li onClick={() => navigate('/create')}>새 글쓰기</li>
            <li onClick={() => navigate('/mypage')}>마이페이지</li>
            <li
                onClick={async () => {
                    await logout()
                        .then(() => {
                            setIsLogin(false);
                            navigate('/');
                        })
                        .catch((err) => alert(err));
                }}
            >
                로그아웃
            </li>
        </>
    );
};

export default DropdownList;
