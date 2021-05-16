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


};
