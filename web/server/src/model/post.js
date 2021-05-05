module.exports = (sequelize, DataTypes) => {
  const Post = sequelize.define("Post", {
    PID: {
      type: DataTypes.INTEGER,
      autoIncreament: true,
      primaryKey: true,
    },
    title: { type: DataTypes.STRING(50), allowNull: false },
    context: { type: DataTypes.TEXT, allowNull: false },
    date: { type: DataTypes.DATE, allowNull: false },
    category: { type: DataTypes.STRING(20), allowNull: false },
    view: { type: DataTypes.INTEGER, allowNull: false },
  });

  const PostImage = sequelize.define("PostImage", {
    IID: {
      type: DataTypes.INTEGER,
      autoIncreament: true,
      primaryKey: true,
    },
    url: { type: DataTypes.STRING(100), allowNull: false },
  });

  const Favorite = sequelize.define("Favorite", {
    GID: {
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true,
    },
    PID: {
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true,
    },
  });

  const Comment = sequelize.define("Comment", {
    CID: {
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true,
    },
    content: { type: DataTypes.TEXT, allowNull: false },
    date: { type: DataTypes.DATE, allowNull: false },
  });

  const DB = { Post, PostImage, Favorite, Comment };

  DB.associate = (db) => {
    db.Post.hasMany(db.PostImage, { foreignKey: "PID" });
    db.Post.hasMany(db.Favorite, { foreignKey: "PID" });
    db.Post.hasMany(db.Comment, { foreignKey: "PID" });

    db.Post.belongsTo(db.Group, { foreignKey: "GID" });
    db.Comment.belongsTo(db.Post, { foreignKey: "PID" });
  };
  return DB;
};
