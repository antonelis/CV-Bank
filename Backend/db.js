// const nodeFetch = require('node-fetch')

const express = require('express')
const cors = require('cors')
const mysql = require('mysql')

// Databas
let connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '',
  database: ''
})
connection.connect()

const app = express()
app.use(cors())

//Get, Post etc.
app.get('/', (req, res) => {
  //Kod kommer
})

app.post('/', (req, res) => {
  //Kod kommer
})





connection.end()