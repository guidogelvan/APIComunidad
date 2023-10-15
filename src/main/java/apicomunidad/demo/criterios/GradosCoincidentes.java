package apicomunidad.demo.criterios;

import apicomunidad.demo.models.ComunidadModel;

import java.util.Objects;

public class GradosCoincidentes implements CriterioFusion{
    @Override
    public Boolean esCoincidente(ComunidadModel comunidad1, ComunidadModel comunidad2) {
            return Objects.equals(comunidad1.getGradoDeConfianza(), comunidad2.getGradoDeConfianza());
    }
}
