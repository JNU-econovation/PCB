import PostIt from "../../components/postIt";
import PostItem from "../../components/home/postItem";
import logo from "../../images/logo.png"
import Header from "../../components/common/header";
import { styled } from "styled-components";


const Wrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 5rem;

    height: 100%;
    width: 100%;
`

const LogoImg = styled.img`
    height: 3rem;
`

const HomePage = () => {
    const result = [
        {
            "boardId" : 1,
            "title" : "Are there any return exclusions?",
            "content" : "Nam nec accumsan arcu justo magna. Faucibus ut mollis id pellentesque imperdiet aenean amet. Aliquet molestie nisl semper nunc.",
            "tag" : ["dog", "cat", "rabbit"],
            "createdAt": {
				"date": 0,
				"day": 12,
				"hours" : 0,
				"minutes" : 0,
				"month" : 3,
				"nanos" : 0,
				"seconds" : 0,
				"time" : 0,
				"timezoneOffset" : 0,
				"year" : 2023,
			}
        },
        {
            "boardId" : 2,
            "title": "Why do we use it?",
            "content": "Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            "tag": ["cake", "cookie"],
            "createdAt": {
				"date": 0,
				"day": 24,
				"hours" : 0,
				"minutes" : 0,
				"month" : 7,
				"nanos" : 0,
				"seconds" : 0,
				"time" : 0,
				"timezoneOffset" : 0,
				"year" : 2022,
			}
        },
        {
            "boardId" : 3,
            "title": "Are there any return exclusions?",
            "content": "Nam nec accumsan arcu justo magna. Faucibus ut mollis id pellentesque imperdiet aenean amet. Aliquet molestie nisl semper nunc.",
            "tag": ["dog", "cat", "rabbit"],
            "createdAt": {
				"date": 0,
				"day": 4,
				"hours" : 0,
				"minutes" : 0,
				"month" : 8,
				"nanos" : 0,
				"seconds" : 0,
				"time" : 0,
				"timezoneOffset" : 0,
				"year" : 2021,
			},
        },
        {
            "boardId" : 4,
            "title": "Why do we use it?",
            "content": "Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            "tag": ["cake", "cookie"],
            "createdAt": {
				"date": 0,
				"day": 9,
				"hours" : 0,
				"minutes" : 0,
				"month" : 3,
				"nanos" : 0,
				"seconds" : 0,
				"time" : 0,
				"timezoneOffset" : 0,
				"year" : 2023,
			}
        },
        {
            "boardId" : 5,
            "title": "Are there any return exclusions?",
            "content": "Nam nec accumsan arcu justo magna. Faucibus ut mollis id pellentesque imperdiet aenean amet. Aliquet molestie nisl semper nunc.",
            "tag": ["dog", "cat", "rabbit"],
            "createdAt": {
				"date": 0,
				"day": 25,
				"hours" : 0,
				"minutes" : 0,
				"month" : 5,
				"nanos" : 0,
				"seconds" : 0,
				"time" : 0,
				"timezoneOffset" : 0,
				"year" : 2023,
			}
        },
        {
            "boardId" : 6,
            "title": "Why do we use it?",
            "content": "Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            "tag": ["cake", "cookie"],
            "createdAt": {
				"date": 0,
				"day": 7,
				"hours" : 0,
				"minutes" : 0,
				"month" : 11,
				"nanos" : 0,
				"seconds" : 0,
				"time" : 0,
				"timezoneOffset" : 0,
				"year" : 2020,
			}
        }
        
    ]

    return (<Wrapper>
    <Header />
    <div><LogoImg src={logo} alt="pcb logo" /></div>
    <PostItem datas={result}/>
    </Wrapper>);
}

export default HomePage;