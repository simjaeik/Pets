const { Group, MemberGroup, sequelize } = require("../model/index");

const checkInvalidData = (data) => {
  const { name, info, image, share, latitude, longitude } = data;
  if (!name || !info || !image || !share || !latitude || !longitude) {
    return true;
  }
  return false;
};

module.exports = {
  getMyGroups: async ({ UID }) => {
    if (!UID) {
      return { error: "invalid Token" };
    }

    try {
      const groups = await MemberGroup.findAll({
        include: [
          {
            model: Group,
          },
        ],
        where: { UID },
      });
      return groups;
    } catch (error) {
      return { error };
    }
  },

  setGroup: async ({ data, body }) => {
    if (!data) {
      return { error: "invalid Token" };
    }
    if (checkInvalidData(body)) {
      return { error: "모든 데이터를 입력해주세요." };
    }
  },
};
