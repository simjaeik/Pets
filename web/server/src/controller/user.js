const userService = require("../service/user");
const control = require("../lib/controller");

module.exports = {
  login: async (req, res) => {
    const { status, result } = await control(userService.login, req.member);

    return res.status(status).json(result);
  },
};
