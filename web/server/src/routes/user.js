// Dependencies
const express = require("express");
const localAuth = require("./middleware/local-auth");

// Controller
const controller = require("../controller/user");

const router = express.Router();

router.get("/", controller.getMemberInfo);

router.get("/email/:email", controller.isEmailExist);

router.get("/nickname/:nickname", controller.isNicknameExist);

router.post("/signUp", controller.signUp);

router.post("/login", localAuth, controller.login);

module.exports = router;
