// Dependencies
const Sequelize = require("sequelize");

// Config
const { dbConfig } = require("../config");

// DB
const sequelize = new Sequelize(dbConfig);
const { Animal, MedicalHistory, Memo } = require("./animal")(
  sequelize,
  Sequelize
);
const { Post, PostImage, Comment, Favorite } = require("./post")(
  sequelize,
  Sequelize
);
const { Group, GalleryImage, ItemHistory, MemberGroup } = require("./group")(
  sequelize,
  Sequelize
);
const { Member } = require("./member")(sequelize, Sequelize);

// initDB
const aniaml = require("./animal")(sequelize, Sequelize);
const post = require("./post")(sequelize, Sequelize);
const group = require("./group")(sequelize, Sequelize);
const member = require("./member")(sequelize, Sequelize);

const initDB = { aniaml, post, group, member };

const db = {
  Animal,
  MedicalHistory,
  Memo,
  Post,
  PostImage,
  Comment,
  Favorite,
  Member,
  Group,
  GalleryImage,
  ItemHistory,
  MemberGroup,
};

Object.keys(initDB).forEach((modelName) => {
  console.log(initDB[modelName]);
  if (initDB[modelName].associate) {
    initDB[modelName].associate(db);
  }
});

db.sequelize = sequelize;
db.Sequelize = Sequelize;

module.exports = db;
