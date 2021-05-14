const bcrypt = require("bcryptjs");

const SALT = 10;

module.exports = {
  hash: async (target) => {
    return await bcrypt.hash(target, SALT);
  },

  compare: async (target1, target2) => {
    return await bcrypt.compare(target1, target2);
  },
};
