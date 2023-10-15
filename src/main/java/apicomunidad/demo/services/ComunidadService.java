package apicomunidad.demo.services;

import apicomunidad.demo.criterios.*;
import apicomunidad.demo.models.*;

import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;


@Setter
@Service
public class ComunidadService {
        private ArrayList<CriterioFusion> criterios = new ArrayList<>();
        private static final Integer mesesEntrePropuestas = 6;



//    // ME FIJO SI LAS COMUNIDADES CUMPLEN AMBAS CONDICIONES
//    public Boolean cumplenCondiciones(ArrayList<PropuestaDeFusionModel> propuestas,
//                                      ComunidadModel comunidad, ComunidadModel comunidad2){
//        return !masDeUnaPropuestaActiva(propuestas,comunidad) &&
//                !masDeUnaPropuestaActiva(propuestas,comunidad2) &&
//                !hayPropuestaRepetidaMenos6Meses(propuestas,comunidad, comunidad2);
//    }

    // ACA ME FIJO SI ALGUNA DE LAS COMUNIDADES DE LA PROPUESTA YA TIENE UNA ACTIVA (CONDICION 1)
    public Boolean masDeUnaPropuesta(ArrayList<PropuestaDeFusionModel> propuestas,
                                             ComunidadModel comunidadModel){
        for(PropuestaDeFusionModel p: propuestas){
            if(p.getComunidadesAFusionar().stream().anyMatch(c -> c.getId().equals(comunidadModel.getId()))){
                return true;
            }
        }
        return false;
    }

    // ACA ME FIJO SI HAY UNA PROPUESTA REPETIDA EN MENOS DE 6 MESES (CONDICION 2)

    public Boolean hayPropuestaRepetidaMenosXMeses(ArrayList<PropuestaDeFusionModel> propuestas,
                                                   ComunidadModel comunidad1, ComunidadModel comunidad2){
        for(PropuestaDeFusionModel p: propuestas){ //RECORRO TODAS LAS PROPUESTAS
            if(tienenMismasComunidades(p,comunidad1, comunidad2)){ // SI LAS PROPUESTAS SON CON LAS MISMAS 2 COMUNIDADES
                if(!p.masDeXMesesDePropuesta(mesesEntrePropuestas)){ // SI PASO MENOS DE 6 MESES DE LA PROPUESTA
                    return true;
                }
            }
        }
        return false;
    }

    // ACA ME FIJO SI CUMPLE EL CRITERIO ELEGIDO (COINCIDEN EN ESTABLECIMIENTOS, SERVICIOS, ETC)
    public Boolean cumpleCriterios(ComunidadModel comunidad1, ComunidadModel comunidad2){
        this.criterios.add(new EstablecimientosCoincidentes());
        this.criterios.add(new ServiciosCoincidentes());
        this.criterios.add(new GradosCoincidentes());
        this.criterios.add(new MiembrosCoincidentes());

        return criterios.stream().allMatch(c-> c.esCoincidente(comunidad1,comunidad2));
    }

    public Boolean tienenMismasComunidades(PropuestaDeFusionModel p, ComunidadModel c1, ComunidadModel c2){
        //List<String> nombresCdePropuesta1 = p.getComunidadesAFusionar().stream().map(ComunidadModel::getNombre).toList();
        List<Long> idsCdePropuesta1 = p.getComunidadesAFusionar().stream().map(ComunidadModel::getId).toList();

        return idsCdePropuesta1.contains(c1.getId()) && idsCdePropuesta1.contains(c2.getId());
    }

