// Dependencies
const express = require("express");
const { upload } = require("./middleware/multer");

// Controller
const controller = require("../controller/image");

const router = express.Router();

router.get("/all/:id", controller.getGalleryImages);

router.get("/:id", controller.getGalleryImage);

router.post("/", upload.single("img"), controller.setGalleryImage);

router.patch("/:id", upload.single("img"), controller.updateGalleryImage);

router.delete("/:id", controller.deleteGalleryImage);

module.exports = router;
