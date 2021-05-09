// Dependencies
const express = require("express");
const localAuth = require("./middleware/local-auth");

// Controller
const userController = require("../controller/user");

const router = express.Router();

router.get("/email/:email", userController.isEmailExist);

router.get("/nickname/:nickname", userController.isNicknameExist);

router.post("/signUp", userController.signUp);

router.post("/login", localAuth, userController.login);

module.exports = router;
