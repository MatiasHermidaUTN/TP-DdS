// Importa los módulos necesarios
import { Request, Response } from 'express';
import { getRepository } from 'typeorm';
import { Incidente } from '../types/Incidentes/Incidente'; // Asegúrate de ajustar la importación según la ubicación de tu modelo

// Define el controlador
export const listaIncidentes = async (req: Request, res: Response): Promise<void> => {
    try {
        // Recupera todos los incidentes desde la base de datos
        const incidentes = await getRepository(Incidente).find();

        // Renderiza la vista con los incidentes
        res.render('incidentes', { incidentes }); // Asegúrate de ajustar el nombre de la vista según tu configuración
    } catch (error) {
        // Maneja el error de manera apropiada
        console.error(error);
        res.status(500).send('Error interno del servidor');
    }
};

// Otros controladores pueden ser necesarios dependiendo de las acciones que desees realizar
