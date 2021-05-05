const { createJWT } = require("../lib/utill/jwt");
const checkUserValid = async ({ name, password, email, nickName }) => {
  const existEmail = await Member.findOne({ where: { email } });
  const existNickName = await Member.findOne({ where: { nickName } });

  if (!name || !password || !email || !nickName) {
    return { error: "모든 정보를 입력해주세요." };
  }

  if (existEmail) {
    return { error: "중복된 email입니다." };
  }

  if (existNickName) {
    return { error: "중복된 닉네임입니다." };
  }
  return false;
};

module.exports = {
  login: async (member) => {
    const jwtToken = createJWT(member);

    return { token: jwtToken };
  },
};
