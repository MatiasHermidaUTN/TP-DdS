package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.repositorios.RepoEntidad;
import ar.edu.utn.frba.dds.models.repositorios.RepoOrganismoDeControl;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

public class CargadorCSVController {
    private RepoEntidad repoEntidad;
    private RepoOrganismoDeControl repoOrganismoDeControl;

    public CargadorCSVController(RepoEntidad repoEntidad, RepoOrganismoDeControl repoOrganismoDeControl) {
        this.repoEntidad = repoEntidad;
        this.repoOrganismoDeControl = repoOrganismoDeControl;
    }

    public void index(Context context) {
        context.render("cargaDatos/cargaDatos.hbs");
    }
    public void cargarDatos(Context context) {
        String redirectScript;
        UploadedFile file = context.uploadedFile("csvFile");
        if(file == null || file.size() == 0){
            redirectScript = """
                    <script>
                    window.alert(\"No se cargo un archivo, volve a intentar\");
                    setTimeout(function() { window.location.href = '/cargarDatos'; }, 0);
                    </script>
                    """;
        } else {
            System.out.println(file.filename());
            // aca va la implementacion del lectorCSV pero solamente tengo el nombre del archivo
            redirectScript = """
                    <script>
                    window.alert(\"Archivo cargado exitosamente\");
                    setTimeout(function() { window.location.href = '/cargarDatos'; }, 0);
                    </script>
                    """;
        }
        context.html(redirectScript);
        //TODO b. Carga masiva de datos de entidades prestadoras y organismos de control
    }
}
