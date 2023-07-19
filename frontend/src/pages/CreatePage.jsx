import { useState } from 'react';
import Box from '../components/atoms/Box';
import Heading from '../components/atoms/Heading';
import CreateForm from '../components/organisms/CreateForm';

const CreatePage = () => {
    const [value, setValues] = useState;
    return (
        <Box direction="column" className="page">
            <Heading>글 작성</Heading>
            <CreateForm />
        </Box>
    );
};

export default CreatePage;
