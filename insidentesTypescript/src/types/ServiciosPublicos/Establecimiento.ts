import { Entity, PrimaryGeneratedColumn, Column, ManyToOne, OneToOne, JoinColumn } from "typeorm";

import { Servicio } from "./Servicio";


@Entity()
export class Establecimiento {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column()
  nombre: string;

  servicios: Servicio[];

  constructor(nombre: string) {
    this.nombre = nombre;
    // this.servicios = [];
  }

  public tieneServicio(servicio: Servicio): boolean {
    return this.servicios.includes(servicio);
  }

  public agregarServicios(servicio1: Servicio, ...servicios: Servicio[]): void {
    this.servicios.push(servicio1, ...servicios);
  }

  public eliminarServicio(servicio: Servicio): void {
    const index = this.servicios.indexOf(servicio);
    if (index !== -1) {
      this.servicios.splice(index, 1);
    }
  }
}

export default Establecimiento;
