// Dependencies
const express = require("express");

// Controller
const controller = require("../controller/group");

const router = express.Router();

router.get("/", controller.getMyGoups);

module.exports = router;
