const uuid = require("uuid");

module.exports = (sequelize, DataTypes) => {
  const Member = sequelize.define("Member", {
    UID: {
      type: DataTypes.UUID,
      defaultValue: DataTypes.UUIDV4,
      primaryKey: true,
      allowNull: false,
    },
    name: { type: DataTypes.STRING(30), allowNull: false },
    password: { type: DataTypes.STRING(100), allowNull: false },
    email: { type: DataTypes.STRING(50), allowNull: false },
    nickName: { type: DataTypes.STRING(30), allowNull: false },
  });

  const DB = { Member };

  DB.associate = (db) => {
    db.Member.hasMany(db.MemberGroup, { foreignKey: "UID" });
    db.Member.hasMany(db.Comment, { foreignKefy: "UID" });
  };
  return DB;
};
