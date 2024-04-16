const prisma = require("../database/db")

const adminController = {}

adminController.verifyAdmin = async (adminData) => {
  // Verify admin from prisma
  const [ admin ] = await prisma.admin.findMany({
    where: { studentCardNum: adminData.studentCardNum, adminPassword: adminData.adminPassword },
  })

  return admin
}

module.exports = adminController
