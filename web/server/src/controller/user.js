const userService = require("../service/user");
const control = require("../lib/controller");

module.exports = {
  signUp: async (req, res) => {
    const { status, result } = await control(userService.signUp, req.body);

    return res.status(status).json(result);
  },

  login: async (req, res) => {
    const { status, result } = await control(userService.login, req.member);

    return res.status(status).json(result);
  },
};
