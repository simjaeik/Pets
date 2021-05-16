// Dependencies
const express = require("express");
const groupAuth = require("./middleware/group-auth");

// Controller
const controller = require("../controller/group");

const router = express.Router();

router.get("/", controller.getMyGroups);

router.get("/:id", controller.getGroup);

router.post("/", controller.setGroup);

router.post("/member/:id", groupAuth, controller.addGroupMember);

router.patch("/:id", groupAuth, controller.updateGroup);

router.patch("/share/:id", groupAuth, controller.toggleShare);

router.delete("/:id", groupAuth, controller.deleteGroup);

module.exports = router;
