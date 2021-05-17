// Dependencies
const express = require("express");

// Controller
const controller = require("../controller/animal");
const router = express.Router();

const groupAuth = require("./middleware/group-auth");

// Aniaml
router.get("/all/:id", groupAuth, controller.getAnimalByGroup);

router.get("/:id", controller.getAnimal);

router.post("/:id", groupAuth, controller.addAnimal);

router.patch("/:id", groupAuth, controller.updateAnimalDetail);

router.delete("/:id", groupAuth, controller.deleteAnimal);

// Memo

router.get("/memo/:id", controller.getMemos);

router.post("/memo/:id", groupAuth, controller.setMemo);

router.patch("/memo/:id", groupAuth, controller.updateMemo);

router.delete("/:id", group_auth, controller.deleteAnimal);

module.exports = router;
