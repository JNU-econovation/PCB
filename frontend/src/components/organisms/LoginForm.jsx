import { useAtom, useSetAtom } from 'jotai';
import FORM_INFO from '../../constants/FORM_INFO';
import { loginAtom, sessionIdAtom, userNicknameAtom } from '../../store/login';
import LabeledInput from '../molecules/LabeledInput';
import Form from '../atoms/Form';
import SubmitButton from '../atoms/SubmitButton';
import { useState } from 'react';
import { login } from '../../services/api';
import _ from 'lodash';
import { useNavigate } from 'react-router-dom';
import validateLoginForm from '../../utils/validateLoginForm';
import ROUTES from '../../constants/ROUTES';

const LoginForm = () => {
    const setSessionId = useSetAtom(sessionIdAtom);
    const setNickName = useSetAtom(userNicknameAtom);
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
            await login({ uid: values.uid, pwd: values.pwd }).then((res) => {
                if (!res.data.success) return;

                setSessionId(res?.data?.response?.sessionId);
                setNickName(res?.data?.response?.nickname);
                navigate(ROUTES.home);
            });
        }
    };

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
