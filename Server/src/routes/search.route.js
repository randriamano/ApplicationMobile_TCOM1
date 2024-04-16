const { Router } = require("express");

const searchController = require("../controllers/search.controller");

const searchRouter = Router();

/**
 * Route to get searched items
 */
searchRouter.get("/:keyword", async function (req, res) {
  const keyword = req.params.keyword
  const result = await searchController.getProductsByKeyword(keyword);

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

module.exports = searchRouter;
