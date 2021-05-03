const { createJWT } = require("../lib/utill/jwt");

module.exports = {
  login: async (member) => {
    const jwtToken = createJWT(member);

    return { token: jwtToken };
  },
};
