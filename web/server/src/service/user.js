const { createJWT } = require("../lib/utill/jwt");
const { Member, MemberGroup } = require("../model/index");
const bcrypt = require("bcryptjs");

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
  isEmailExist: async ({ email }) => {
    const exist = await Member.findOne({ where: { email: email } });

    if (exist) {
      return { result: true };
    }
    return { result: false };
  },

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

  login: async ({ email }) => {
    try {
      const { UID } = await Member.findOne({
        raw: true,
        where: { email: email },
      });

      data = { UID };
    } catch (error) {
      return { error };
    }

    const jwtToken = createJWT(data);

    return { token: jwtToken };
  },
};
