import "reflect-metadata"
import { DataSource } from "typeorm"

export const AppDataSource = new DataSource({
    type: "mysql",
    host: "localhost",
    port: 3306,
    username: "root",
    password: "tpadds16",
    database: "test",
    synchronize: true,
    logging: false,
    entities: ["./src/types/**/*.ts"],
    migrations: [],
    subscribers: [],
})
