package apicomunidad.demo.criterios;

import apicomunidad.demo.models.ComunidadModel;
import apicomunidad.demo.models.MiembroModel;

import java.util.ArrayList;


public class MiembrosCoincidentes implements CriterioFusion{
    private static final Float porcentajeMiembros = 0.05F;
    @Override
    public Boolean esCoincidente(ComunidadModel comunidad1, ComunidadModel comunidad2) {
        ArrayList<MiembroModel> miembros1 = comunidad1.getMiembros();
        ArrayList<MiembroModel> miembros2 = comunidad2.getMiembros();
        ArrayList<MiembroModel> miembrosCoincidentes = new ArrayList<>();

        for(MiembroModel m1: miembros1){
            for(MiembroModel m2: miembros2){
                if(m1.getId().equals(m2.getId())){
                    miembrosCoincidentes.add(m1);
                }
            }
        }
        return porcentajeMiembrosCoincidentes(miembros1, miembros2,miembrosCoincidentes) > porcentajeMiembros;
    }

    public Float porcentajeMiembrosCoincidentes(ArrayList<MiembroModel> miembrosComunidad1,
                                                ArrayList<MiembroModel> miembrosComunidad2,
                                                ArrayList<MiembroModel> miembrosCoincidentes){
        ArrayList<MiembroModel> miembrosSinRepetir = new ArrayList<>(miembrosComunidad1);
        for(MiembroModel m: miembrosComunidad1){
            for(MiembroModel m2: miembrosComunidad2){
                if(!(m.getId().equals(m2.getId())) && miembrosSinRepetir.stream().noneMatch(a -> a.getId().equals(m2.getId()))){
                    miembrosSinRepetir.add(m2);
                }
            }

        }
        return (float) (miembrosCoincidentes.size() / miembrosSinRepetir.size());
    }
}
