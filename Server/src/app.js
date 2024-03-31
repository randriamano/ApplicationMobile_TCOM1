const path = require('node:path')

const express = require('express')

// initialize express app
const app = express()

const logger = (req, res, next) => {
  console.log(`${req.method} ${req.url}`);
  next()
}

// parse json
app
  .use(logger)

// Import all the routes
require('./routes/route')(app)

module.exports = app
