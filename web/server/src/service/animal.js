const { Animal, MedicalHistory, Memo } = require("../model/index");

const checkAddBodyValid = (body) => {
  if (Object.keys(body).length !== 8) {
    return false;
  }
  return true;
};

module.exports = {
  // Animal
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

  updateAnimalDetail: async ({ AID, body }) => {
    AID = AID.id;
    if (!AID || !body) {
      return { error: "정보가 부족합니다." };
    }

    delete body.GID;
    try {
      await Animal.update(body, { where: { AID } });
      return { result: true };
    } catch (error) {
      console.log(error);
      return { error };
    }
  },

  deleteAnimal: async ({ id }) => {
    if (!id) {
      return { error: "정보가 부족합니다." };
    }

    try {
      await MedicalHistory.destroy({ where: { AID: id } });
      await Memo.destroy({ where: { AID: id } });
      await Animal.destroy({ where: { AID: id } });
      return { result: true };
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },

  // Memos

  getMemos: async ({ id }) => {
    if (!id) {
      return { error: "AID를 확인하세요." };
    }

    try {
      const memos = await Memo.findAll({
        where: { AID: id },
        attributes: ["MID", "content"],
      });
      return memos;
    } catch (error) {
      console.log(error);
      return { error };
    }
  },

  setMemo: async ({ AID, body }) => {
    if (!AID || !body) {
      return { error: "입력한 정보가 부족합니다." };
    }
    body.AID = AID.id;
    delete body.GID;
    try {
      await Memo.create(body);
      return { result: true };
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },

};
