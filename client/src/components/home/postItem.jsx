import styled from "styled-components"
import theme from "../../styles/theme";
import SkeletonItem from "../common/skeletonItem";

const Wrapper = styled.div`
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    align-items: flex-start;
    gap: 5rem;

    max-width: 70rem;

    padding: 1rem 0;
`;

const Post = styled.div`
    display: grid;
    grid-template-rows: 5.5rem 13rem 2rem 2rem;

    height: 26rem;
    width: 19rem;
    padding: 2rem;
    
    background-color: ${theme.color.lightGray};
    border-radius: ${theme.border.round12};

    transition: transform .2s;

    &:hover {
        transform: scale(1.05);
    }
`;

const Title = styled.h3`
    font-size: ${theme.font.xxl};
    font-weight: ${theme.font.extra_bold};
    color: ${theme.color.black};
`;

const Content = styled.p`
    font-size: ${theme.font.lg};
    font-weight: ${theme.font.regular};
    color: ${theme.color.darkray};
`;

const Tags = styled.div`
    display: flex;
    flex-wrap: wrap;
    gap: 0.5rem;
`;

const Tag = styled.p`
    font-size: ${theme.font.md};
    font-weight: ${theme.font.regular};
    color: ${theme.color.blue};
`

const Date = styled.p`
    font-size: ${theme.font.sm};
    font-weight: ${theme.font.regular};
    color: ${theme.color.gray};
`;

const PostItem = (props) => {
    const datas = props.datas;
    return(
    <Wrapper>
        {datas.map(data=>(
            <Post>
                <Title>{data.title}</Title>
                <Content>{data.content.length < 200 ? data.content : data.content.substr(0, 200)}</Content>
                <Tags>
                    {data.tag.map(t => <Tag>#{t}</Tag>)}
                </Tags>
                <Date>{data.createdAt.year}년 {data.createdAt.month}월 {data.createdAt.day}일</Date>
            </Post>
        ))}
        
    </Wrapper>)
}

export default PostItem;