import StyledButton from './StyledButton';

const SubmitButton = ({ children, className = '' }) => {
    return (
        <StyledButton type="submit" className={className}>
            {children}
        </StyledButton>
    );
};

export default SubmitButton;
