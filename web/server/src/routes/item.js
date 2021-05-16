// Dependencies
const express = require("express");
const groupAuth = require("./middleware/group-auth");

// Controller
const controll = require("../controller/item");

const router = express.Router();

router.get("/:id", controll.getItems);

router.post("/", groupAuth, controll.setItem);

module.exports = router;
