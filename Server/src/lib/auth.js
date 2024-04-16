const bcrypt = require("bcrypt")

/**
 * Middleware to verify the key in the request
 */
module.exports = async (req, res, next) => {
  const key = req.query.key
  let admin = []

  // if (key) {
  //   admin = await prisma.admin.findMany({
  //     where: { adminKey: key }
  //   })
  // }

  if (key) {
    allAdmin = await prisma.admin.findMany()
    allAdmin.forEach((administrator) => {
      if (bcrypt.compareSync(key, administrator.adminKey)) {
        admin = administrator
      }
    });
  }

  if (admin.length === 0) {
    res.status(401).json({ message: "Don't have authorization to this ressources" })
  }
  else {
    next()
  }
}
