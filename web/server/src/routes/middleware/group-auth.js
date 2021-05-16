//Dependencies
const { verifyJWT } = require("../../lib/utill/jwt");
const { MemberGroup, Sequelize } = require("../../model");
const { Op } = Sequelize;

module.exports = async (req, res, next) => {
  const { authorization } = req.headers;
  const { data } = verifyJWT(authorization);
  const { UID } = data;

  const id = req.body.GID ? req.body.GID : req.params.id;

  if (!data) {
    return res.status(401).json("로그인 해주시기 바랍니다");
  }

  try {
    const memberGroup = await MemberGroup.findOne({
      where: {
        [Op.and]: [{ UID }, { GID: id }],
      },
      attributes: ["authority"],
      raw: true,
    });

    if (memberGroup === null || memberGroup === undefined) {
      return res.status(400).json("해당 그룹 & 멤버가 존재하지 않습니다.");
    }

    const { authority } = memberGroup;
    if (authority === "수정권한" || authority === "관리자") {
      req.auth = authority;
      req.gid = id;
      req.uid = UID;
      return next();
    }
  } catch (error) {
    console.log(error);
    return { error };
  }

  return res.status(401).json("이 그룹에 권한이 없습니다.");
};
