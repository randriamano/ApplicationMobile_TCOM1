/**
 * Middleware to verify the key in the request
 */
module.exports = (req, res, next) => {
  const key = req.query.key

  if (key !== 'admin') {
    res.status(401).json({ message: "Don't have authorization to this ressources" })
  }
  else {
    next()
  }
}
