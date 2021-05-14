const { Group, MemberGroup, sequelize, Member } = require("../model/index");

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

  getGroup: async ({ id }) => {
    if (!id) {
      return { error: "모든 정보를 입력해주세요." };
    }

    try {
      const group = await Group.findOne({ where: { GID: id } });
      return group;
    } catch (error) {
      console.log(error);
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
    const { UID } = data;
    const { name, info, image, share, latitude, longitude } = body;

    const t = await sequelize.transaction();

    try {
      const group = await Group.create(
        {
          name,
          info,
          image,
          share,
          latitude,
          longitude,
        },
        { transaction: t }
      );
      const { GID } = group.dataValues;

      await MemberGroup.create(
        {
          UID,
          GID,
          authority: "수정권한",
        },
        { transaction: t }
      );

      await t.commit();

      return { result: true };
    } catch (error) {
      await t.rollback();
      console.log(error);
      return { error };
    }
  },
};
