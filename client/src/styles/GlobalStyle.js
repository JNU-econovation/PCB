import { createGlobalStyle } from "styled-components";

const GlobalStyle = createGlobalStyle`
    @font-face {
        font-family: "SUITE";
        font-weight: 300;
        src: url("./fonts/SUITE-Light.ttf") format("truetype");
    }

    @font-face {
    font-family: "SUITE";
    font-weight: 400;
    src: url("./fonts/SUITE-Regular.ttf") format("truetype");
    }

    @font-face {
        font-family: "SUITE";
        font-weight: 500;
        src: url("./fonts/SUITE-Medium.ttf") format("truetype");
    }

    @font-face {
        font-family: "SUITE";
        font-weight: 600;
        src: url("./fonts/SUITE-SemiBold.ttf") format("truetype");
    }

    @font-face {
        font-family: "SUITE";
        font-weight: 700;
        src: url("./fonts/SUITE-Bold.ttf") format("truetype");
    }

    @font-face {
        font-family: "SUITE";
        font-weight: 800;
        src: url("./fonts/SUITE-ExtraBold.ttf") format("truetype");
    }

    @font-face {
        font-family: "SUITE";
        font-weight: 900;
        src: url("./fonts/SUITE-Heavy.ttf") format("truetype");
    }

    *{
        margin: 0;
        padding: 0;
        font-size: 16px;
        box-sizing: border-box;

        font-family: "SUITE", "Pretendard Variable", Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue", "Segoe UI", "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;
    }

    body {
        width: 100vw;
        min-height: 100vh;
        overflow-x: hidden;
    }

    #root{
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        min-height: 100%;
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
`;

export default GlobalStyle;
