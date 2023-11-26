import { AppDataSource } from "./data-source"
import { User } from "./entity/User"
import { Usuario } from "./types/Comunidades/Usuario";

AppDataSource.initialize().then(async () => {

    // console.log("Inserting a new user into the database...")
    // const user = new User()
    // user.firstName = "Timber"
    // user.lastName = "Saw"
    // user.age = 25
    // await AppDataSource.manager.save(user)
    // console.log("Saved a new user with id: " + user.id)

    // console.log("Loading users from the database...")
    // const users = await AppDataSource.manager.find(User)
    // console.log("Loaded users: ", users)
    
    const user = new Usuario("pepex@gmail.com", "pepe", "pepe123")
    await AppDataSource.manager.save(user)
    console.log("El usuario pepe fue guardado")
    console.log("Here you can setup and run express / fastify / any other framework.")

}).catch(error => console.log(error))

// import express from 'express';
// import { createConnection } from 'typeorm';
import { Liquid } from 'liquidjs';
import "reflect-metadata";

// const app = express();
const port = 3000;

// TypeORM configuration
// createConnection()
//   .then(() => {
//     console.log('Connected to the database');
//   })
//   .catch((error) => console.error('TypeORM connection error: ', error));

// LiquidJS configuration
const engine = new Liquid();
// app.engine('liquid', engine.express());
// app.set('views', './views');
// app.set('view engine', 'liquid');

// // Express route
// app.get('/', (req, res) => {
//   res.render('index', { message: 'Hello, TypeScript!' });
// });

// // Start the server
// app.listen(port, () => {
//   console.log(`Server is running at http://localhost:${port}`);
// });