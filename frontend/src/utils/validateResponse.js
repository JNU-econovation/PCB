/**
 * response로 받은 data에 필요한 값들이 모두 들어있는지 검증하는 함수
 * @param {Object} data : response 값
 * @param {Array} requiredKeys : 필요한 값들의 정보
 * @returns 검증 결과 (Boolean)
 */
export const validateResponse = (data, requiredKeys) => {
    if (!data || !data.data || !data.data.response) {
        return false;
    }

    const keys = Object.keys(data.data.response);

    for (let i = 0; i < requiredKeys.length; i++) {
        const requiredKey = requiredKeys[i];
        if (!keys.includes(requiredKey)) {
            console.log(requiredKey);
            return false;
        }
    }

    return true;
};
