module.exports = (sequelize, DataTypes) => {
  const Animal = sequelize.define("Animal", {
    AID: {
      type: DataTypes.UUID,
      defaultValue: DataTypes.UUIDV4,
      allowNull: false,
      primaryKey: true,
    },
    name: { type: DataTypes.STRING(30), allowNull: false },
    age: { type: DataTypes.INTEGER, allowNull: false },
    sex: { type: DataTypes.STRING(10), allowNull: false },
    species: { type: DataTypes.STRING(100), allowNull: false },
    subspecies: { type: DataTypes.STRING(100), allowNull: false },
    weight: { type: DataTypes.FLOAT, allowNull: false },
    number: { type: DataTypes.STRING(100), allowNull: false },
    image: { type: DataTypes.STRING(100), allowNull: false },
  });

  const MedicalHistory = sequelize.define("MedicalHistory", {
    HID: {
      type: DataTypes.UUID,
      defaultValue: DataTypes.UUIDV4,
      allowNull: false,
      primaryKey: true,
    },
    date: { type: DataTypes.DATE, allowNull: false },
    title: { type: DataTypes.STRING(100), allowNull: false },
    content: { type: DataTypes.TEXT, allowNull: false },
    hospital: { type: DataTypes.STRING(100), allowNull: false },
  });

  const Memo = sequelize.define("Memo", {
    MID: {
      type: DataTypes.UUID,
      defaultValue: DataTypes.UUIDV4,
      allowNull: false,
      primaryKey: true,
    },
    content: {
      type: DataTypes.TEXT,
      allowNull: false,
    },
  });

  const DB = { Animal, MedicalHistory, Memo };

  DB.associate = (db) => {
    db.Animal.hasMany(db.MedicalHistory, {
      foreignKey: "AID",
      onDelete: "CASCADE",
    });

    db.Animal.belongsTo(db.Group, { foreignKey: "GID", onDelete: "CASCADE" });
    db.MedicalHistory.belongsTo(db.Animal, {
      foreignKey: "AID",
      onDelete: "CASCADE",
    });
    db.Memo.belongsTo(db.Animal, { foreignKey: "AID", onDelete: "CASCADE" });
  };
  return DB;
};
