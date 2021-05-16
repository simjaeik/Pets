module.exports = (sequelize, DataTypes) => {
  const Group = sequelize.define("_Group", {
    GID: {
      type: DataTypes.UUID,
      defaultValue: DataTypes.UUIDV4,
      allowNull: false,
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
      type: DataTypes.UUID,
      defaultValue: DataTypes.UUIDV4,
      allowNull: false,
      primaryKey: true,
    },
    GID: {
      type: DataTypes.UUID,
      references: {
        model: "_Group",
        key: "GID",
      },
      allowNull: false,
    },
    url: { type: DataTypes.STRING(100), allowNull: true },
    tag: { type: DataTypes.STRING(50), allowNull: true },
  });

  const ItemHistory = sequelize.define("ItemHistory", {
    HID: {
      type: DataTypes.UUID,
      defaultValue: DataTypes.UUIDV4,
      allowNull: false,
      primaryKey: true,
    },
    GID: {
      type: DataTypes.UUID,
      references: {
        model: "_Group",
        key: "GID",
      },
      allowNull: false,
    },
    name: { type: DataTypes.STRING(50), allowNull: false },
    category: { type: DataTypes.STRING(50), allowNull: false },
    link: { type: DataTypes.STRING(100), allowNull: false },
    price: { type: DataTypes.INTEGER, allowNull: false },
  });

  const MemberGroup = sequelize.define("MemberGroup", {
    UID: {
      type: DataTypes.UUID,
      allowNull: false,
      primaryKey: true,
    },
    GID: {
      type: DataTypes.UUID,
      allowNull: false,
      primaryKey: true,
    },
    authority: { type: DataTypes.STRING(30), allowNull: false },
  });

  const DB = { Group, GalleryImage, ItemHistory, MemberGroup };

  DB.associate = (db) => {
    db.Group.hasMany(db.Animal, { foreignKey: "GID", onDelete: "CASCADE" });
    db.Group.hasMany(db.Post, { foreignKey: "GID", onDelete: "CASCADE" });
    db.Group.hasMany(db.Favorite, { foreignKey: "GID", onDelete: "CASCADE" });
    db.Group.hasMany(db.MemberGroup, {
      foreignKey: "GID",
      onDelete: "CASCADE",
    });

    db.GalleryImage.belongsTo(db.Group, {
      foreignKey: "GID",
      onDelete: "CASCADE",
    });
    db.ItemHistory.belongsTo(db.Group, {
      foreignKey: "GID",
      onDelete: "CASCADE",
    });
    db.MemberGroup.belongsTo(db.Group, {
      foreignKey: "GID",
      onDelete: "CASCADE",
    });
    db.MemberGroup.belongsTo(db.Member, {
      foreignKey: "UID",
      onDelete: "CASCADE",
    });
  };
  return DB;
};
