const service = require("../service/animal");
const control = require("../lib/controller");

module.exports = {
  getAnimalByGroup: async (req, res) => {
    const { status, result } = await control(service.getAnimalByGroup, req.gid);

    return res.status(status).json(result);
  },

  getAnimal: async (req, res) => {
    const { status, result } = await control(service.getAnimal, req.params);

    return res.status(status).json(result);
  },

  addAnimal: async (req, res) => {
    const { status, result } = await control(service.addAnimal, {
      GID: req.gid,
      body: req.body,
    });

    return res.status(status).json(result);
  },

  updateAnimalDetail: async (req, res) => {
    const { status, result } = await control(service.updateAnimalDetail, {
      AID: req.params,
      body: req.body,
    });

    return res.status(status).json(result);
  },

};
