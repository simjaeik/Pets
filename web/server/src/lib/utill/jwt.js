const jwt = require("jsonwebtoken");

const { JWT_SECRET } = process.env;

module.exports = {
  createJWT: (data) => {
    return jwt.sign(
      { data, exp: Math.floor(Date.now() / 1000) + 60 * 60 },
      JWT_SECRET
    );
  },

  verifyJWT: (token) => {
    try {
      const id = jwt.verify(token, JWT_SECRET);
      return id;
    } catch (error) {
      return false;
    }
  },
};
