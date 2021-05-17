// Dependencies
const express = require("express");
const groupAuth = require("./middleware/group-auth");
const { upload } = require("./middleware/multer");

// Controller
const controller = require("../controller/group");

const router = express.Router();

router.get("/", controller.getMyGroups);

router.get("/:id", controller.getGroup);

router.get("/members/:id", controller.getGroupMembers);

router.post("/", upload.single("img"), controller.setGroup);

router.post("/member/:id", groupAuth, controller.addGroupMember);

router.patch("/:id", upload.single("img"), groupAuth, controller.updateGroup);

router.patch("/share/:id", groupAuth, controller.toggleShare);

router.delete("/:id", groupAuth, controller.deleteGroup);

router.delete("/member/:id", groupAuth, controller.deleteGroupMember);

module.exports = router;
