import LabeledInput from '../molecules/LabeledInput';
import FORM_INFO from '../../constants/FORM_INFO';
import LabeledTextarea from '../molecules/LabeledTextarea';
import Form from '../atoms/Form';
import SubmitButton from '../atoms/SubmitButton';
import { useState } from 'react';
import FORM_DEFAULT from '../../constants/FORM_DEFAULT';
import { boardCreate } from '../../services/board';

const EditForm = () => {
    const [values, setValues] = useState(FORM_DEFAULT.BOARD);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setValues({ ...values, [name]: value });
    };
    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!values.title) {
            alert('제목을 입력해주세요');
        } else if (!values.content) {
            alert('설명을 입력해주세요');
        } else {
            await boardCreate({
                title: values.title,
                content: values.content,
                boardTagList: values.boardTagList,
            })
                .then((res) => {
                    navigate(`/board:${res.data.response.boardId}`);
                })
                .catch((err) => alert(err));
        }
    };

    return (
        <Form onSubmit={handleSubmit}>
            {FORM_INFO.BOARD.map((info) => {
                if (info.id === 'content') {
                    return (
                        <LabeledTextarea
                            key={info.id}
                            id={info.id}
                            label={info.label}
                            onChange={handleChange}
                            value={values[info.id]}
                            rows={15}
                            cols={60}
                        />
                    );
                } else {
                    return (
                        <LabeledInput
                            key={info.id}
                            id={info.id}
                            type={info.type}
                            placeholder={info.placeholder}
                            label={info.label}
                            value={values[info.id]}
                            onChange={handleChange}
                            className="board"
                        />
                    );
                }
            })}
            <SubmitButton className="xl">제출하기</SubmitButton>
        </Form>
    );
};

export default CreateForm;
