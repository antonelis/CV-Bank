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

//file-controller
app.get('/downloadFile/{fileId}', (req, res) => {
  //Kod kommer
})
app.post('/uploadMultipleFiles', (req, res) => {
  //Kod kommer
})




app.listen(3000) //Port 8080 ligger för frontend
connection.end()