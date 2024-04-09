const path = require('node:path')

const express = require('express')
const helmet = require('helmet')

// Initialize express app
const app = express()

app.use(helmet())

// Path to the public directory
const public = path.join(__dirname, 'public')

// Import all the routes
require('./routes/route')(app, public)

module.exports = app
