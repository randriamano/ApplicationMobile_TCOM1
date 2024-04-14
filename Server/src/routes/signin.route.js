const { Router } = require("express")

const signinController = require("../controllers/signin.controller")
const auth = require("../lib/auth")
const { extractData, successResponse } = require("../lib/helper")

const signinRouter = Router()

/** 
 * Route to signup new user
 */
signinRouter.post("/signup", auth, async function (req, res) {
  try {
    const data = JSON.parse(await extractData(req))
    const studentData = {
      studentName: data.studentName,
      studentFirstname: data.studentFirstname,
      studentCardNum: data.studentCardNum,
      studentPassword: data.studentPassword,
    }

    const student = await signinController.addStudent(studentData)

    res.json({
      sucess: true,
      student: student,
    })
    // res.json(successResponse("Student added", student))
  } catch (e) {
    res.status(404).json({ success: false })
    // if (e.message === 'Failed to parse URL from ') {
    //   res.status(404).json({ message: `The URL '${process.env.API_URL}' is invalid` })
    // } else {
    //   res.status(400).json({ message: "The request or data type is not correct" })
    // }
  }
})

/** 
 * Route to login user
 */
signinRouter.post("/login", auth, async function (req, res) {
  try {
    const data = JSON.parse(await extractData(req))
    const studentData = {
      studentCardNum: data.studentCardNum,
      studentPassword: data.studentPassword,
    }

    const student = await signinController.verifyStudent(studentData)

    if (student.length === 0) {
      // res.status(404).json({ message: "Student not found" })
      res.status(404).json({ success: false })
    }
    else {
      // res.json(successResponse("Student logged", student))
      res.json({
        success: true,
        student: student,
      })
    }
  } catch (e) {
    res.status(400).json({ success: false })
  }
})

module.exports = signinRouter
