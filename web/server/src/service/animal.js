const { Animal } = require("../model/index");

const checkAddBodyValid = (body) => {
  if (Object.keys(body).length !== 8) {
    return false;
  }
  return true;
};

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

  addAnimal: async ({ GID, body }) => {
    if (!GID) {
      return { error: "GID가 존재하지 않습니다." };
    }

    const validAddBody = checkAddBodyValid(body);
    if (!validAddBody) {
      return { error: "body의 정보가 부족합니다." };
    }

    body.GID = GID;
    try {
      await Animal.create(body);
      return { result: true };
    } catch (error) {
      console.log(error);
      return error;
    }
  },

};
