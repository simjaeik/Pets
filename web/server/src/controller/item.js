const service = require("../service/item");
const control = require("../lib/controller");

module.exports = {
  getItems: async (req, res) => {
    const { status, result } = await control(service.getItems, req.params);

    return res.status(status).json(result);
  },

  getItem: async (req, res) => {
    const { status, result } = await control(service.getItem, req.params);

    return res.status(status).json(result);
  },

  setItem: async (req, res) => {
    const { status, result } = await control(service.setItem, req.body);

    return res.status(status).json(result);
  },

  updateItem: async (req, res) => {
    const { status, result } = await control(service.updateItem, {
      HID: req.params,
      body: req.body,
    });

    return res.status(status).json(result);
  },

  deleteItem: async (req, res) => {
    const { status, result } = await control(service.deleteItem, req.params);

    return res.status(status).json(result);
  },
};
