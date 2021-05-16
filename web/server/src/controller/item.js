const service = require("../service/item");
const control = require("../lib/controller");

module.exports = {
  getItems: async (req, res) => {
    const { status, result } = await control(service.getItems, req.params);

    return res.status(status).json(result);
  },

  setItem: async (req, res) => {
    const { status, result } = await control(service.setItem, req.body);

    return res.status(status).json(result);
  },

};
