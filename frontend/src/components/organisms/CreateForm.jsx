import LabeledInput from '../molecules/LabeledInput';
import FORM_INFO from '../../constants/FORM_INFO';
import LabeledTextarea from '../molecules/LabeledTextarea';
import Form from '../atoms/Form';
import SubmitButton from '../atoms/SubmitButton';
import { useEffect, useState } from 'react';
import FORM_DEFAULT from '../../constants/FORM_DEFAULT';
import { boardCreate } from '../../services/board';
import TagInput from '../molecules/TagInput';
import { useNavigate } from 'react-router-dom';

const CreateForm = ({ data, handleSubmit }) => {
    const [values, setValues] = useState(data);
    const [tag, setTag] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setValues({ ...values, [name]: value });
    };

    const onCheckEnter = (e) => {
        if (e.key === 'Enter') {
            if (e.target.type === 'textarea') return;
            if (e.target.id === 'boardTagList' && e.target.value.length !== 0) {
                if (values.boardTagList.length >= 3) {
                    alert('태그는 최대 3개 입력할 수 있습니다.');
                    e.preventDefault();
                    return;
                }
                submitTagItem(e.target.value);
            }
            e.preventDefault();
        }
    };

    const submitTagItem = (value) => {
        let updatedTagList = [...values.boardTagList];
        updatedTagList.push(value);
        setValues((prev) => ({ ...prev, boardTagList: updatedTagList }));
        setTag('');
    };

    return (
        <Form onSubmit={(e) => handleSubmit(e, values)} onKeyPress={onCheckEnter}>
            <LabeledInput
                key={FORM_INFO.BOARD.title.id}
                id={FORM_INFO.BOARD.title.id}
                type={FORM_INFO.BOARD.title.type}
                placeholder={FORM_INFO.BOARD.title.placeholder}
                label={FORM_INFO.BOARD.title.label}
                value={values[FORM_INFO.BOARD.title.id]}
                onChange={handleChange}
                className="board"
            />
            <LabeledTextarea
                key={FORM_INFO.BOARD.content.id}
                id={FORM_INFO.BOARD.content.id}
                label={FORM_INFO.BOARD.content.label}
                onChange={handleChange}
                value={values[FORM_INFO.BOARD.content.id]}
                rows={15}
                cols={60}
            />
            <TagInput
                key={FORM_INFO.BOARD.tag.id}
                id={FORM_INFO.BOARD.tag.id}
                type={FORM_INFO.BOARD.tag.type}
                placeholder={FORM_INFO.BOARD.tag.placeholder}
                label={FORM_INFO.BOARD.tag.label}
                tagList={values[FORM_INFO.BOARD.tag.id]}
                setValues={setValues}
                tag={tag}
                setTag={setTag}
            />
            <SubmitButton className="xl">제출하기</SubmitButton>
        </Form>
    );
};

export default CreateForm;
