const { Router } = require("express");

const searchController = require("../controllers/search.controller");
const auth = require("../lib/auth");
const { extractData, successResponse } = require("../lib/helper");

const searchRouter = Router();

/**
 * Route to get searched items
 */
searchRouter.get("/:keyword", async function (req, res) {
  const keyword = req.params.keyword
  const result = await searchController.getProductsByKeyword(keyword);

  if (result.length === 0) {
    res.json({ message: "Products list is empty" });
  } else {
    res.json({
      product: result,
      size: result.length,
    });
  }
});

module.exports = searchRouter;
