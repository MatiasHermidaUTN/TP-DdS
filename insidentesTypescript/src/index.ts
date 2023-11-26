import express from 'express';
import { createConnection } from 'typeorm';
import { Liquid } from 'liquidjs';
import "reflect-metadata";

const app = express();
const port = 3000;

// TypeORM configuration
createConnection()
  .then(() => {
    console.log('Connected to the database');
  })
  .catch((error) => console.error('TypeORM connection error: ', error));

// LiquidJS configuration
const engine = new Liquid();
app.engine('liquid', engine.express());
app.set('views', './views');
app.set('view engine', 'liquid');

// Express route
app.get('/', (req, res) => {
  res.render('index', { message: 'Hello, TypeScript!' });
});

// Start the server
app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});