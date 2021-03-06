// Dependencies
const express = require("express");
const localAuth = require("./middleware/local-auth");
const jwtAuth = require("./middleware/jwt-auth");

// Controller
const controller = require("../controller/user");

const router = express.Router();

router.get("/", jwtAuth, controller.getMemberInfo);

router.get("/email/:email", controller.isEmailExist);

router.get("/nickname/:nickname", controller.isNicknameExist);

router.post("/signUp", controller.signUp);

router.post("/login", localAuth, controller.login);

router.patch("/", jwtAuth, controller.updateMemberInfo);

router.patch("/PW", jwtAuth, controller.updateMemberPW);

module.exports = router;
