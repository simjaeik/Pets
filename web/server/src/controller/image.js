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

  getGalleryImage: async (req, res) => {
    const { status, result } = await control(
      service.getGalleryImage,
      req.params
    );

    return res.status(status).json(result);
  },

  setGalleryImage: async (req, res) => {
    const { status, result } = await control(service.setGalleryImage, {
      body: req.body,
      file: req.file,
    });

    return res.status(status).json(result);
  },

  updateGalleryImage: async (req, res) => {
    const { status, result } = await control(service.updateGalleryImage, {
      IID: req.params,
      file: req.file,
    });

    return res.status(status).json(result);
  },

};
