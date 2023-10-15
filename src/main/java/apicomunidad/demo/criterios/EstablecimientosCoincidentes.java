package apicomunidad.demo.criterios;

import apicomunidad.demo.models.ComunidadModel;
import apicomunidad.demo.models.EstablecimientoModel;
import apicomunidad.demo.models.MiembroModel;

import java.util.ArrayList;


public class EstablecimientosCoincidentes implements CriterioFusion{
    private static final Float porcentajeEstablecimientos = 0.75F;
    @Override
    public Boolean esCoincidente(ComunidadModel comunidad1, ComunidadModel comunidad2) {
        ArrayList<EstablecimientoModel> establecimientos1 = comunidad1.getEstablecimientosObservados();
        ArrayList<EstablecimientoModel> establecimientos2 = comunidad2.getEstablecimientosObservados();
        ArrayList<EstablecimientoModel> entidadesCoincidentes = new ArrayList<>();

        for(EstablecimientoModel e1: establecimientos1){
            for(EstablecimientoModel e2: establecimientos2){
                if(e1.getId().equals(e2.getId())){
                    entidadesCoincidentes.add(e1);
                    entidadesCoincidentes.add(e2);
                }
            }
        }
        return porcentajCoincidente(establecimientos1, establecimientos2,entidadesCoincidentes) > porcentajeEstablecimientos;
    }



    public Float porcentajCoincidente(ArrayList<EstablecimientoModel> establecimientosComunidad1,
                                      ArrayList<EstablecimientoModel> establecimientosComunidad2,
                                      ArrayList<EstablecimientoModel> establecimientosCoincidentes){

        ArrayList<EstablecimientoModel> establecimientosSinRepetir = new ArrayList<>(establecimientosComunidad1);

        for(EstablecimientoModel e: establecimientosComunidad1){
            for(EstablecimientoModel e2: establecimientosComunidad2){
                if(!(e.getId().equals(e2.getId())) && establecimientosSinRepetir.stream().noneMatch(a -> a.getId().equals(e2.getId()))){
                    establecimientosSinRepetir.add(e2);
                }
            }

        }

        return (float) (establecimientosCoincidentes.size() / establecimientosSinRepetir.size());
    }
}
