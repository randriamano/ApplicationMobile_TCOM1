const { Router } = require("express")

const buyController = require("../controllers/buy.controller")
const auth = require("../lib/auth")
const { extractData, successResponse } = require("../lib/helper")

const buyRouter = Router()

/** 
 * Route to buy product
 */
buyRouter.post('/:productId', auth, async (res, req) => {
  try {
    const data = JSON.parse(await extractData(req))
    const productData = {
      productId: data.id,
    }

    const product = await buyController.buyProduct(productData)

    res.json(successResponse("Product buyed", product))
  } catch (e) {
    res.status(400).json({ message: "The request or data type is not correct" })
  }
})

module.exports = buyRouter
