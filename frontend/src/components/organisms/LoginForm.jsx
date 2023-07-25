import { useAtom } from 'jotai';
import FORM_INFO from '../../constants/FORM_INFO';
import { isLoginAtom, loginAtom, sessionIdAtom, userNicknameAtom } from '../../store/login';
import LabeledInput from '../molecules/LabeledInput';
import Form from '../atoms/Form';
import SubmitButton from '../atoms/SubmitButton';
import { useState } from 'react';
import { login } from '../../services/api';
import _ from 'lodash';
import { useNavigate } from 'react-router-dom';
import validateLoginForm from '../../utils/validateLoginForm';
import URL from '../../constants/ROUTES';

const LoginForm = () => {
    const [sessionId, setSessionId] = useAtom(sessionIdAtom);
    const [nickname, setNickName] = useAtom(userNicknameAtom);
    const navigate = useNavigate();
    const [values, setValues] = useAtom(loginAtom);
    const [error, setError] = useState({});
    const handleChange = (e) => {
        const { name, value } = e.target;
        setValues({ ...values, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const validationError = validateLoginForm({ uid: values.uid, pwd: values.pwd });
        setError(validationError);
        if (_.isEmpty(validationError)) {
            await login({ uid: values.uid, pwd: values.pwd })
                .then((res) => {
                    setSessionId(res?.data?.response?.sessionId);
                    setNickName(res?.data?.response?.nickname);
                    navigate(URL.home);
                })
                .catch((err) => alert(err));
        }
    };

    // const handleSubmit = (e) => {
    //     e.preventDefault();
    //     const validationError = validateLoginForm({ uid: values.uid, pwd: values.pwd });
    //     setError(validationError);
    //     if (_.isEmpty(validationError)) {
    //         setSessionId('F9B388F83AB8AB5D54A2F953C5A56539');
    //         setNickName('바다');
    //         navigate(URL.home);
    //     }
    // };

    return (
        <Form onSubmit={handleSubmit}>
            {FORM_INFO.LOGIN.map((info) => (
                <LabeledInput
                    key={info.id}
                    id={info.id}
                    type={info.type}
                    value={values.id}
                    onChange={handleChange}
                    label={info.label}
                    placeholder={info.placeholder}
                    required={false}
                    errorMessage={error[info.id] ?? ''}
                />
            ))}
            <SubmitButton className="lg">로그인</SubmitButton>
        </Form>
    );
};

export default LoginForm;
