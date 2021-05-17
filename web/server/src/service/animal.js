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

  addAnimal: async ({ GID, body, file }) => {
    if (!GID) {
      return { error: "GID가 존재하지 않습니다." };
    }

    const validAddBody = checkAddBodyValid(body);
    if (!validAddBody || !file) {
      return { error: "body의 정보가 부족합니다." };
    }

    body.GID = GID;
    body.image = file.location;
    try {
      await Animal.create(body);
      return { result: true };
    } catch (error) {
      console.log(error);
      return error;
    }
  },

  updateAnimalDetail: async ({ AID, body, file }) => {
    AID = AID.id;
    if (!AID || !body) {
      return { error: "정보가 부족합니다." };
    }

    if (file) {
      body.image = file.location;
    }

    delete body.GID;
    try {
      const result = await Animal.update(body, { where: { AID } });
      if (result <= 0) {
        return { result: false, error: "수정이 되지 않았습니다" };
      }
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

  getMemo: async ({ id }) => {
    if (!id) {
      return { error: "AID를 확인하세요." };
    }

    try {
      const memos = await Memo.findOne({
        where: { MID: id },
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

  updateMemo: async ({ MID, body }) => {
    MID = MID.id;
    if (!MID || !body) {
      return { error: "입력한 정보가 부족합니다." };
    }
    delete body.GID;

    try {
      const result = await Memo.update(body, { where: { MID } });
      if (result <= 0) {
        return { result: false, error: "수정된 내용이 없습니다." };
      }
      return { result: true };
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },

  deleteMemo: async ({ id }) => {
    if (!id) {
      return { error: "입력한 정보가 부족합니다." };
    }

    try {
      const result = await Memo.destroy({ where: { MID: id } });
      if (result <= 0) {
        return { result: false, error: "아무것도 삭제되지 않았습니다." };
      }
      return { result: true };
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },

  // MedicalHistory

  getMedicalHistories: async ({ id }) => {
    if (!id) {
      return { error: "AID를 확인하세요." };
    }

    try {
      const medicals = await MedicalHistory.findAll({
        where: { AID: id },
        attributes: ["HID", "date", "title", "content", "hospital"],
        raw: true,
      });
      return medicals;
    } catch (error) {
      console.log(error);
      return { error };
    }
  },

  getMedicalHistory: async ({ id }) => {
    if (!id) {
      return { error: "HID를 확인하세요." };
    }

    try {
      const medical = await MedicalHistory.findOne({
        where: { HID: id },
        raw: true,
      });
      return medical;
    } catch (error) {
      console.log(error);
      return { error };
    }
  },

  setMedicalHistory: async ({ AID, body }) => {
    if (!AID || !body) {
      return { error: "입력한 정보가 부족합니다." };
    }
    body.AID = AID.id;
    delete body.GID;
    try {
      const result = await MedicalHistory.create(body);
      console.log(result);
      return { result: true };
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },

  updateMedicalHistory: async ({ HID, body }) => {
    HID = HID.id;
    if (!HID || !body) {
      return { error: "입력한 정보가 부족합니다." };
    }
    delete body.GID;

    try {
      const result = await MedicalHistory.update(body, { where: { HID } });
      if (result <= 0) {
        return { result: false, error: "수정된 내용이 없습니다." };
      }
      return { result: true };
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },

  deleteMedicalHistory: async ({ id }) => {
    if (!id) {
      return { error: "입력한 정보가 부족합니다." };
    }

    try {
      const result = await MedicalHistory.destroy({ where: { HID: id } });
      if (result <= 0) {
        return { result: false, error: "아무것도 삭제되지 않았습니다." };
      }
      return { result: true };
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },
};
