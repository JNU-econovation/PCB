import { styled } from "styled-components"

const StyledButton = styled.button`
    border: ${({theme}) => theme.border.button};
    border-radius: ${({theme}) => theme.border.round4};
    margin-right: 0.5rem;

    &.small{
        width: 7rem;
        height: 2.75rem;

        padding: 0.5rem 1.25rem;

        font-size: ${({theme}) => theme.font.lg};
        font-weight: ${({theme}) => theme.font.bold};
    }

    &.medium{
        weight: 16.25rem;
        height: 3.7rem;

        font-size: ${({theme}) => theme.font.xl};
        font-weight: ${({theme}) => theme.font.bold};
    }

    &.large{
        weight: 28.125rem;
        height: 3.7rem;

        font-size: ${({theme}) => theme.font.xl};
        font-weight: ${({theme}) => theme.font.bold};
    }

    &.white{
        background-color: ${({theme}) => theme.color.white};
        color: ${({theme}) => theme.color.blue};
        border-color: ${({theme}) => theme.color.white}
    }

    &.blue{
        background-color: ${({theme}) => theme.color.blue};
        color: ${({theme}) => theme.color.white};
    }

    &:hover{
        cursor: pointer;

        background-color: ${({theme}) => theme.color.softBlue};
        color: ${({theme}) => theme.color.white};
        border-color: ${({theme}) => theme.color.softBlue};
    }

    &:active{
        
    }
`

const Button = (props) => {
    return (
        <StyledButton className={`${props.size} ${props.color}`}>{props.innerText}</StyledButton>
    );
}

export default Button;
