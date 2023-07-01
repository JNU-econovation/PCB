const color = {
  dark_blue: "#06283D",
  blue: "#1363DF",
  lightBlue: "#47B5FF",
  skyBlue: "#DFF6FF",

  gray: "#AFB1B6",
  lightGray: "#EEEEEE",
  darkGray: "#5C5C5C",
  black: "#000",
  white: "#FFF",
  red: "#DC3434",

  softBlack: "#343434",
  softYellow: "#FFF3CE",
  softGreen: "#DDFFDD",
  softBlue: "#BAEAFF",
};

const font = {
  xs: "0.75rem",
  sm: "0.875rem",
  md: "1rem",
  lg: "1.125rem",
  xl: "1.25rem",
  xxl: "1.5rem",

  subTitle: "1.5rem",
  title: "2.25rem",

  extra_bold: "800",
  bold: "700",
  semiBold: "600",
  medium: "500",
  regular: "400",
  thin: "300",
};

const border = {
  round4: "0.25rem",
  round12: "0.75rem",
  round16: "1rem",

  search: `2px solid ${color.lightGray}`,
  button: `2px solid ${color.blue}`,
};

const location = {
  flex: (direction = "row", align = "center", justify = "center") => `
        display: flex;
        flex-direction: ${direction};
        align-items: ${align};
        justify-content: ${justify};
    `,

  positionFixed: `
        position: fixed;
        top: 0;
        left: 0;
        z-index: 999;
    `,
};

const theme = { color, font, border, location };

export default theme;
