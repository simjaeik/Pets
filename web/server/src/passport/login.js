// Dependencies
const passport = require("passport");
const { Strategy: LocalStrategy } = require("passport-local");
const bcrypt = require("../lib/encrypt");

// DB
const Member = require("../model").Member;

const passportConfig = { usernameField: "email", passwordField: "password" };

const passportVerify = async (email, password, done) => {
  try {
    const member = await Member.findOne({ where: { email } });
    if (!member) {
      done(null, false, { message: "존재하지 않는 사용자 입니다." });
      return;
    }

    const comparePassword = await bcrypt.compare(password, member.password);

    if (comparePassword) {
      done(null, member);
      return;
    }
    done(null, false, { reason: "올바르지 않은 비밀번호 입니다." });
  } catch (error) {
    console.log(error);
    done(error);
  }
};

module.exports = () => {
  passport.use("local", new LocalStrategy(passportConfig, passportVerify));
};
