const { Router } = require("express")

const commandController = require("../controllers/command.controller")
const auth = require("../lib/auth")
const { extractData } = require("../lib/helper")

const commandRouter = Router()

/** 
 * Route to get commanded product
 */

 commandRouter.get('/:category', async (req, res) => {
  const category = req.params.category
  const result = await commandController.getCommandedProduct(category);

  res.json({
    products: result,
  });
})

/** 
 * Route to command product
 */
commandRouter.post('/', auth, async (req, res) => {
  try {
    const data = JSON.parse(await extractData(req))
    const productData = {
      productId: data.productId,
      studentId: data.studentId,
      productSizeChosen: data.productSizeChosen,
      productColorChosen: data.productColorChosen, 
    }

    const product = await commandController.commandProduct(productData)

    res.json({
      success: true,
      products: product,
    })
  } catch (e) {
    res.status(400).json({
      success: false,
      products: {
        commandId: -1,
        productId: -1,
        studentId: -1,
        productSizeChosen: "",
        productColorChosen: "",
        commandDate: "",
    },
    })
  }
})

module.exports = commandRouter
