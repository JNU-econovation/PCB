import { DragDropContext, Draggable, Droppable } from 'react-beautiful-dnd';
import PostIt from './postIt';
const PostItContainer = ({ postitData, setPostitData }) => {
  const handlePostitChange = ({ source, destination }) => {
    // 유효하지 않은 곳으로 drag 시 이벤트 종료
    if (!destination) return;
    console.log('handlePostitChange!!!');
    // 출발지와 목적지의 status 저장
    const sourceKey = source.droppableId;
    const destinationKey = destination.droppableId;

    // 깊은 복사
    const items = JSON.parse(JSON.stringify(postitData));
    // drag를 시작한 리스트에서 drag한 아이템 빼기
    const [reorderedItem] = items[sourceKey].splice(source.index, 1);
    // drop 되는 리스트에 알맞는 위치에 아이템 추가
    items[destinationKey].splice(destination.index, 0, reorderedItem);
    setPostitData(items);
  };

  return (
    <DragDropContext onDragEnd={handlePostitChange}>
      <div style={{ display: 'flex' }}>
        {Object.keys(postitData).map((key) => (
          <Droppable key={key} droppableId={key}>
            {(provided) => (
              <div
                className="postitlists}"
                {...provided.droppableProps}
                ref={provided.innerRef}
              >
                {postitData[key].map((item, idx) => (
                  <Draggable
                    draggableId={`test-${item.id}`}
                    index={idx}
                    key={`test-${item.id}`}
                  >
                    {(provided) => {
                      return (
                        <div
                          {...provided.draggableProps}
                          {...provided.dragHandleProps}
                          ref={provided.innerRef}
                        >
                          <PostIt
                            id={item.id}
                            originalBg={item.color}
                            content={item.content}
                            nickname={item.nickname}
                            own={item.own}
                            comments={item.comments}
                          />
                        </div>
                      );
                    }}
                  </Draggable>
                ))}
                {provided.placeholder}
              </div>
            )}
          </Droppable>
        ))}
      </div>
    </DragDropContext>
  );
};

export default PostItContainer;
