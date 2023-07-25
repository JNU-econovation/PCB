import { useAtom } from 'jotai';
import FORM_INFO from '../../constants/FORM_INFO';
import { signupAtom } from '../../store/signup';
import LabeledInput from '../molecules/LabeledInput';
import Form from '../atoms/Form';
import SubmitButton from '../atoms/SubmitButton';
import { useState } from 'react';
import isEmpty from 'lodash/isEmpty';
import { useNavigate } from 'react-router-dom';
import validateSignupForm from '../../utils/validateSignupForm';
import Button from '../atoms/Button';
import Box from '../atoms/Box';
import LabeledButtonInput from '../molecules/LabeledButtonInput';
import { signup, uidCheck, nicknameCheck } from '../../services/signup';

const uidValidation = FORM_INFO.SIGNUP.uid.validation;
const nicknameValidation = FORM_INFO.SIGNUP.nickname.validation;

const SignupForm = () => {
    const navigate = useNavigate();
    const [values, setValues] = useAtom(signupAtom);
    const [error, setError] = useState({});
    const handleChange = (e) => {
        const { name, value } = e.target;
        setValues({ ...values, [name]: value });
    };
    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!values.isUidCheck) {
            alert('아이디 중복 확인을 해주세요'); // 여기 메세지들도 constant로 만들어~
            return;
        }
        if (!values.isNicknameCheck) {
            alert('닉네임 중복 확인을 해주세요'); // 여기 메세지들도 constant로 만들어~
            return;
        }

        const validationError = validateSignupForm({
            uid: values.uid,
            pwd: values.pwd,
            pwdCheck: values.pwdCheck,
            nickname: values.nickname,
        });
        setError(validationError);

        if (!isEmpty(validationError)) {
            return;
        }
        await signup({ uid: values.uid, pwd: values.pwd, nickname: values.nickname })
            .then((res) => {
                navigate('/login');
            })
            .catch((err) => alert(err));
    };

    const handleUidCheck = async () => {
        try {
            await uidCheck({ uid: values.uid });
            setValues((prev) => ({ ...prev, isUidCheck: true }));
        } catch (error) {
            setValues((prev) => ({ ...prev, isUidCheck: false }));
            setError({ ...error, uid: uidValidation.checkUrl.message });
        }
    };

    const handleNicknameCheck = async () => {
        try {
            await nicknameCheck({ nickname: values.nickname });
            setValues((prev) => ({ ...prev, isNicknameCheck: true }));
        } catch (error) {
            setValues((prev) => ({ ...prev, isNicknameCheck: false }));
            setError({ ...error, nickname: nicknameValidation.checkUrl.message });
        }
    };

    return (
        <Form onSubmit={handleSubmit}>
            <LabeledButtonInput
                key={FORM_INFO.SIGNUP.uid.id}
                id={FORM_INFO.SIGNUP.uid.id}
                type={FORM_INFO.SIGNUP.uid.type}
                value={values.uid}
                onChange={handleChange}
                label={FORM_INFO.SIGNUP.uid.label}
                placeholder={FORM_INFO.SIGNUP.uid.placeholder}
                required={false}
                btn={'중복 확인'}
                onClick={handleUidCheck}
                errorMessage={error[FORM_INFO.SIGNUP.uid.id] ?? ''}
            />
            <LabeledInput
                key={FORM_INFO.SIGNUP.pwd.id}
                id={FORM_INFO.SIGNUP.pwd.id}
                type={FORM_INFO.SIGNUP.pwd.type}
                value={values.pwd}
                onChange={handleChange}
                label={FORM_INFO.SIGNUP.pwd.label}
                placeholder={FORM_INFO.SIGNUP.pwd.placeholder}
                required={false}
                className="lg"
                errorMessage={error[FORM_INFO.SIGNUP.pwd.id] ?? ''}
            />
            <LabeledInput
                key={FORM_INFO.SIGNUP.pwdCheck.id}
                id={FORM_INFO.SIGNUP.pwdCheck.id}
                type={FORM_INFO.SIGNUP.pwdCheck.type}
                value={values.pwdCheck}
                onChange={handleChange}
                label={FORM_INFO.SIGNUP.pwdCheck.label}
                placeholder={FORM_INFO.SIGNUP.pwdCheck.placeholder}
                required={false}
                className="lg"
                errorMessage={error[FORM_INFO.SIGNUP.pwdCheck.id] ?? ''}
            />
            <LabeledButtonInput
                key={FORM_INFO.SIGNUP.nickname.id}
                id={FORM_INFO.SIGNUP.nickname.id}
                type={FORM_INFO.SIGNUP.nickname.type}
                value={values.nickname}
                onChange={handleChange}
                label={FORM_INFO.SIGNUP.nickname.label}
                placeholder={FORM_INFO.SIGNUP.nickname.placeholder}
                required={false}
                btn={'중복 확인'}
                onClick={handleNicknameCheck}
                errorMessage={error[FORM_INFO.SIGNUP.nickname.id] ?? ''}
            />
            <SubmitButton className="xl">회원가입</SubmitButton>
        </Form>
    );
};

export default SignupForm;
