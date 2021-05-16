const { ItemHistory } = require("../model/index");

const checkBodyValid = (body) => {
  const invalidValue = Object.values(body).some(
    (v) => v === null || v === undefined
  );
  const invalidBody = Object.keys(body).length === 5 ? false : true;
  return invalidValue || invalidBody ? false : true;
};

module.exports = {
  getItems: async ({ id }) => {
    if (!id) {
      return { error: "그룹 아이디가 존재하지 않습니다." };
    }

    try {
      const items = await ItemHistory.findAll({
        where: { GID: id },
        raw: true,
      });
      return items;
    } catch (error) {
      console.log(error);
      return { error };
    }
  },

  setItem: async (body) => {
    const bodyValid = checkBodyValid(body);
    if (!bodyValid) {
      return { error: "입력한 body의 정보가 부족합니다." };
    }

    try {
      await ItemHistory.create(body);
      return { result: true };
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },

  updateItem: async ({ HID, body }) => {
    const { id } = HID;
    if (!id) {
      return { error: "정보가 부족합니다." };
    }
    try {
      const [result] = await ItemHistory.update(body, { where: { HID: id } });
      if (result <= 0) {
        return { result: false, error: "아무 변화가 없습니다." };
      }
      return { result: true };
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },

  deleteItem: async ({ id }) => {
    if (!id) {
      return { error: "HID가 입력되지 않았습니다." };
    }

    try {
      await ItemHistory.destroy({ where: { HID: id } });
      return { result: true };
    } catch (error) {
      console.log(error);
      return { return: false, error };
    }
  },
};
