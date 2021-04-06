module.exports = (sequelize, DataTypes) => {
  const Group = sequelize.define("Group", {
    GID: {
      type: DataTypes.INTEGER,
      autoIncreament: true,
      primaryKey: true,
    },
    name: { type: DataTypes.STRING(30), allowNull: false },
    info: { type: DataTypes.TEXT, allowNull: false },
    image: { type: DataTypes.STRING(100), allowNull: false },
    share: { type: DataTypes.BOOLEAN, allowNull: false },
    latitude: { type: DataTypes.FLOAT, allowNull: false },
    longitude: { type: DataTypes.FLOAT, allowNull: false },
  });

  const GalleryImage = sequelize.define("GalleryImage", {
    IID: {
      type: DataTypes.INTEGER,
      autoIncreament: true,
      primaryKey: true,
    },
    url: { type: DataTypes.STRING(100), allowNull: true },
  });

  const ItemHistory = sequelize.define("ItemHistory", {
    HID: {
      type: DataTypes.INTEGER,
      autoIncreament: true,
      primaryKey: true,
    },
    name: { type: DataTypes.STRING(50), allowNull: false },
    category: { type: DataTypes.STRING(50), allowNull: false },
    link: { type: DataTypes.STRING(100), allowNull: false },
    price: { type: DataTypes.INTEGER, allowNull: false },
  });

  const MemberGroup = sequelize.define("MemberGroup", {
    MID: {
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true,
    },
    GID: {
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true,
    },
    authority: { type: DataTypes.STRING(30), allowNull: false },
  });

  const DB = { Group, GalleryImage, ItemHistory, MemberGroup };

  DB.associate = (db) => {
    db.Group.hasMany(db.Animal, { foreignKey: "GID" });
    db.Group.hasMany(db.Post, { foreignKey: "GID" });
    db.Group.hasMany(db.Favorite, { foreignKey: "GID" });
    db.Group.hasMany(db.MemberGroup, { foreignKey: "GID" });

    db.GalleryImage.belongsTo(db.Group, { foreignKey: "GID" });
    db.ItemHistory.belongsTo(db.Group, { foreignKey: "GID" });
    db.MemberGroup.belongsTo(db.Group, { foreignKey: "GID" });
    db.MemberGroup.belongsTo(db.Member, { foreignKey: "UID" });
  };
  return DB;
};