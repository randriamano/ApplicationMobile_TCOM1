const prisma = require("../database/db")
const fetchAPI = require("../lib/fetchAPI")

const signinController = {}

/**
 * Add a new student 
 */
signinController.addStudent = async (studentData) => {
  // Verify student data from other api
  // studentInfo = await fetchAPI('')

  // Add new student from prisma
  const student = await prisma.student.create({
    data: studentData
  })

  return student
}

/**
 * Verify student for login
 */
signinController.verifyStudent = async (studentData) => {
  // Verify student from prisma
  const student = await prisma.student.findMany({
    where: { cardNum: studentData.cardNum, password: studentData.password },
  })

  return student
}

module.exports = signinController
