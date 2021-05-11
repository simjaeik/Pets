const loginStrategy = require("./login");
const jwtStategy = require("./jwt");

const initStrategy = () => {
  loginStrategy();
  jwtStategy();
};

module.exports = initStrategy;
