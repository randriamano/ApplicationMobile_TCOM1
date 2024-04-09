const { Router } = require("express")

const productsController = require("../controllers/products.controller")
const auth = require("../lib/auth")
const { extractData, successResponse } = require("../lib/helper")

const productsRouter = Router()

/** 
 * Route to get all products 
 */
productsRouter.get("/", async function (req, res) {
  const result = await productsController.getAllProducts()

  if (result.length === 0) {
    res.json({ message: "Products list is empty" })
  } else {
    res.json(successResponse("Products list", result))
  }
})

/** 
 * Route to get a product item 
 */
productsRouter.get("/:productId", async function (req, res) {
  const productId = req.params.productId
  const product = await productsController.getProductById(productId)

  if (product === null) {
    res.status(404).json({ message: "Product is not found" })
  } else {
    res.json(successResponse("View product", product))
  }
})

/** 
 * Route to add product item 
 */
productsRouter.post("/", auth, async function (req, res) {
  try {
    const data = JSON.parse(await extractData(req))
    const productData = {
      name: data.name,
      price: data.price,
      description: data.description,
      category: data.category,
      // image: image,
    }

    const product = await productsController.addProduct(productData)

    res.json(successResponse("Product added", product))
  } catch (e) {
    res.status(400).json({ message: "The request or data type is not correct" })
  }
})

/**
 * Route to get modify product item 
 */
productsRouter.put("/:productId", auth, async function (req, res) {
  try {
    const productId = req.params.productId
    const data = JSON.parse(await extractData(req))
    const product = await productsController.updateProduct({
      productId: productId,
      productData: { ...data },
    })

    res.json(successResponse("Product updated", product))
  } catch (e) {
    res.status(400).json({ message: "The request or data type is not correct" })
  }
})

/**
 * Route to get delete product item
 */
productsRouter.delete("/:productId", auth, async function (req, res) {
  try {
    const productId = req.params.productId
    const deleteResult = await productsController.deleteProduct(productId)

    res.json(successResponse("Product deleted", deleteResult))
  } catch (e) {
    res.status(403).json({ message: "Can not delete this product" })
  }
})

module.exports = productsRouter
