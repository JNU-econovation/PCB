import { styled } from "styled-components";
import Button from "./button";
import search_icon from "../../assets/search-icon.png"

const Container = styled.div`
    display: flex;
    justify-content: space-around;

    width: 100%;

    padding: 1rem;

    border-bottom: 1px solid ${props => props.theme.color.lightGray};

    background-color: ${({theme}) => theme.color.white};

    position: sticky;

`

const StyledInput = styled.input`
    border: none;

    width: 80%;
    height: 100%;

    color: ${({theme}) => theme.color.darkGray};

    &:focus {
        outline: none;
    }

    &::placeholder {
        color: ${({theme}) => theme.color.gray};
    }
`

const SearchForm = styled.form`
    display: flex;
    flex-grow: 0.8;

    height: 2.75rem;
    padding: 0.5rem 1rem;
    

    border: ${({theme}) => theme.border.search};
    border-radius: ${({theme}) => theme.border.round16};
`

const StyledIcon = styled.img`
    height: 1.5rem;

    margin-right: 1rem;
`


const Header = () => {
    return (<Container>
        <SearchForm action="">
            <div><StyledIcon src={search_icon} alt="search" /></div>
            <StyledInput type="text" placeholder="Search" />
        </SearchForm>
        <div>
            <Button color="white" size="small" innerText="Sign up" />
            <Button color="blue" size="small" innerText="Log In" />
        </div>
    </Container>);
}

export default Header;