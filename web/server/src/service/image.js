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

  getGalleryImage: async ({ id }) => {
    if (!id) {
      return { error: "모든 정보를 입력해주세요" };
    }
    try {
      const image = await GalleryImage.findOne({
        where: { IID: id },
      });
      if (image <= 0) {
        return { result: false, error: "조회된 데이터가 없습니다." };
      }
      return image;
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },

  setGalleryImage: async ({ body, file }) => {
    if (!body || !file) {
      return { error: "모든 정보를 입력해주세요" };
    }

    body.url = file.location;

    try {
      const result = await GalleryImage.create(body);
      return { result: true };
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },

  updateGalleryImage: async ({ IID, file }) => {
    console.log(IID, file);
    if (!IID || !file) {
      return { error: "모든 정보를 입력하세요." };
    }

    IID = IID.id;

    try {
      const result = await GalleryImage.update(
        { url: file.location },
        { where: { IID } }
      );
      if (result <= 0) {
        return { result: false, error: "수정에 실패하였습니다." };
      }
      return { result: true };
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },

};
