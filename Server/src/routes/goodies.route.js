const { Router } = require("express")

const goodiesController = require("../controllers/goodies.controller")
const { extractData, successResponse } = require("../lib/helper")

const goodiesRouter = Router()

/** 
 * Route to get all goodies 
 */
goodiesRouter.get("/", async function (req, res) {
  const result = await goodiesController.getAllGoodies()

  if (result.length === 0) {
    res.json({ message: "Goodies list is empty" })
  } else {
    res.json(successResponse("Goodies list", result))
  }
})

/** 
 * Route to get a student item 
 */
goodiesRouter.get("/:goodieId", async function (req, res) {
  const goodieId = req.params.goodieId
  const goodie = await goodiesController.getGoodieById(goodieId)

  if (goodie === null) {
    res.status(404).json({ message: "Goodie is not found" })
  } else {
    res.json(successResponse("View goodie", goodie))
  }
})

/** 
 * Route to add goodie item 
 */
goodiesRouter.post("/", async function (req, res) {
  try {
    const data = JSON.parse(await extractData(req))
    const goodieData = {
      name: data.name,
      price: data.price,
    }

    const goodie = await goodiesController.addGoodie(goodieData)

    res.json(successResponse("Goodie added", goodie))
  } catch (e) {
    res.status(400).json({ message: "The request or data type is not correct" })
  }
})

/**
 * Route to get modify goodie item 
 */
goodiesRouter.put("/:goodieId", async function (req, res) {
  try {
    const goodieId = req.params.goodieId
    const data = JSON.parse(await extractData(req))
    const goodie = await goodiesController.updateGoodie({
      goodieId: goodieId,
      goodieData: { ...data },
    })

    res.json(successResponse("Goodie updated", goodie))
  } catch (e) {
    res.status(400).json({ message: "The request or data type is not correct" })
  }
})

/**
 * Route to get delete student item
 */
goodiesRouter.delete("/:goodieId", async function (req, res) {
  try {
    const goodieId = req.params.goodieId
    const deleteResult = await goodiesController.deleteGoodie(goodieId)

    res.json(successResponse("Goodie deleted", deleteResult))
  } catch (e) {
    res.status(403).json({ message: "Can not delete this goodie" })
  }
})

module.exports = goodiesRouter
