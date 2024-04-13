const fs = require("node:fs");

const prisma = require("../database/db");

const productsController = {};

/**
 * Get all products
 */
productsController.getAllProducts = async (filter) => {
  // Get all products from prisma
  const products = await prisma.product.findMany({
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

/**
 * Get a product filterd by category
 */
productsController.getProductByCategory = async (category) => {
  let products = []

  // Get the specified product from prisma
  const results = await prisma.product.findMany({
    where: { productCategory: category },
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

  return results;
};

/**
 * Get a product item
 */
productsController.getProductById = async (productId) => {
  // Get the specified product from prisma
  const product = await prisma.product.findUnique({
    where: { productId: Number(productId) },
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

  return product;
};

/**
 * Add a product item
 */
productsController.addProduct = async (productData, imagesNumber) => {
  const now = new Date();
  const productID = `${productData.name}_${now.getTime()}`;
  const imagePath = `/images/${productID}/0`;
  let imagesURL = [imagePath];

  for (let i = 1; i <= imagesNumber; i++) {
    imagesURL.push(`${imagePath}/${i}`);
  }

  // Add new product from prisma
  const product = await prisma.product.create({
    data: { ...productData, productImageURLList: imagesURL },
  });

  let public = __dirname.split("\\");
  public.pop();
  public = public.join("\\");
  const imageDir = `${public}\\public\\img\\${productID}`;

  fs.mkdirSync(imageDir);

  return product;
};

/**
 * Update product item
 */
productsController.updateProduct = async ({ productId, productData }) => {
  // Update product data from prisma
  const product = await prisma.product.update({
    where: { productId: Number(productId) },
    data: productData,
  });

  return product;
};

/**
 * Delete product item
 */
productsController.deleteProduct = async (productId) => {
  // Delete product from prisma
  const deletedItem = await prisma.product.delete({
    where: { productId: Number(productId) },
  });

  return deletedItem;
};

module.exports = productsController;
