/* eslint-disable no-unused-vars */
import { styled } from 'styled-components';
import theme from '../styles/theme';
import HeartImg from '../assets/heart.png';
import EmptyHeartImg from '../assets/emptyHeart.png';
import EditImg from '../assets/edit.png';
import DeleteImg from '../assets/delete.png';
import CheckImg from '../assets/check-mark.png';
import CancleImg from '../assets/remove.png';

import softYellowImg from '../assets/softYellow.png';
import softBlueImg from '../assets/softBlue.png';
import softGreenImg from '../assets/softGreen.png';
import whiteImg from '../assets/white.png';

import { useState } from 'react';

const PostIt = ({ id, originalBg, content, nickname, own, comments }) => {
  const [like, setLike] = useState(true);
  const [status, setStatus] = useState('default');
  const [bg, setBg] = useState(originalBg);
  return (
    <>
      <PostItWrap bg={bg} key={id}>
        <p>{content}</p>
        {own ? (
          <MyIconItems nickname={nickname} status={status}></MyIconItems>
        ) : (
          <OtherIconItems nickname={nickname} like={like}></OtherIconItems>
        )}
        <form>
          <input></input>
        </form>
        {comments.map((comment) => {
          return (
            <>
              <div>{comment.cont}</div>
              <div>{comment.name}</div>
            </>
          );
        })}
      </PostItWrap>
    </>
  );
};

const PostItWrap = styled.div`
  background-color: ${(props) => {
    switch (props.bg) {
      case 'blue':
        return props.theme.color.softBlue;
      case 'yellow':
        return props.theme.color.softYellow;
      case 'white':
        return props.theme.color.white;
      case 'green':
        return props.theme.color.softGreen;
      default:
        break;
    }
  }};
  color: ${(props) => props.theme.color.softBlack};
  width: 450px;
`;

const Icon = styled.img`
  wight: 1rem;
  height: 1rem;
`;

const IconItems = styled.div`
  ${(props) => props.theme.location.flex('row', 'center', 'start')}
  border-top: 0.06rem solid ${(props) => props.theme.color.gray};
`;

const OtherIconItems = ({ nickname, like }) => {
  return (
    <IconItems>
      <p>{nickname}</p>
      <Icon src={like ? HeartImg : EmptyHeartImg}></Icon>
    </IconItems>
  );
};

const MyIconItems = ({ nickname, status }) => {
  switch (status) {
    case 'default':
      return (
        <IconItems>
          <p>{nickname}</p>
          <Icon src={EditImg}></Icon>
          <Icon src={DeleteImg}></Icon>
        </IconItems>
      );
    case 'edit':
      return (
        <IconItems>
          <p>{nickname}</p>
          <Icon src={CheckImg}></Icon>
          <Icon src={CancleImg}></Icon>
          <Icon src={whiteImg}></Icon>
          <Icon src={softYellowImg}></Icon>
          <Icon src={softBlueImg}></Icon>
          <Icon src={softGreenImg}></Icon>
        </IconItems>
      );
    case 'delete':
      return (
        <IconItems>
          <p>{nickname}</p>
          <Icon src={CheckImg}></Icon>
          <Icon src={CancleImg}></Icon>
        </IconItems>
      );
    default:
      break;
  }
};

export default PostIt;
