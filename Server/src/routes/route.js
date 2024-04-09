const path = require('node:path')
const fs = require('node:fs')

const productsRouter = require('./products.route')

module.exports = (app, public) => {
  // Sample hello world
  app.get('/', (req, res) => {
    res.sendFile(path.join(public, 'index.html'))
  })

  // Products api endpoints
  app.use('/api/products', productsRouter)

  // URL to send image
  app.get('/images/:productId', (req, res) => {
    try {
      const path = req.path.split('/')
      const imageId = path[path.length - 1]
      const imagePath = `${public}/img/${imageId}`
      const image = fs.readFileSync(imagePath)

      res.contentType('image/jpeg').send(image)
    } catch (e) {
      res.status(404).json({ message: "Image doesn't exist"})
    }
  })

  // Error for undefined endpoints
  app.all('*', (req, res) => {
    res.status(404).json({ message: "The URL is undefined" })
  })
}
