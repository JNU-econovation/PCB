const calcRem = (size) => `${size / 16}rem`;

const fontSize = {
    small: calcRem(14),
    base: calcRem(16),
    lg: calcRem(18),
    xl: calcRem(20),
    xxl: calcRem(22),
    xxxl: calcRem(24),

    heading: calcRem(40),
    title: calcRem(32),
};

const padding = {
    small: calcRem(8),
    base: calcRem(10),
    lg: calcRem(12),
    xl: calcRem(14),
    xxl: calcRem(16),
    xxxl: calcRem(18),
};

const margin = {
    small: calcRem(8),
    base: calcRem(10),
    lg: calcRem(12),
    xl: calcRem(14),
    xxl: calcRem(16),
    xxxl: calcRem(18),
};

const border = {
    rad_sm: calcRem(4),
    rad_base: calcRem(8),
    rad_lg: calcRem(12),
};

const color = {
    black: '#000',
    white: '#FFF',
    dark_gray: '#495057',
    gray: '#adb5bd',
    light_gray: '#dee2e6',

    light_blue: '#AFD3E2',
    blue: '#19A7CE',
    dark_blue: '#146C94',
    red: '#DC3434',

    soft_white: '#F6F1F1',
    soft_black: '#212529',
    soft_yellow: '#FFF3CE',
    soft_green: '#DDFFDD',
    soft_blue: '#BAEAFF',
};

const location = {
    flex: (direction = 'row', align = 'center', justify = 'center') => `
        display: flex;
        flex-direction: ${direction};
        align-items: ${align};
        justify-content: ${justify};
    `,

    form: `
        display: flex;
        flex-direction: column;
        align-items: stretch;
        justify-content: center;
        gap: 1rem;

        padding: ${padding.lg}
    `,
};

const theme = {
    fontSize,
    padding,
    margin,
    color,
    border,
    location,
};

export default theme;
