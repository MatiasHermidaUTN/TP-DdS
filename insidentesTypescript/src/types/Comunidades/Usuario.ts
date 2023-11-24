import {
    Entity,
    PrimaryGeneratedColumn,
    Column,
    ManyToOne,
    OneToOne,
    ManyToMany,
    JoinTable,
  } from "typeorm";
  import { Perfil } from "./Perfil"; // Asegúrate de importar la clase Perfil correcta
  import { Entidad } from "../ServiciosPublicos/Entidad"; // Asegúrate de importar la clase Entidad correcta
  import { Servicio } from "../ServiciosPublicos/Servicio"; // Asegúrate de importar la clase Servicio correcta
  import { Incidente } from "../Incidentes/Incidente"; // Asegúrate de importar la clase Incidente correcta
  
  @Entity()
  export class Usuario {
    @PrimaryGeneratedColumn()
    id!: number;
  
    @Column()
    email: string;
  
    @Column()
    usuario: string;
  
    @Column()
    contrasenia: string;
  
    @Column()
    telefono!: number;
  
    @ManyToOne(() => Perfil, { eager: true })
    perfiles: Perfil[];

  
    @ManyToMany(() => Entidad, { cascade: true })
    @JoinTable()
    entidadesInteres: Entidad[];
  
    @ManyToMany(() => Servicio, { cascade: true })
    @JoinTable()
    serviciosInteres: Servicio[];
  
    @Column({ name: "tipo_usuario" })
    tipoUsuario!: string; // Asegúrate de que TipoUsuario tenga un tipo adecuado en TypeScript
  
    @ManyToMany(() => Incidente)
    @JoinTable({ name: "usuario_incidentes_nuevos" })
    incidentesNuevos!: Incidente[];
  
    @ManyToMany(() => Incidente)
    @JoinTable({ name: "usuario_incidentes_concluidos" })
    incidentesConcluidos!: Incidente[];
  
    constructor(email: string, usuario: string, contrasenia: string) {
      this.email = email;
      this.usuario = usuario;
      this.contrasenia = contrasenia;
      this.perfiles = [];
      this.entidadesInteres = [];
      this.serviciosInteres = [];
    }

    setId(id: number) {
      this.id = id;
    }

    getId() {
      return JSON.stringify({
        id: this.id,
      });
    }
  
    agregarIncidenteNuevo(incidente: Incidente) {
      this.incidentesNuevos.push(incidente);
    }
  
    removerIncidenteNuevo(incidente: Incidente) {
      const index = this.incidentesNuevos.indexOf(incidente);
      if (index !== -1) {
        this.incidentesNuevos.splice(index, 1);
      }
    }
  
    agregarIncidenteConcluido(incidente: Incidente) {
      this.incidentesConcluidos.push(incidente);
    }
  
    incidentesNotificados() {
      this.incidentesNuevos = [];
      this.incidentesConcluidos = [];
    }
  
    agregarPerfil(perfil: Perfil) {
      this.perfiles.push(perfil);
    }
  
    agregarEntidadInteres(entidad: Entidad) {
      this.entidadesInteres.push(entidad);
    }
  
    eliminarEntidadInteres(entidad: Entidad) {
      const index = this.entidadesInteres.indexOf(entidad);
      if (index !== -1) {
        this.entidadesInteres.splice(index, 1);
      }
    }
  
    agregarServicioInteres(servicio: Servicio) {
      this.serviciosInteres.push(servicio);
    }
  
    eliminarServicioInteres(servicio: Servicio) {
      const index = this.serviciosInteres.indexOf(servicio);
      if (index !== -1) {
        this.serviciosInteres.splice(index, 1);
      }
    }
  
    toString() {
      return JSON.stringify({
        id: this.id,
        usuario: this.usuario,
      });
    }
  }
  