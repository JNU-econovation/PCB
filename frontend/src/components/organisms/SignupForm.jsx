import { useAtom } from 'jotai';
import FORM_INFO from '../../constants/FORM_INFO';
import { signupAtom } from '../../store/signup';
import LabeledInput from '../molecules/LabeledInput';
import Form from '../atoms/Form';
import SubmitButton from '../atoms/SubmitButton';
import { useState } from 'react';
import _ from 'lodash';
import { useNavigate } from 'react-router-dom';
import validateSignupForm from '../../utils/validateSignupForm';
import Button from '../atoms/Button';
import Box from '../atoms/Box';
import { signup, uidCheck, nicknameCheck } from '../../services/signup';

const uidValidation = FORM_INFO.SIGNUP.find((e) => e.id === 'uid').validation;
const nicknameValidation = FORM_INFO.SIGNUP.find((e) => e.id === 'nickname').validation;

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
            alert('아이디 중복 확인을 해주세요');
        } else if (!values.isNicknameCheck) {
            alert('닉네임 중복 확인을 해주세요');
        } else {
            const validationError = validateSignupForm({
                uid: values.uid,
                pwd: values.pwd,
                pwdCheck: values.pwdCheck,
                nickname: values.nickname,
            });
            setError(validationError);
            if (_.isEmpty(validationError)) {
                await signup({ uid: values.uid, pwd: values.pwd, nickname: values.nickname })
                    .then((res) => {
                        navigate('/login');
                    })
                    .catch((err) => alert(err));
            }
        }
    };

    const handleUidCheck = async () => {
        try {
            await uidCheck({ uid: uid });
            setValues((prev) => ({ ...prev, isUidCheck: true }));
        } catch (error) {
            setValues((prev) => ({ ...prev, isUidCheck: false }));
            setError({ ...error, uid: uidValidation.checkUrl.message });
        }
    };

    const handleNicknameCheck = async () => {
        try {
            await nicknameCheck({ nickname: nickname });
            setValues((prev) => ({ ...prev, isNicknameCheck: true }));
        } catch (error) {
            setValues((prev) => ({ ...prev, isNicknameCheck: false }));
            setError({ ...error, nickname: nicknameValidation.checkUrl.message });
        }
    };

    return (
        <Form onSubmit={handleSubmit}>
            {FORM_INFO.SIGNUP.map((info) => (
                <Box align="flex-start">
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
                    {info.id === 'uid' && (
                        <Button className="check white" onClick={handleUidCheck}>
                            중복 확인
                        </Button>
                    )}
                    {info.id === 'nickname' && (
                        <Button className="check white" onClick={handleNicknameCheck}>
                            중복 확인
                        </Button>
                    )}
                </Box>
            ))}
            <SubmitButton className="xl">회원가입</SubmitButton>
        </Form>
    );
};

export default SignupForm;
