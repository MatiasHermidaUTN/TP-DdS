import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToMany,
  ManyToOne,
  JoinColumn,
  OneToMany,
} from "typeorm";

import { Establecimiento } from "./Establecimiento";

@Entity({ name: "servicio" })
export class Servicio {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column()
  nombre: string;

  @ManyToOne(() => Establecimiento)
  @JoinColumn({ name: "establecimiento_id", referencedColumnName: "id" })
  establecimiento!: Establecimiento;

  constructor(nombre: string) {
    this.nombre = nombre;
  }
}
