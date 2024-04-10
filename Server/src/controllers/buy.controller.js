const prisma = require("../database/db")

const buyController = {}

/**
 * Buy product
 */
buyController.buyProduct = async (productData) => {
  // Buy product from prisma
  const product = await prisma.buy.create({
    data: { productData }
  })

  return product
}

module.exports = buyController
