//Dependencies
const passport = require("passport");

module.exports = (req, res, next) => {
  passport.authenticate(
    "local",
    { session: false },
    (error, member, message) => {
      if (!member) {
        return res.status(400).json(message);
      }
      if (error) {
        return res.status(400).json(error);
      }

      req.member = member;
      return next();
    }
  )(req, res, next);
};
