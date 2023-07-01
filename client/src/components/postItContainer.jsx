import { DragDropContext, Droppable } from "react-beautiful-dnd"

const PostItContainer = () => {
    return(<DragDropContext>
        <Droppable></Droppable>
    </DragDropContext>)
}