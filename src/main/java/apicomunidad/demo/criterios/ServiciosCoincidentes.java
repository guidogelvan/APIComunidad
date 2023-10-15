package apicomunidad.demo.criterios;

import apicomunidad.demo.models.ComunidadModel;
import apicomunidad.demo.models.MiembroModel;
import apicomunidad.demo.models.ServicioModel;

import java.util.ArrayList;


public class ServiciosCoincidentes implements CriterioFusion{
    private static final Float porcentajeServicios = 0.75F;
    @Override
    public Boolean esCoincidente(ComunidadModel comunidad1, ComunidadModel comunidad2) {
        ArrayList<ServicioModel> servicios1 = comunidad1.getServiciosObservados();
        ArrayList<ServicioModel> servicios2 = comunidad2.getServiciosObservados();
        ArrayList<ServicioModel> serviciosCoincidentes = new ArrayList<>();

        for(ServicioModel s1: servicios1){
            for(ServicioModel s2: servicios2){
                if(s1.getId().equals(s2.getId())){
                    serviciosCoincidentes.add(s1);
                }
            }
        }
        return porcentajeServiciosCoincidentes(servicios1, servicios2,serviciosCoincidentes) > porcentajeServicios;
    }

    public Float porcentajeServiciosCoincidentes(ArrayList<ServicioModel> serviciosComunidad1,
                                                 ArrayList<ServicioModel> serviciosComunidad2,
                                                 ArrayList<ServicioModel> serviciosCoincidentes){
        ArrayList<ServicioModel> serviciosSinRepetir = new ArrayList<>(serviciosComunidad1);

        for(ServicioModel s: serviciosComunidad1){
            for(ServicioModel s2: serviciosComunidad2){
                if(!(s.getId().equals(s2.getId())) && serviciosSinRepetir.stream().noneMatch(a -> a.getId().equals(s2.getId()))){
                    serviciosSinRepetir.add(s2);
                }
            }

        }

        return (float) (serviciosCoincidentes.size() / serviciosSinRepetir.size());
    }

}
