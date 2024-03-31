const prisma = require("../database/db")

const goodiesController = {}

// Il faut entrourer tous les requêtes vers prisma dans un bloc try/catch

/**
 * Get all goodies
 */
goodiesController.getAllGoodies = async (filter) => {
  // Get all goodies from prisma
  const goodies = await prisma.goodies.findMany()
  
  return goodies
}

/**
 * Get a goodie item
 */
goodiesController.getGoodieById = async (id) => {
  // Get the specified goodie from prisma
  const goodie = await prisma.goodies.findUnique({
    where: { id: Number(id) },
    select: {
      name: true,
      price: true,
    },
  })

  return goodie
}

/**
 * Add a goodie item
 */
goodiesController.addGoodie = async (goodieData) => {
  // Add new goodie from prisma
  const goodie = await prisma.goodies.create({
    data: {
      name: goodieData.name,
      price: goodieData.price,
    }
  })

  return goodie
}

/**
 * Update goodie item
 */
goodiesController.updateGoodie = async ({ goodieId, goodieData }) => {
  // Update goodie data from prisma
  const goodie = await prisma.goodies.update({
    where: { id: Number(goodieId) },
    data: goodieData,
  })

  return { id: goodieId, ...goodieData }
}

/**
 * Delete goodie item
 */
goodiesController.deleteGoodie = async (goodieId) => {
  // Delete goodie from prisma
  const deletedItem = await prisma.goodies.delete({
    where: { id: Number(goodieId) }
  })
  
  return deletedItem
}

module.exports = goodiesController
