import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { ThemeProvider } from 'styled-components';
import GlobalStyle from './styles/GlobalStyle';
import theme from './styles/theme';

function App() {
    return (
        <ThemeProvider theme={theme}>
            <GlobalStyle />
            <Router>
                <Route path="/" element={<HomePage />} />
            </Router>
        </ThemeProvider>
    );
}

export default App;
