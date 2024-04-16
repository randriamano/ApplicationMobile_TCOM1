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
    
    for (const key in studentData) {
      if (studentData[key] === "") {
        res.json({
          student: {
            studentId: -1,
            studentCardNum: "",
            studentName: "",
            studentFirstname: "",
            studentPassword: "",
          }
        })

        return
      }
    }

    const student = await signinController.addStudent(studentData)

    res.json({
      student: student,
    })
  } catch (e) {
    res.json({
      student: {
        studentId: -1,
        studentCardNum: "",
        studentName: "",
        studentFirstname: "",
        studentPassword: "",
      }
    })

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
signinRouter.post("/login", async function (req, res) {
  try {
    const data = JSON.parse(await extractData(req))
    const studentData = {
      studentCardNum: data.studentCardNum,
      studentPassword: data.studentPassword,
    }

    for (const key in studentData) {
      if (studentData[key] === "") {
        res.json({
          student: {
            studentId: -1,
            studentCardNum: "",
            studentName: "",
            studentFirstname: "",
            studentPassword: "",
          }
        })
        
        return
      }
    }

    const student = await signinController.verifyStudent(studentData)

    if (student.length === 0) {
      res.json({
        student: {
          studentId: -1,
          studentCardNum: "",
          studentName: "",
          studentFirstname: "",
          studentPassword: "",
        }
      })
    }
    else {
      res.json({
        student: student[0],
      })
    }
  } catch (e) {
    res.status(400).json({
      student: {
        studentId: -1,
        studentCardNum: "",
        studentName: "",
        studentFirstname: "",
        studentPassword: "",
      }
    })
  }
})

module.exports = signinRouter
