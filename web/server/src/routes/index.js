// Dependencies
const express = require("express");
const jwtAuth = require("./middleware/jwt-auth");

// Router
const userRouter = require("./user");
const animalRouter = require("./animal");
const commentRouter = require("./comment");
const groupRouter = require("./group");
const imageRouter = require("./image");
const itemRouter = require("./item");
const postRouter = require("./post");

const router = express.Router();

router.use("/user", userRouter);
router.use(jwtAuth);
router.use("/animal", animalRouter);
router.use("/comment", commentRouter);
router.use("/group", groupRouter);
router.use("/image", imageRouter);
router.use("/item", itemRouter);
router.use("/post", postRouter);

module.exports = router;
