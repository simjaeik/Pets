// Dependencies
const express = require("express");

// Controller
const controller = require("../controller/animal");
const router = express.Router();

const group_auth = require("./middleware/group-auth");

router.get("/all/:id", group_auth, controller.getAnimalByGroup);

router.get("/:id", controller.getAnimal);


module.exports = router;
