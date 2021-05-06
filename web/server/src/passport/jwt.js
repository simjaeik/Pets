const passport = require("passport");
const { Strategy: JwtStrategy, ExtractJwt } = require("passport-jwt");
const Member = require("../model").Member;

const options = {
  jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
  secretOrKey: process.env.JWT_SECRET,
};

module.exports = () => {
  passport.use(
    new JwtStrategy(options, async function (jwt_payload, done) {
      try {
        const member = await Member.findOne({
          where: { id: jwt_payload.id },
        });

        if (member) {
          return done(null, member);
        }

        return done(null, false);
      } catch (error) {
        return done(error, false);
      }
    })
  );
};
