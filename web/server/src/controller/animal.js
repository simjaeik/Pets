const service = require("../service/animal");
const control = require("../lib/controller");

module.exports = {
  getAnimalByGroup: async (req, res) => {
    const { status, result } = await control(service.getAnimalByGroup, req.gid);

    return res.status(status).json(result);
  },

};
