const service = require("../service/group");
const control = require("../lib/controller");

module.exports = {
  getMyGroups: async (req, res) => {
    const { status, result } = await control(service.getMyGroups, req.data);

    return res.status(status).json(result);
  },

};
