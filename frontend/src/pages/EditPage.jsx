import Box from '../components/atoms/Box';
import Heading from '../components/atoms/Heading';
import CreateForm from '../components/organisms/CreateForm';

const EditPage = () => {
    return (
        <Box direction="column" className="page">
            <Heading>글 수정</Heading>
            <CreateForm />
        </Box>
    );
};

export default EditPage;
