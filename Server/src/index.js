const https = require('node:https')
const fs = require('node:fs')

const dotenv = require('dotenv')

const app = require('./app')

dotenv.config()

// listnening port
const PORT = process.env.PORT || 8080

// certificate for the https server
const options = {
  key: fs.readFileSync('config/cert/tco-key.pem'),
  cert: fs.readFileSync('config/cert/tco-cert.pem')
}

const server = https.createServer(options, app)

server.listen(PORT, () => {
  console.log(`Server listing to port ${PORT}`)
})
