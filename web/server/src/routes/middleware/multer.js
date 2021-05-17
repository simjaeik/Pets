const multer = require("multer");
const multerS3 = require("multer-s3");
const aws = require("aws-sdk");
const dotenv = require("dotenv");

dotenv.config();

const s3 = new aws.S3({
  accessKeyId: process.env.IAM_SECRET_ID,
  secretAccessKey: process.env.IAM_SECRET_KEY,
  region: process.env.AWS_REGION,
});
