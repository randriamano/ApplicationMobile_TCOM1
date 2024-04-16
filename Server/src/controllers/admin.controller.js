const bcrypt = require("bcrypt")

const prisma = require("../database/db")

const adminController = {}

adminController.verifyAdmin = async (adminData) => {
  // Verify admin from prisma
  const admin = await prisma.admin.findMany({
    where: { studentCardNum: adminData.studentCardNum },
  })

  if (!bcrypt.compareSync(adminData.adminPassword, admin[0].adminPassword)) {
    return []
  }

  return admin
}

module.exports = adminController
