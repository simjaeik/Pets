const { createJWT } = require("../lib/utill/jwt");
const Member = require("../model/index").Member;
const bcrypt = require("bcrypt");

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
  signUp: async (data) => {
    const { name, password, email, nickName } = data;
    const hasError = await checkUserValid(data);

    if (hasError) {
      return hasError;
    }

    const salt = 10;
    const encryptPWD = await bcrypt.hash(password, salt);

    try {
      const member = await Member.create({
        name,
        password: encryptPWD,
        email,
        nickName,
      });
      return member;
    } catch (er) {
      return { error: er };
    }
  },

  login: async (member) => {
    const jwtToken = createJWT(member);

    return { token: jwtToken };
  },
};
