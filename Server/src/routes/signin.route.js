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
      cardNum: data.cardNum,
      name: data.name,
      firstname: data.firstname,
      password: data.password,
    }

    const student = await signinController.addStudent(studentData)

    res.json(successResponse("Student added", student))
  } catch (e) {
    res.status(400).json({ message: "The request or data type is not correct" })
  }
})

/** 
 * Route to login user
 */
signinRouter.get("/login", auth, async function (req, res) {
  try {
    const data = JSON.parse(await extractData(req))
    const studentData = {
      cardNum: data.cardNum,
      password: data.password,
    }

    const student = await signinController.verifyStudent(studentData)

    if (student.length === 0) {
      res.status(404).json({ message: "Student not found" })
    }
    else {
      res.json(successResponse("Student logged", student))
    }
  } catch (e) {
    res.status(400).json({ message: "The request or data type is not correct" })
  }
})

module.exports = signinRouter
