const jwt = require("jsonwebtoken");

const { JWT_SECRET } = process.env;

module.exports = {
  createJWT: (id) => jwt.sign({ id }, JWT_SECRET),
};
