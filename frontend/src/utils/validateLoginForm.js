import FORM_INFO from '../constants/FORM_INFO';

const info = FORM_INFO.LOGIN;

const validateLoginForm = ({ uid, pwd }) => {
    const errors = {};

    const uidValidation = info.find((e) => e.id === 'uid').validation;
    const pwdValidation = info.find((e) => e.id === 'pwd').validation;

    if (!uid) {
        errors.uid = uidValidation.required.message;
    }

    // password
    if (!pwd) {
        errors.pwd = pwdValidation.required.message;
    }

    return errors;
};

export default validateLoginForm;
