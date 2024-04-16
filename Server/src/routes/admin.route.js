const { Router } = require("express")

const adminController = require("../controllers/admin.controller")
const auth = require("../lib/auth")
const { extractData, successResponse } = require("../lib/helper")

const adminRouter = Router()

/** 
 * Route to login admin
 */
adminRouter.post("/login", async function (req, res) {
  try {
    const data = JSON.parse(await extractData(req))
    const adminData = {
      studentCardNum: data.studentCardNum,
      adminPassword: data.adminPassword,
    }

    const admin = await adminController.verifyAdmin(adminData)

    if (admin.length === 0) {
      // res.status(404).json({ message: "Student not found" })
      res.status(404).json({ success: false })
    }
    else {
      // res.json(successResponse("Student logged", student))
      res.json({
        success: true,
        admin: admin,
      })
    }
  } catch (e) {
    res.status(400).json({ success: false })
  }
})

module.exports = adminRouter
