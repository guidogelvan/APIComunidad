Link a la documentación de la API con Swagger: http://localhost:8080/doc/swagger-ui/index.html

GET localhost:8080/comunidad/sugerirFusiones
Se le pasa por el body una petición que tiene propuestas viejas y las comunidades que se quieren analizar si pueden fusionarse. La API responde con una lista de propuestas de peticiones nueva.

GET localhost:8080/comunidad/fusionarComunidades
Se le pasa por el body una propuesta de fusión (Contiene las 2 comunidades a fusionar). La API devuelve la comunidad fusionada.


Ejemplo Peticion sugerirFusiones:

{
  "propuestas": [
       {
  "comunidadesAFusionar": [
    {
      "nombre": "Comunidad3",
      "miembros": [
        {
          "nombre": "string"
        }
      ],
      "establecimientosObservados": [
        {
          "nombre": "string"
        }
      ],
      "serviciosObservados": [
        {
          "nombre": "string"
        }
      ],
      "gradoDeConfianza": "string"
    },
    {
      "nombre": "Comunidad4",
      "miembros": [
        {
          "nombre": "string"
        }
      ],
      "establecimientosObservados": [
        {
          "nombre": "string"
        }
      ],
      "serviciosObservados": [
        {
          "nombre": "string"
        }
      ],
      "gradoDeConfianza": "string"
    }
  ],
  "fechaPropuesta": "2023-09-10 14:30:14"
}
  ],
  "comunidades": [
    {
                "nombre": "Comunidad1",
                "miembros": [
                    {
                        "nombre": "Juan"
                    },
                    {
                        "nombre": "Pedro"
                    }
                ],
                "establecimientosObservados": [
                    {
                        "nombre": "Medrano"
                    },
                    {
                        "nombre": "Lugano"
                    }
                ],
                "serviciosObservados": [
                    {
                        "nombre": "Escalera"
                    },
                    {
                        "nombre": "Ascensor"
                    }
                ],
                "gradoDeConfianza": "Alto"
            },
    {
                "nombre": "Comunidad2",
                "miembros": [
                    {
                        "nombre": "Pedro"
                    },
                    {
                        "nombre": "Juan"
                    }
                ],
                "establecimientosObservados": [
                    {
                        "nombre": "Medrano"
                    },
                    {
                        "nombre": "Lugano"
                    }
                ],
                "serviciosObservados": [
                    {
                        "nombre": "Escalera"
                    },
                    {
                        "nombre": "Ascensor"
                    }
                ],
                "gradoDeConfianza": "Alto"
            }
  ]
}


Ejemplo para fusionarComunidades:

{
        "comunidadesAFusionar": [
            {
                "nombre": "Comunidad1",
                "miembros": [
                    {
                        "nombre": "Juan"
                    },
                    {
                        "nombre": "Pedro"
                    }
                ],
                "establecimientosObservados": [
                    {
                        "nombre": "Medrano"
                    },
                    {
                        "nombre": "Lugano"
                    }
                ],
                "serviciosObservados": [
                    {
                        "nombre": "Escalera"
                    },
                    {
                        "nombre": "Ascensor"
                    }
                ],
                "gradoDeConfianza": "Alto"
            },
            {
                "nombre": "Comunidad2",
                "miembros": [
                    {
                        "nombre": "Pedro"
                    },
                    {
                        "nombre": "Juan"
                    }
                ],
                "establecimientosObservados": [
                    {
                        "nombre": "Medrano"
                    },
                    {
                        "nombre": "Lugano"
                    }
                ],
                "serviciosObservados": [
                    {
                        "nombre": "Escalera"
                    },
                    {
                        "nombre": "Ascensor"
                    }
                ],
                "gradoDeConfianza": "Alto"
            }
        ],
        "fechaPropuesta": "2023-09-10 19:47:49"
    }