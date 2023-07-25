import FORM_INFO from '../constants/FORM_INFO';

const info = FORM_INFO.SIGNUP;

const uidValidation = info.find((e) => e.id === 'uid').validation;
const pwdValidation = info.find((e) => e.id === 'pwd').validation;
const pwdCheckValidation = info.find((e) => e.id === 'pwdCheck').validation;
const nicknameValidation = info.find((e) => e.id === 'nickname').validation;

const validateSignupForm = ({ uid, pwd, pwdCheck, nickname }) => {
    console.log();
    const errors = {};

    // user id
    if (!uid) {
        errors.uid = uidValidation.required.message;
    } else if (!uidValidation.pattern.value.test(uid)) {
        errors.uid = uidValidation.pattern.message;
    }

    // password
    if (!pwd) {
        errors.pwd = pwdValidation.required.message;
    } else if (!pwdValidation.pattern.value.test(pwd)) {
        errors.pwd = pwdValidation.pattern.message;
    }

    // password check
    if (!pwdCheck) {
        errors.pwdCheck = pwdCheckValidation.required.message;
    } else if (pwd !== pwdCheck) {
        errors.pwdCheck = pwdCheckValidation.mismatch.message;
    }

    // password
    if (!nickname) {
        errors.nickname = nicknameValidation.required.message;
    } else if (!nicknameValidation.pattern.value.test(nickname)) {
        errors.nickname = nicknameValidation.pattern.message;
    }

    return errors;
};

export default validateSignupForm;
