const prisma = require("../database/db")

const commandController = {}

/**
 * Get all commanded products
 */
commandController.getCommandedProduct = async (category) => {
  let commanded = []
  // Get command from prisma
  const results = await prisma.command.findMany({
    include: {
      product: true,
      student: true
    }
  })
  
  results.forEach((result) => {
    const { product } = result
    
    if (product.productCategory === category) {
      commanded.push({
        productId: product.productId,
        productName: product.productName,
        productPrice: product.productPrice,
        productDescription: product.productDescription,
        productSizeChosen: result.productSizeChosen,
        productColorChosen: result.productColorChosen,
        productCategory: product.productCategory,
        productIsPaid: false,
      })
    }
  });
  
  return commanded
}

/**
 * Buy product
 */
commandController.commandProduct = async (productData) => {
  // Buy product from prisma
  const product = await prisma.command.create({
    data: productData
  })

  return product
}

module.exports = commandController
