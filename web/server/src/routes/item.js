// Dependencies
const express = require("express");
const groupAuth = require("./middleware/group-auth");

// Controller
const controll = require("../controller/item");

const router = express.Router();

router.get("/all/:id", controll.getItems);

router.get("/:id", controll.getItem);

router.post("/", groupAuth, controll.setItem);

router.patch("/:id", groupAuth, controll.updateItem);

router.delete("/:id", groupAuth, controll.deleteItem);

module.exports = router;
