const { GalleryImage } = require("../model/index");

module.exports = {
  getGalleryImages: async ({ id }) => {
    if (!id) {
      return { error: "모든 정보를 입력해주세요" };
    }
    try {
      const image = await GalleryImage.findAll({
        where: { GID: id },
        attributes: ["IID", "url", "tag"],
      });
      return image;
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },

};
