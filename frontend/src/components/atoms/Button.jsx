import StyledButton from './StyledButton';

const Button = ({ children, onClick, className = '' }) => {
    return (
        <StyledButton type="button" onClick={onClick} className={className}>
            {children}
        </StyledButton>
    );
};

export default Button;
