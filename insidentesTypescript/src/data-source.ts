import "reflect-metadata"
import { DataSource } from "typeorm"

export const AppDataSource = new DataSource({
    type: "mysql",
    host: "localhost",
    port: 3306,
    username: "root",
    password: "tpadds16",
    database: "ddscopia3",
    synchronize: false,
    logging: false,
    entities: ["./src/types/**/*.ts"],
    migrations: [],
    subscribers: [],
})
