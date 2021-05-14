const { createJWT } = require("../lib/utill/jwt");
const { Member } = require("../model/index");
const bcrypt = require("../lib/encrypt");

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

const checkPWValid = async (pw, UID) => {
  const member = await Member.findOne({ where: { UID } });
  const pwValid = await bcrypt.compare(pw, member.password);
  return pwValid ? true : false;
};

module.exports = {
  getMemberInfo: async ({ UID }) => {
    if (!UID) {
      return { error: "토큰이 존재하지 않습니다." };
    }

    try {
      const member = await Member.findOne({
        where: { UID },
        attributes: ["name", "email", "nickname"],
      });
      return member;
    } catch (error) {
      return { error: "Token이 올바르지 않습니다." };
    }
  },

  isEmailExist: async ({ email }) => {
    const exist = await Member.findOne({ where: { email: email } });

    if (exist) {
      return { result: true };
    }
    return { result: false };
  },

  isNicknameExist: async ({ nickname }) => {
    const exist = await Member.findOne({ where: { nickname: nickname } });

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

    const encryptPWD = await bcrypt.hash(password);

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

  updateMemberInfo: async ({ data, body }) => {
    const { UID } = data;
    const bodySize = Object.keys(body).length;
    if (!data || !body || bodySize <= 0) {
      return { error: "모든 정보를 입력해주세요." };
    }

    try {
      await Member.update(body, { where: { UID } });
      return { result: true };
    } catch (error) {
      console.log(error);
      return { result: false, error: "수정에 실패했습니다." };
    }
  },

  updateMemberPW: async ({ data, body }) => {
    const { UID } = data;
    const { password, afterPassword, afterRePassword } = body;

    const check = await checkPWValid(password, UID);
    if (!check) {
      return { error: "기존 비밀번호가 일치하지 않습니다." };
    }
    if (afterPassword !== afterRePassword) {
      return { error: "입력하신 두개의 비밀번호가 일치하지 않습니다." };
    }

    try {
      const updatePW = await bcrypt.hash(afterPassword);
      await Member.update({ password: updatePW }, { where: { UID } });
      return { result: true };
    } catch (error) {
      console.log(error);
      return { result: false, error: "수정에 실패했습니다." };
    }
  },
};
