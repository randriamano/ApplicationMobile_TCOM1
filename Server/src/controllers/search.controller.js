const fs = require("node:fs");

const prisma = require("../database/db");

const searchController = {};

/**
 * Get all products
 */
searchController.getProductsByKeyword = async (keyword) => {
  // Get all products from prisma
  const products = await prisma.product.findMany({
    where: {
      OR: [
        { productName: { contains: keyword, mode: 'insensitive' } },
        { productCategory: { contains: keyword, mode: 'insensitive' } },
      ]
    },
    select: {
      productId: true,
      productName: true,
      productPrice: true,
      productDescription: true,
      availableColorList: true,
      availableSizeList: true,
      productCategory: true,
      productRemainingStock: true,
      productImageURLList: true,
    },
  });

  return products;
};

module.exports = searchController;
