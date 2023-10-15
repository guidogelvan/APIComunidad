package apicomunidad.demo.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class PeticionModel {
    private ArrayList<PropuestaDeFusionModel> propuestas;
    private ArrayList<ComunidadModel> comunidades;
}
