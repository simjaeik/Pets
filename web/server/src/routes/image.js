// Dependencies
const express = require("express");
const { upload } = require("./middleware/multer");

// Controller
const controller = require("../controller/image");

const router = express.Router();

router.get("/all/:id", controller.getGalleryImages);

module.exports = router;
