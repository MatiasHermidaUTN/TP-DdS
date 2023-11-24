import { Entity, PrimaryGeneratedColumn, Column, ManyToOne } from "typeorm";
import { Usuario } from "../Comunidades/Usuario"; // Asegúrate de importar la clase Usuario correcta
import { Incidente } from "./Incidente";

@Entity()
export class Observacion {
  @PrimaryGeneratedColumn()
  id!: number;

  @ManyToOne(() => Usuario, { eager: true })
  usuario: Usuario;
  // ojo que puede faltar un @ManyToOne que va a incidente
  incidente!: Incidente;

  @Column()
  observado: string;

  constructor(usuario: Usuario, observado: string) {
    this.usuario = usuario;
    this.observado = observado;
  }
}

export default Observacion;
