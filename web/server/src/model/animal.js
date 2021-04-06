module.exports = (sequelize, DataTypes) => {
  const Animal = sequelize.define("Animal", {
    AID: {
      type: DataTypes.INTEGER,
      autoIncreament: true,
      primaryKey: true,
    },
    name: { type: DataTypes.INTEGER, allowNull: false },
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
      type: DataTypes.INTEGER,
      autoIncreament: true,
      primaryKey: true,
    },
    date: { type: DataTypes.DATE, allowNull: false },
    title: { type: DataTypes.STRING(100), allowNull: false },
    content: { type: DataTypes.TEXT, allowNull: false },
    hospital: { type: DataTypes.STRING(100), allowNull: false },
  });

};
