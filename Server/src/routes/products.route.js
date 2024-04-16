const { Router } = require("express");

const productsController = require("../controllers/products.controller");
const auth = require("../lib/auth");
const { extractData } = require("../lib/helper");

const productsRouter = Router();

/**
 * Route to get all products
 */
productsRouter.get("/", async function (req, res) {
  const result = await productsController.getAllProducts();

  if (result.length === 0) {
    res.json({
      product: [],
      size: 0,
    });
  } else {
    res.json({
      product: result,
      size: result.length,
    });
  }
});

/**
 * Route to get a product item by id
 */
productsRouter.get("/:productId", async function (req, res) {
  const productId = req.params.productId;
  const product = await productsController.getProductById(productId);

  if (product === null) {
    res.status(404).json({
      productId: -1,
      productName: "",
      productPrice: "",
      productDescription: "",
      availableColorList: [],
      availableSizeList: [],
      productCategory: "",
      productRemainingStock: 0,
      productImageURLList: []
    });
  } else {
    res.json(product)
  }
});

/**
 * Route to get a product item by category
 */
productsRouter.get("/category/:category", async function (req, res) {
  const category = req.params.category;
  const product = await productsController.getProductByCategory(category);

  if (product === null) {
    res.status(404).json({
      product: [],
      size: 0,
    });
  } else {
    res.json({
      product: product,
      size: product.length,
    })
  }
});

/**
 * Route to add product item
 */
productsRouter.post("/", auth, async function (req, res) {
  try {
    const data = JSON.parse(await extractData(req));
    const productData = {
      productName: data.productName,
      productPrice: data.productPrice,
      productDescription: data.productDescription,
      availableColorList: data.availableColorList,
      availableSizeList: data.availableSizeList,
      productCategory: data.productCategory,
      productRemainingStock: data.productRemainingStock,
      // productImageURLList: [],
    };
    const imagesNumber = data.imagesNumber || 0;

    const product = await productsController.addProduct(
      productData,
      imagesNumber
    );

    res.json({
      success: true,
      product: product,
    })
  } catch (e) {
    res.status(400).json({
      success: false,
      product: {
          productId: -1,
          productName: "",
          productPrice: "",
          productDescription: "",
          productCategory: "",
          availableColorList: [],
          availableSizeList: [],
          productRemainingStock: -1,
          productImageURLList: [],
          productPostedDate: ""
      }
    });
  }
});

/**
 * Route to get modify product item
 */
productsRouter.put("/:productId", auth, async function (req, res) {
  try {
    const productId = req.params.productId;
    const data = JSON.parse(await extractData(req));
    const product = await productsController.updateProduct({
      productId: productId,
      productData: { ...data },
    });

    res.json({
      success: true,
    })
  } catch (e) {
    res.status(400).json({
      success: false,
    });
  }
});

/**
 * Route to get delete product item
 */
productsRouter.delete("/:productId", auth, async function (req, res) {
  try {
    const productId = req.params.productId;
    const deleteResult = await productsController.deleteProduct(productId);

    res.json({
      success: true
    })
  } catch (e) {
    res.status(403).json({
      success: false
    });
  }
});

module.exports = productsRouter;
