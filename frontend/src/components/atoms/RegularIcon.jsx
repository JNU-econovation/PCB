import {
    faHeart,
    faPenToSquare,
    faTrash,
    faSquareCheck,
    faSquareXmark,
} from '@fortawesome/free-regular-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { styled } from 'styled-components';

const icons = {
    heart: faHeart,
    edit: faPenToSquare,
    remove: faTrash,
    check: faSquareCheck,
    x: faSquareXmark,
};

const RegularIcon = ({ name, size = '' }) => {
    if (size == 'sm') {
        return <FontAwesomeIcon icon={icons[name]} size="xs" onClick={onClick} />;
    } else if (size == 'lg') {
        return <FontAwesomeIcon icon={icons[name]} size="lg" onClick={onClick} />;
    } else {
        return <FontAwesomeIcon icon={icons[name]} onClick={onClick} />;
    }
};

export default RegularIcon;
