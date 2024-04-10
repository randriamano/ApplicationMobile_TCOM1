const http = require('node:http')

const dotenv = require('dotenv')

const app = require('./app')

dotenv.config()

// listnening port
const PORT = process.env.PORT || 8080

http.createServer(app)

const server = http.createServer(app)

server.listen(PORT, () => {
  console.log(`Listing on port ${PORT}`)
})
