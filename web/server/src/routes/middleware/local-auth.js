//Dependencies
const passport = require("passport");

module.exports = (req, res, next) => {
  passport.authenticate(
    "local",
    { session: false },
    (error, member, message) => {
      if (!member) {
        return res.status(401).json(message);
      }
      if (error) {
        return res.status(401).json(error);
      }

      req.member = member;
      return next();
    }
  )(req, res, next);
};
