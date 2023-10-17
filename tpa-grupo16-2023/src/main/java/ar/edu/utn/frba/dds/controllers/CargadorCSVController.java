package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.lectorCSV.LectorCSV;
import ar.edu.utn.frba.dds.models.repositorios.RepoEntidad;
import ar.edu.utn.frba.dds.models.repositorios.RepoOrganismoDeControl;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import io.javalin.util.FileUtil;

import java.io.File;

public class CargadorCSVController {
    private RepoEntidad repoEntidad;
    private RepoOrganismoDeControl repoOrganismoDeControl;

    private LectorCSV lectorCSV = new LectorCSV();

    public CargadorCSVController(RepoEntidad repoEntidad, RepoOrganismoDeControl repoOrganismoDeControl) {
        this.repoEntidad = repoEntidad;
        this.repoOrganismoDeControl = repoOrganismoDeControl;
    }

    public void index(Context context) {
        context.render("cargaDatos/cargaDatos.hbs");
    }
    public void cargarDatos(Context context) throws Exception{
        String redirectScript;
        UploadedFile uploadedFile = context.uploadedFile("csvFile");
        if(uploadedFile == null || uploadedFile.size() == 0){
            redirectScript = """
                    <script>
                    window.alert(\"No se cargo un archivo, volve a intentar\");
                    setTimeout(function() { window.location.href = '/cargarDatos'; }, 0);
                    </script>
                    """;
        } else {
            System.out.println(uploadedFile.filename());
            String csvPath = "src/main/properties/csvs/" + uploadedFile.filename();
            FileUtil.streamToFile(uploadedFile.content(), csvPath);
            lectorCSV.leerCSV(csvPath);
            File file = new File(csvPath);
            file.delete();
            redirectScript = """
                    <script>
                    window.alert(\"Archivo cargado exitosamente\");
                    setTimeout(function() { window.location.href = '/cargarDatos'; }, 0);
                    </script>
                    """;
        }
        context.html(redirectScript);
    }
}
