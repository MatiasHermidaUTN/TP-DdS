import {
    Entity,
    PrimaryGeneratedColumn,
    Column,
    ManyToOne,
    JoinColumn,
    OneToMany,
  } from "typeorm";

  import { Establecimiento } from "../ServiciosPublicos/Establecimiento"; // Asegúrate de importar la clase Establecimiento correcta
  import { Servicio } from "../ServiciosPublicos/Servicio"; // Asegúrate de importar la clase Servicio correcta

  import { Prestacion } from "./Prestacion"; // Asegúrate de importar la clase Prestacion correcta
  import EstadoIncidente from "./EstadoIncidente"; // Importa el enum EstadoIncidente adecuado
  
  @Entity()
  export class Incidente {
    @PrimaryGeneratedColumn()
    id!: number;
  
    @Column({ name: "estado_incidente" })
    estado: EstadoIncidente;
  
    @ManyToOne(() => Prestacion, { eager: true })
    @JoinColumn({ name: "prestacion_id" })
    prestacion: Prestacion;
  
    constructor(
      prestacion: Prestacion,
    ) {
      this.prestacion = prestacion;
      this.estado = EstadoIncidente.ABIERTO;
    }
  
    getEstablecimientoNombre(): string {
      return this.prestacion.establecimiento.nombre;
    }
  
    getServicioNombre(): string {
      return this.prestacion.servicio.nombre;
    }

    getEstablecimiento(): Establecimiento {
      return this.prestacion.establecimiento;
    }
  
    getServicio(): Servicio {
      return this.prestacion.servicio;
    }

  
    estaResuelto(): boolean {
      return this.estado === EstadoIncidente.RESUELTO;
    }
  
    toString(): string {
      return JSON.stringify({
        estado: this.estado,
        prestacion: this.prestacion,
      });
    }
  }
  
  export default Incidente;
  