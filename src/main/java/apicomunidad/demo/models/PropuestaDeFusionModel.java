package apicomunidad.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Getter
@Setter
public class PropuestaDeFusionModel {
    private ArrayList<ComunidadModel> comunidadesAFusionar = new ArrayList<>();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaPropuesta = LocalDateTime.now();

    public Boolean masDeXMesesDePropuesta(Integer mes){
        return this.fechaPropuesta.plusMonths(mes).isBefore(LocalDateTime.now());
    }

    public void agregarComunidad(ComunidadModel comunidadModel){
        comunidadesAFusionar.add(comunidadModel);
    }
}
