const {
  Group,
  Member,
  MemberGroup,
  Animal,
  ItemHistory,
  GalleryImage,
  Favorite,
  Post,
  sequelize,
  Sequelize,
} = require("../model/index");
const { Op } = Sequelize;

const checkInvalidData = (data) => {
  const { name, info, image, share, latitude, longitude } = data;
  if (
    name === undefined ||
    info === undefined ||
    !image ||
    share === undefined ||
    latitude === undefined ||
    longitude === undefined
  ) {
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

  getGroupMembers: async ({ id }) => {
    if (!id) {
      return { error: "모든 정보를 입력해주세요" };
    }

    try {
      const members = await MemberGroup.findAll({
        include: [
          {
            model: Member,
            attributes: ["name", "email", "nickName"],
          },
        ],
        attributes: ["UID", "authority"],
        where: { GID: id },
        raw: true,
      });
      return members;
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

    const t = await sequelize.transaction();

    try {
      const group = await Group.create(body, { transaction: t });
      const { GID } = group.dataValues;

      await MemberGroup.create(
        {
          UID,
          GID,
          authority: "관리자",
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

  addGroupMember: async ({ GID, body }) => {
    if (!GID || !body) {
      return { error: "정보가 부족합니다" };
    }

    const { email, authority } = body;

    try {
      const { UID } = await Member.findOne({ where: { email }, raw: true });

      const result = await MemberGroup.create({ GID, UID, authority });
      return result;
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },

  updateGroup: async ({ GID, body }) => {
    if (!body || !GID) {
      return { error: "정보가 부족합니다" };
    }

    try {
      await Group.update(body, { where: { GID } });
      return { result: true };
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },

  toggleShare: async ({ GID }) => {
    if (!GID) {
      return { error: "정보가 부족합니다." };
    }
    try {
      let { share } = await Group.findOne({ where: { GID }, raw: true });
      share = share === 1 ? 0 : 1;
      await Group.update({ share }, { where: { GID } });
      return { result: true };
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },

  deleteGroup: async (GID) => {
    if (!GID) {
      return { error: "삭제하고자 하는 그룹의 id가 존재하지 않습니다." };
    }

    try {
      const result = await Group.destroy({ where: { GID } });
      return result;
    } catch (error) {
      console.log(error);
      return { error };
    }
  },

  deleteGroupMember: async ({ GID, UID }) => {
    console.log(GID, UID);
    if (!GID || !UID) {
      return { error: "삭제하고자 하는 그룹의 id가 존재하지 않습니다." };
    }

    try {
      await Animal.destroy({ where: { GID } });
      await MemberGroup.destroy({ where: { GID } });
      await Favorite.destroy({ where: { GID } });
      await ItemHistory.destroy({ where: { GID } });
      await GalleryImage.destroy({ where: { GID } });
      await Post.destroy({ where: { GID } });

      await MemberGroup.destroy({ where: { [Op.and]: [{ UID }, { GID }] } });
      return { result: true };
    } catch (error) {
      console.log(error);
      return { result: false, error };
    }
  },
};
