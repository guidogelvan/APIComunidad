package apicomunidad.demo.models;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter

public class ComunidadModel {
    private Long id;
    private String nombre;
    private ArrayList<MiembroModel> miembros = new ArrayList<>();
    private ArrayList<EstablecimientoModel> establecimientosObservados = new ArrayList<>();
    private ArrayList<ServicioModel> serviciosObservados = new ArrayList<>();
    private String gradoDeConfianza;

}
