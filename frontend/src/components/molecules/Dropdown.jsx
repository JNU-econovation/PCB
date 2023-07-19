import { styled } from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faAngleUp } from '@fortawesome/free-solid-svg-icons';
import { faAngleDown } from '@fortawesome/free-solid-svg-icons';
import DropdownList from '../atoms/DropdownList';
import { useAtomValue } from 'jotai';
import { userNicknameAtom } from '../../store/login';
import Text from '../atoms/Text';

const Dropdown = ({ view, setView, className = '' }) => {
    const username = useAtomValue(userNicknameAtom);
    return (
        <StyledUl
            className={view ? `viewed ${className}` : className}
            onClick={() => setView(!view)}
        >
            <Text className="dropdown">
                반가워요, {username} 님! {'   '}
                {view ? (
                    <FontAwesomeIcon icon={faAngleUp} />
                ) : (
                    <FontAwesomeIcon icon={faAngleDown} />
                )}
            </Text>
            {view && <DropdownList />}
        </StyledUl>
    );
};

const StyledUl = styled.ul`
    margin-top: 0.25rem;
    max-height: 0rem;
    font-weight: 600;

    & > li {
        font-weight: 500;
        ${({ theme }) => theme.location.flex()}
        margin: ${({ theme }) => theme.margin.small} 0;
        height: 3rem;
        background-color: ${({ theme }) => theme.color.white};
        border: 1px solid ${({ theme }) => theme.color.blue};
    }
`;
export default Dropdown;
