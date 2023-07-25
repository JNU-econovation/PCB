export const arrangeArray = (array) => {
    const tail = array.find((item) => item.after === -1);
    const result = [tail];

    while (true) {
        const prevItem = array.find((item) => item.after === result[result.length - 1].commentId);
        if (prevItem === undefined) {
            break;
        }

        result.push(prevItem);
    }

    return result.reverse();
};
