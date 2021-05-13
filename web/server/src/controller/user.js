const userService = require("../service/user");
const control = require("../lib/controller");
const user = require("../service/user");

module.exports = {
  getMemberInfo: async (req, res) => {
    const { status, result } = await control(
      userService.getMemberInfo,
      req.data
    );

    return res.status(status).json(result);
  },

  updateMemberInfo: async (req, res) => {
    const { status, result } = await control(userService.updateMemberInfo, {
      data: req.data,
      body: req.body,
    });

    return res.status(status).json(result);
  },

  isEmailExist: async (req, res) => {
    const { status, result } = await control(
      userService.isEmailExist,
      req.params
    );

    return res.status(status).json(result);
  },

  isNicknameExist: async (req, res) => {
    const { status, result } = await control(
      userService.isNicknameExist,
      req.params
    );

    return res.status(status).json(result);
  },

  signUp: async (req, res) => {
    const { status, result } = await control(userService.signUp, req.body);

    return res.status(status).json(result);
  },

  login: async (req, res) => {
    const { status, result } = await control(userService.login, req.member);

    return res.status(status).json(result);
  },
};
