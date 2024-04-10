/**
 * Middleware to verify the key in the request
 */
module.exports = async (req, res, next) => {
  const key = req.query.key
  let admin = []
  
  if (key) {
    admin = await prisma.admin.findMany({
      where: { key: key },
      select: {
        name: true,
      },
    })
  }

  if (admin.length === 0) {
    res.status(401).json({ message: "Don't have authorization to this ressources" })
  }
  else {
    next()
  }
}
