const service = require("../service/group");
const control = require("../lib/controller");

module.exports = {
  getMyGroups: async (req, res) => {
    const { status, result } = await control(service.getMyGroups, req.data);

    return res.status(status).json(result);
  },

  getGroup: async (req, res) => {
    const { status, result } = await control(service.getGroup, req.params);

    return res.status(status).json(result);
  },

  getGroupMembers: async (req, res) => {
    const { status, result } = await control(
      service.getGroupMembers,
      req.params
    );

    return res.status(status).json(result);
  },

  setGroup: async (req, res) => {
    const { status, result } = await control(service.setGroup, {
      data: req.data,
      body: req.body,
    });

    return res.status(status).json(result);
  },

  addGroupMember: async (req, res) => {
    const { status, result } = await control(service.addGroupMember, {
      GID: req.gid,
      body: req.body,
    });

    return res.status(status).json(result);
  },

  updateGroup: async (req, res) => {
    const { status, result } = await control(service.updateGroup, {
      GID: req.gid,
      body: req.body,
    });

    return res.status(status).json(result);
  },

  toggleShare: async (req, res) => {
    const { status, result } = await control(service.toggleShare, {
      GID: req.gid,
      body: req.body,
    });

    return res.status(status).json(result);
  },

  deleteGroup: async (req, res) => {
    const { status, result } = await control(service.deleteGroup, req.gid);

    return res.status(status).json(result);
  },

  deleteGroupMember: async (req, res) => {
    const { status, result } = await control(service.deleteGroupMember, {
      GID: req.gid,
      UID: req.uid,
    });

    return res.status(status).json(result);
  },
};
