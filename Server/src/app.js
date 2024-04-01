const path = require('node:path')

const express = require('express')

// Initialize express app
const app = express()

// Path to the public directory
const public = path.join(__dirname, 'public')

// Import all the routes
require('./routes/route')(app, public)

module.exports = app
