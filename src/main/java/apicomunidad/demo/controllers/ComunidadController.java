package apicomunidad.demo.controllers;


import apicomunidad.demo.models.ComunidadModel;
import apicomunidad.demo.models.PeticionModel;
import apicomunidad.demo.models.PropuestaDeFusionModel;
import apicomunidad.demo.services.ComunidadService;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
@RequestMapping("/comunidad")
public class ComunidadController {
    private final ComunidadService comunidadService = new ComunidadService();

//    @GetMapping("/prueba")
//    public String prueba(){
//        return "Conecte";
//    }

    @GetMapping("/sugerirFusiones")
    public ArrayList<PropuestaDeFusionModel> sugerirFusiones(@RequestBody PeticionModel peticion){
        return comunidadService.sugerirFusiones(peticion);
    }

//    @GetMapping("/cumpleCriterios")
//    public Boolean cumpleCriterios(@RequestBody PropuestaDeFusionModel p){
//        return comunidadService.cumpleCriterios(p.getComunidadesAFusionar().get(0),p.getComunidadesAFusionar().get(1) );
//    }
//
//    @GetMapping("/cumpleCriterio1")
//    public Boolean cumpleCriterio1(@RequestBody PropuestaDeFusionModel p){
//        EstablecimientosCoincidentes e = new EstablecimientosCoincidentes();
//        return e.esCoincidente(p.getComunidadesAFusionar().get(0),p.getComunidadesAFusionar().get(1) );
//    }
//
//    @GetMapping("/cumpleCriterio2")
//    public Boolean cumpleCriterio2(@RequestBody PropuestaDeFusionModel p){
//        ServiciosCoincidentes e = new ServiciosCoincidentes();
//        return e.esCoincidente(p.getComunidadesAFusionar().get(0),p.getComunidadesAFusionar().get(1) );
//    }
//
//    @GetMapping("/cumpleCriterio3")
//    public Boolean cumpleCriterio3(@RequestBody PropuestaDeFusionModel p){
//        GradosCoincidentes e = new GradosCoincidentes();
//        return e.esCoincidente(p.getComunidadesAFusionar().get(0),p.getComunidadesAFusionar().get(1) );
//    }
//    @GetMapping("/cumpleCriterio4")
//    public Boolean cumpleCriterio4(@RequestBody PropuestaDeFusionModel p){
//        MiembrosCoincidentes e = new MiembrosCoincidentes();
//        return e.esCoincidente(p.getComunidadesAFusionar().get(0),p.getComunidadesAFusionar().get(1) );
//    }
    @GetMapping("/fusionarComunidades")
    public ComunidadModel fusionarComunidades(@RequestBody PropuestaDeFusionModel propuestaDeFusionModel){
        return comunidadService.fusionarComunidades(propuestaDeFusionModel);
    }

}
