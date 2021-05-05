// Dependencies
const express = require("express");
const localAuth = require("./middleware/local-auth");

// Controller
const userController = require("../controller/user");

const router = express.Router();

router.post("/login", localAuth, userController.login);

module.exports = router;
