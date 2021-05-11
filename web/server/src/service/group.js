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

};
