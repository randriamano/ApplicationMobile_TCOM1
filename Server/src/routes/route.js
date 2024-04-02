const path = require('node:path')

const goodiesRouter = require('./goodies.route')

module.exports = (app, public) => {
  // sample hello world
  app.get('/', (req, res) => {
    res.send("<p>Hello World !</p>")
  })

  // goodies api endpoints
  app.use('/api/goodies', goodiesRouter)

  // error for undefined endpoints
  app.all('*', (req, res) => {
    res.status(404).sendFile(path.join(public, 'error.html'))
  })
}
