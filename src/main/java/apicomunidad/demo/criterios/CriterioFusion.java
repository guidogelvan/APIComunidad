package apicomunidad.demo.criterios;

import apicomunidad.demo.models.ComunidadModel;

public interface CriterioFusion {

    public Boolean esCoincidente(ComunidadModel comunidad1, ComunidadModel comunidad2);

}
