const prisma = require("../database/db")

const commandController = {}

/**
 * Get all commanded products
 */
commandController.getCommandedProduct = async () => {
  // Get command from prisma
  const commanded = await prisma.command.findMany({
    include: {
      product: true,
      student: true
    }
  })

  return commanded
}

/**
 * Buy product
 */
commandController.buyProduct = async (productData) => {
  // Buy product from prisma
  const product = await prisma.command.create({
    data: { productData }
  })

  return product
}

module.exports = commandController
