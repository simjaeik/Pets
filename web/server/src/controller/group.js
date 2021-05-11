const service = require("../service/group");
const control = require("../lib/controller");

module.exports = {
  getMyGroups: async (req, res) => {
    const { status, result } = await control(service.getMyGroups, req.data);

    return res.status(status).json(result);
  },

  setGroup: async (req, res) => {
    const { status, result } = await control(service.setGroup, {
      data: req.data,
      body: req.body,
    });

    return res.status(status).json(result);
  },
};
