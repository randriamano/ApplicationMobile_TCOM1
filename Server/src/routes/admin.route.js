const { Router } = require("express")

const adminController = require("../controllers/admin.controller")
const { extractData } = require("../lib/helper")

const adminRouter = Router()

/** 
 * Route to login admin
 */
adminRouter.post("/login", async function (req, res) {
    const data = JSON.parse(await extractData(req))
    const adminData = {
      studentCardNum: data.studentCardNum,
      adminPassword: data.adminPassword,
    }

    const admin = await adminController.verifyAdmin(adminData)

    if (admin.length === 0) {
      res.status(404).json({
        success: false,
        admin: {
          adminId: -1,
          studentCardNum: "",
          adminPassword: "",
          adminKey: "",
        }
      })
    }
    else {
      res.json({
        success: true,
        admin: admin[0],
      })
    }

})

module.exports = adminRouter
