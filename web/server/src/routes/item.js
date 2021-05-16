// Dependencies
const express = require("express");
const groupAuth = require("./middleware/group-auth");

// Controller
const controll = require("../controller/item");

const router = express.Router();

router.get("/:id", controll.getItems);

module.exports = router;
