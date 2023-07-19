import { atom } from 'jotai';
import FORM_DEFAULT from '../constants/FORM_DEFAULT';

export const loginAtom = atom(FORM_DEFAULT.LOGIN);
export const isLoginAtom = atom(true);
export const userNicknameAtom = atom('바다');
