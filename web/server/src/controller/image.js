const service = require("../service/image");
const control = require("../lib/controller");

module.exports = {
  getGalleryImages: async (req, res) => {
    const { status, result } = await control(
      service.getGalleryImages,
      req.params
    );

    return res.status(status).json(result);
  },


};
