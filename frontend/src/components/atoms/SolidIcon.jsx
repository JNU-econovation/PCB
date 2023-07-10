import { faUser, faAngleLeft, faHeart } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { styled } from 'styled-components';

const icons = {
    user: faUser,
    back: faAngleLeft,
    heart: faHeart,
};

const SolidIcon = ({ name, size = '', onClick, className = '' }) => {
    return (
        <FontAwesomeIcon
            icon={icons[name]}
            onClick={onClick}
            size={size !== '' && size}
            className={className}
        />
    );
};

export default SolidIcon;
