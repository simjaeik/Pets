// Dependencies
const express = require("express");

// Controller
const controller = require("../controller/animal");
const router = express.Router();

const group_auth = require("./middleware/group-auth");

router.get("/all/:id", group_auth, controller.getAnimalByGroup);

router.get("/:id", controller.getAnimal);

router.post("/:id", group_auth, controller.addAnimal);

router.patch("/:id", group_auth, controller.updateAnimalDetail);

router.delete("/:id", group_auth, controller.deleteAnimal);

module.exports = router;
