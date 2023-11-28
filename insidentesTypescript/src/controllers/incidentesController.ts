// Importa los módulos necesarios
import { Request, Response } from "express";
import { AppDataSource } from "../data-source";
import { Incidente } from "../types/Incidentes/Incidente"; // Asegúrate de ajustar la importación según la ubicación de tu modelo

let a = 1;

class IncidenteFormateado {
  id: number;
  servicioNombre: string;
  establecimientoNombre: string;
  estado: string;

  constructor(
    id: number,
    servicioNombre: string,
    establecimientoNombre: string,
    estado: string
  ) {
    this.id = id;
    this.servicioNombre = servicioNombre;
    this.establecimientoNombre = establecimientoNombre;
    this.estado = estado;
  }
}

// Define el controlador
export const incidentesController = async (
  req: Request,
  res: Response
): Promise<void> => {
  try {
    // Recupera todos los incidentes desde la base de datos
    const incidentes = await AppDataSource.manager.find(Incidente);

    const incidentesFormateados = incidentes.map((incidente) => {
      return new IncidenteFormateado(
        incidente.id,
        incidente.getServicioNombre(),
        incidente.getEstablecimientoNombre(),
        incidente.estado
      );
    });

    console.log(a, "incidentes:", incidentes);
    console.log(a++, "incidentesFormateados:", incidentesFormateados);

    // Renderiza la vista con los incidentes
    res.render("incidentes", { incidentesFormateados }); // Asegúrate de ajustar el nombre de la vista según tu configuración
  } catch (error) {
    // Maneja el error de manera apropiada
    console.error(error);
    res.status(500).send("Error interno del servidor");
  }
};

// Otros controladores pueden ser necesarios dependiendo de las acciones que desees realizar
