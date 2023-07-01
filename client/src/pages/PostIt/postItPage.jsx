/* eslint-disable no-unused-vars */
import { useEffect, useState } from 'react';
import PostItContainer from '../../components/postItContainer';

const initPostitData = [
  {
    id: 1,
    color: 'blue',
    content: '내용 1',
    nickname: 'bboddo',
    own: true,
    comments: [
      { cont: '댓글1', name: 'tony' },
      { cont: '댓글2', name: 'mandu' },
    ],
  },
  {
    id: 2,
    color: 'white',
    content: '내용 2',
    nickname: 'ddori',
    own: false,
    comments: [
      { cont: '댓글3', name: 'eden' },
      { cont: '댓글4', name: 'cherry' },
      { cont: '댓글5', name: 'bboddo' },
    ],
  },
  {
    id: 3,
    color: 'yellow',
    content: '내용 3',
    nickname: 'eden',
    own: false,
    comments: [{ cont: '댓글6', name: 'roro' }],
  },
  {
    id: 4,
    color: 'green',
    content: '내용 4',
    nickname: 'bboddo',
    own: true,
    comments: [],
  },
  {
    id: 5,
    color: 'white',
    content: '내용 5',
    nickname: 'gamjadori',
    own: false,
    comments: [
      { cont: '댓글7', name: 'tony' },
      { cont: '댓글8', name: 'cookie' },
    ],
  },
  {
    id: 6,
    color: 'yellow',
    content: '내용 6',
    nickname: 'loopy',
    own: false,
    comments: [{ cont: '댓글9', name: 'gamjadori' }],
  },
];

const PostItPage = () => {
  const [postitData, setPostitData] = useState({
    left: initPostitData.filter((item, idx) => idx % 2 === 0),
    right: initPostitData.filter((item, idx) => idx % 2 === 1),
  });

  useEffect(() => {
    console.log('postitData is Changed!!!');
    console.log(postitData);
  }, [postitData]);
  return (
    <>
      <h1>Postit Page</h1>
      <PostItContainer postitData={postitData} setPostitData={setPostitData} />
    </>
  );
};

export default PostItPage;
