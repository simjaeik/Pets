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

const upload = multerS3({
  s3: s3,
  bucket: "lacucpets/pets",
  acl: "public-read-write",
  metadata: (req, file, cb) => {
    cb(null, { fieldName: file.fieldname });
  },
  key: (req, file, cb) => {
    cb(null, Date.now() + "." + file.originalname);
  },
});

exports.upload = multer({ storage: upload });
