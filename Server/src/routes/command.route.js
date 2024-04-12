const { Router } = require("express")

const commandController = require("../controllers/command.controller")
const auth = require("../lib/auth")
const { extractData, successResponse } = require("../lib/helper")

const commandRouter = Router()

/** 
 * Route to get commanded product
 */
 commandRouter.get('/', auth, async (req, res) => {
  const result = await commandController.getCommandedProduct();

  res.json({
    commandedProduct: result,
  });
})

/** 
 * Route to buy product
 */
commandRouter.post('/:productId', auth, async (req, res) => {
  try {
    const data = JSON.parse(await extractData(req))
    const productData = {
      productId: data.id,
    }

    const product = await commandController.buyProduct(productData)

    res.json(successResponse("Product buyed", product))
  } catch (e) {
    res.status(400).json({ message: "The request or data type is not correct" })
  }
})

module.exports = commandRouter
