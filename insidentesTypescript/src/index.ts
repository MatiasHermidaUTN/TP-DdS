import { AppDataSource } from "./data-source"
import { incidentesController } from "./controllers/incidentesController";

AppDataSource.initialize().then(async () => {
    
    // const user = new Usuario("pepex@gmail.com", "pepe", "pepe123")
    // await AppDataSource.manager.save(user)
    // console.log("El usuario pepe fue guardado")
    // console.log("Here you can setup and run express / fastify / any other framework.")

}).catch(error => console.log(error))

import express, {Request, Response} from 'express'; 
import { Liquid } from 'liquidjs';
import "reflect-metadata";
import path from "path";

const app = express();
const port = 3000;

// Configuración para servir archivos estáticos desde la carpeta 'public'
app.use(express.static(path.join(__dirname, 'public')));

// LiquidJS configuration
const engine = new Liquid({
  root: ['views/'],
  extname: '.liquid'
});

app.engine('liquid', engine.express());
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'liquid');

// Express route
app.get('/', incidentesController);


// Start the server
app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});