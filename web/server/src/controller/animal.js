const service = require("../service/animal");
const control = require("../lib/controller");

module.exports = {
  // Animal
  getAnimalByGroup: async (req, res) => {
    const { status, result } = await control(service.getAnimalByGroup, req.gid);

    return res.status(status).json(result);
  },

  getAnimal: async (req, res) => {
    const { status, result } = await control(service.getAnimal, req.params);

    return res.status(status).json(result);
  },

  addAnimal: async (req, res) => {
    const { status, result } = await control(service.addAnimal, {
      GID: req.gid,
      body: req.body,
      file: req.file,
    });

    return res.status(status).json(result);
  },

  updateAnimalDetail: async (req, res) => {
    const { status, result } = await control(service.updateAnimalDetail, {
      AID: req.params,
      body: req.body,
      file: req.file,
    });

    return res.status(status).json(result);
  },

  deleteAnimal: async (req, res) => {
    const { status, result } = await control(service.deleteAnimal, req.params);

    return res.status(status).json(result);
  },

  // Memo
  getMemos: async (req, res) => {
    const { status, result } = await control(service.getMemos, req.params);

    return res.status(status).json(result);
  },

  getMemo: async (req, res) => {
    const { status, result } = await control(service.getMemo, req.params);

    return res.status(status).json(result);
  },

  setMemo: async (req, res) => {
    const { status, result } = await control(service.setMemo, {
      AID: req.params,
      body: req.body,
    });

    return res.status(status).json(result);
  },

  updateMemo: async (req, res) => {
    const { status, result } = await control(service.updateMemo, {
      MID: req.params,
      body: req.body,
    });

    return res.status(status).json(result);
  },

  deleteMemo: async (req, res) => {
    const { status, result } = await control(service.deleteMemo, req.params);

    return res.status(status).json(result);
  },

  // MedicalHistory
  getMedicalHistories: async (req, res) => {
    const { status, result } = await control(
      service.getMedicalHistories,
      req.params
    );

    return res.status(status).json(result);
  },

  getMedicalHistory: async (req, res) => {
    const { status, result } = await control(
      service.getMedicalHistory,
      req.params
    );

    return res.status(status).json(result);
  },

  setMedicalHistory: async (req, res) => {
    const { status, result } = await control(service.setMedicalHistory, {
      AID: req.params,
      body: req.body,
    });

    return res.status(status).json(result);
  },

  updateMedicalHistory: async (req, res) => {
    const { status, result } = await control(service.updateMedicalHistory, {
      HID: req.params,
      body: req.body,
    });

    return res.status(status).json(result);
  },

  deleteMedicalHistory: async (req, res) => {
    const { status, result } = await control(
      service.deleteMedicalHistory,
      req.params
    );

    return res.status(status).json(result);
  },
};
