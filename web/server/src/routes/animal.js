// Dependencies
const express = require("express");
const router = express.Router();

// Controller
const controller = require("../controller/animal");
const { upload } = require("./middleware/multer");

const groupAuth = require("./middleware/group-auth");

// Aniaml
router.get("/all/:id", groupAuth, controller.getAnimalByGroup);

router.get("/:id", controller.getAnimal);

router.post("/:id", upload.single("img"), groupAuth, controller.addAnimal);

router.patch(
  "/:id",
  upload.single("img"),
  groupAuth,
  controller.updateAnimalDetail
);

router.delete("/:id", groupAuth, controller.deleteAnimal);

// Memo

router.get("/memo/:id", controller.getMemos);

router.post("/memo/:id", groupAuth, controller.setMemo);

router.patch("/memo/:id", groupAuth, controller.updateMemo);

router.delete("/memo/:id", groupAuth, controller.deleteMemo);

// MedicalHistory

router.get("/medical/:id", controller.getMedicalHistories);

router.post("/medical/:id", groupAuth, controller.setMedicalHistory);

router.patch("/medical/:id", groupAuth, controller.updateMedicalHistory);

router.delete("/medical/:id", groupAuth, controller.deleteMedicalHistory);

module.exports = router;
