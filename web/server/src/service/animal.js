const { Animal } = require("../model/index");

module.exports = {
  getAnimalByGroup: async (GID) => {
    if (!GID) {
      return { error: "GID가 존재하지 않습니다." };
    }
    try {
      const animals = await Animal.findAll({ where: { GID }, raw: true });
      return animals;
    } catch (error) {
      console.log(error);
      return error;
    }
  },

};
