const fs = require('node:fs')

const prisma = require("../database/db")

const productsController = {}

/**
 * Get all products
 */
productsController.getAllProducts = async (filter) => {
  // Get all products from prisma
  const products = await prisma.product.findMany({
    select: {
      id: true,
      name: true,
      price: true,
      image: true,
    }
  })
  
  return products
}

/**
 * Get a product item
 */
productsController.getProductById = async (productId) => {
  // Get the specified product from prisma
  const product = await prisma.product.findUnique({
    where: { id: Number(productId) },
  })

  return product
}

/**
 * Add a product item
 */
productsController.addProduct = async (productData, otherImgNumber) => {
  const now = new Date()
  const productID = `${productData.name}_${now.getTime()}`
  const imagePath = `/images/${productID}`
  let otherImages = [ imagePath ]

  for (let i = 1; i <= otherImgNumber; i++) {
    otherImages.push(`${imagePath}/${i}`) 
  }

  // Add new product from prisma
  const product = await prisma.product.create({
    data: { ...productData, otherImages: otherImages, image: imagePath }
  })

  let public = __dirname.split('\\')
  public.pop()
  public = public.join('\\')
  const imageDir = `${public}\\public\\img\\${productID}`
  
  fs.mkdirSync(imageDir)

  return product
}

/**
 * Update product item
 */
productsController.updateProduct = async ({ productId, productData }) => {
  // Update product data from prisma
  const product = await prisma.product.update({
    where: { id: Number(productId) },
    data: productData,
  })

  return product
}

/**
 * Delete product item
 */
productsController.deleteProduct = async (productId) => {
  // Delete product from prisma
  const deletedItem = await prisma.product.delete({
    where: { id: Number(productId) }
  })
  
  return deletedItem
}

module.exports = productsController
