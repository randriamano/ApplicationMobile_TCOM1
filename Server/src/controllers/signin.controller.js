const prisma = require("../database/db")
const fetchAPI = require("../lib/fetchAPI")

const signinController = {}

/**
 * Add a new student 
 */
signinController.addStudent = async (studentData) => {
  // Verify student data from other api
  let match = false
  let studentName = studentData.studentName.toUpperCase()
  let studentId = studentData.studentCardNum
  studentId = studentId.split('-')

  const apiURL = process.env.API_URL

  studentInfo = await fetchAPI(apiURL)

  studentInfo.forEach((info) => {
    let id = info.id
    id = id.split('-');

    if (studentId[0] === id[0] & studentId[1].toUpperCase() === id[2]) {
      if (studentName === info.lastname || studentData.studentFirstname === info.firstname) {
        match = true
        return
      }
    }
  });

  if (!match)
    throw new Error()
  //

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
