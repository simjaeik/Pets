//Dependencies
const { verifyJWT } = require("../../lib/utill/jwt");
const { MemberGroup, Sequelize } = require("../../model");
const { Op } = Sequelize;

module.exports = async (req, res, next) => {
  const { authorization } = req.headers;
  const { data } = verifyJWT(authorization);
  const { UID } = data;
  const { id } = req.params;

  if (!data) {
    return res.status(401).json("로그인 해주시기 바랍니다");
  }

  const { authority } = await MemberGroup.findOne({
    where: {
      [Op.and]: [{ UID }, { GID: id }],
    },
    attributes: ["authority"],
    raw: true,
  });

  if (authority === "수정권한" || authority === "관리자") {
    req.auth = authority;
    req.gid = id;
    return next();
  }

  return res.status(401).json("이 그룹에 권한이 없습니다.");
};
