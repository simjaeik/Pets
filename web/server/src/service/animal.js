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

  getAnimal: async ({ id }) => {
    if (!id) {
      return { error: "아이디가 존재하지 않습니다." };
    }

    try {
      const animal = await Animal.findOne({ where: { AID: id }, raw: true });
      if (!animal) {
        return { error: "해당 동물이 존재하지 않습니다." };
      }
      return animal;
    } catch (error) {
      console.log(error);
      return { error };
    }
  },

};