    public ComunidadModel fusionarComunidades(PropuestaDeFusionModel propuesta){
        ComunidadModel comunidad1 = propuesta.getComunidadesAFusionar().get(0);
        ComunidadModel comunidad2 = propuesta.getComunidadesAFusionar().get(1);

                ComunidadModel comunidadFusionada = new ComunidadModel();
        ArrayList<EstablecimientoModel> establecimientosFusionados = establecimientosSinRepetir(comunidad1.getEstablecimientosObservados(),
                                                                                            comunidad2.getEstablecimientosObservados());

        ArrayList<ServicioModel> serviciosFusionados = serviciosSinRepetir(comunidad1.getServiciosObservados(),
                                                                            comunidad2.getServiciosObservados());

        ArrayList<MiembroModel> miembrosFusionados = miembrosSinRepetir(comunidad1.getMiembros(),
                                                                         comunidad2.getMiembros());
                comunidadFusionada.setId(comunidad1.getId() + comunidad2.getId()); // Revisar esto
                comunidadFusionada.setNombre(comunidad1.getNombre() + " - " + comunidad2.getNombre());
                comunidadFusionada.setServiciosObservados(serviciosFusionados);
                comunidadFusionada.setEstablecimientosObservados(establecimientosFusionados);
                comunidadFusionada.setMiembros(miembrosFusionados);
                comunidadFusionada.setGradoDeConfianza(comunidad1.getGradoDeConfianza());

                return comunidadFusionada;
    }

    public ArrayList<PropuestaDeFusionModel> sugerirFusiones(PeticionModel peticion){
        ArrayList<PropuestaDeFusionModel> propuestasCreadas = new ArrayList<>();
        for(ComunidadModel c1: peticion.getComunidades()){
            for(ComunidadModel c2: peticion.getComunidades()){
                if(!hayPropuestaRepetidaMenosXMeses(peticion.getPropuestas(),c1,c2)
                        && !masDeUnaPropuesta(propuestasCreadas,c1)  // Revisar esto, puede tirar error por ver una lista que se modifica
                        && !masDeUnaPropuesta(propuestasCreadas, c2)){
                    if(cumpleCriterios(c1,c2)) {
                        if (!c1.getId().equals(c2.getId())) {
                            PropuestaDeFusionModel nuevaPropuesta = new PropuestaDeFusionModel();
                            nuevaPropuesta.agregarComunidad(c1);
                            nuevaPropuesta.agregarComunidad(c2);
                            propuestasCreadas.add(nuevaPropuesta);
                        }
                    }
                }

            }
        }
        return propuestasCreadas;
    }

    public ArrayList<ServicioModel> serviciosSinRepetir(ArrayList<ServicioModel> serviciosComunidad1,
                                                        ArrayList<ServicioModel> serviciosComunidad2){
        ArrayList<ServicioModel> serviciosSinRepeticiones = new ArrayList<>(serviciosComunidad1);

        for(ServicioModel s: serviciosComunidad1){
            for(ServicioModel s2: serviciosComunidad2){
                if(!(s.getId().equals(s2.getId())) && serviciosSinRepeticiones.stream().noneMatch(a -> a.getId().equals(s2.getId()))){
                    serviciosSinRepeticiones.add(s2);
                }
            }

        }
        return serviciosSinRepeticiones;
    }
    public ArrayList<EstablecimientoModel> establecimientosSinRepetir(ArrayList<EstablecimientoModel> establecimientosComunidad1,
                                                                      ArrayList<EstablecimientoModel> establecimientosComunidad2){
        ArrayList<EstablecimientoModel> establecimientosSinRepeticiones = new ArrayList<>(establecimientosComunidad1);

        for(EstablecimientoModel e: establecimientosComunidad1){
            for(EstablecimientoModel e2: establecimientosComunidad2){
                if(!(e.getId().equals(e2.getId())) && establecimientosSinRepeticiones.stream().noneMatch(a -> a.getId().equals(e2.getId()))){
                    establecimientosSinRepeticiones.add(e2);
                }
            }

        }
        return establecimientosSinRepeticiones;
    }
    public ArrayList<MiembroModel> miembrosSinRepetir(ArrayList<MiembroModel> miembrosComunidad1,
                                                      ArrayList<MiembroModel> miembrosComunidad2){
        ArrayList<MiembroModel> miembrosSinRepeticiones = new ArrayList<>(miembrosComunidad1);

        for(MiembroModel m: miembrosComunidad1){
            for(MiembroModel m2: miembrosComunidad2){
                if(!(m.getId().equals(m2.getId())) && miembrosSinRepeticiones.stream().noneMatch(a -> a.getId().equals(m2.getId()))){
                    miembrosSinRepeticiones.add(m2);
                }
            }

        }
        return miembrosSinRepeticiones;
    }

}




