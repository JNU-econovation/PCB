import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
    @font-face {
        font-family: "pretendard";
        src: url("/src/assets/fonts/PretendardVariable.ttf");
    }

    *{
        margin: 0;
        padding: 0;
        font-size: 16px;
        box-sizing: border-box;

        font-family: "pretendard", Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue", "Segoe UI", "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;
    }

    button:focus{
        outline: none;
    }

    button:hover{
        outline: none;
    }

    html, body {
        width: 100vw;
        min-height: 100vh;
        overflow-x: hidden;
    }

    #root{
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100vw;
        height: 100vh;
    }

    input { 
        -webkit-appearance : none;
        -moz-appearance:none;
        appearance:none;
    }

    li {
        list-style:none;
    }

    a{
        text-decoration: none;
    }

    p{
        text-align: start;
    }

    .page{
        flex: 1;
        width: 100%;
        //min-height: 100%;
        align-items: center;
    }

    .postit-column{
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: start;
        gap: 1.5rem;
        height: 100%;
    }

`;

export default GlobalStyle;
