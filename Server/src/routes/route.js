const goodiesRouter = require('./goodies.route')

module.exports = (app) => {
  // sample hello world
  app.get('/', (req, res) => {
    res.send("<p>Hello World !</p>")
  })

  // goodies api endpoints
  app.use('/api/goodies', goodiesRouter)

  // error for undefined endpoints
  app.all('*', (req, res) => {
    res.status(404).send("<h1>Not found</h1>")
  })
}
