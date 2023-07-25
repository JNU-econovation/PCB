import { atom } from 'jotai';
import { atomWithStorage } from 'jotai/utils';
import FORM_DEFAULT from '../constants/FORM_DEFAULT';

export const loginAtom = atom(FORM_DEFAULT.LOGIN);
export const sessionIdAtom = atomWithStorage('sessionId', '');
export const userNicknameAtom = atomWithStorage('nickname', '');
