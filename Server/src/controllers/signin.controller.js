const prisma = require("../database/db")
const fetchAPI = require("../lib/fetchAPI")

const signinController = {}

/**
 * Add a new student 
 */
signinController.addStudent = async (studentData) => {
  const apiURL = process.env.API_URL
  // Verify student data from other api
  //studentInfo = await fetchAPI(apiURL)

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
    where: { studentCardNum: studentData.studentCardNum, studentPassword: studentData.studentPassword },
  })

  return student
}

module.exports = signinController
