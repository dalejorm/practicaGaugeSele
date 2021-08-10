Integración servicios de entidades
===============
Created by aramirez on 04/03/2021

//Scenario servicio REST MEN
//--------------------------
//* Valores servicios "respuestas"
//|CAMPO|VALOR|
//|Tipo de Documento|CC|
//|Documento de Identidad|1152445938|
//|País Documento de Identidad|Colombia|
//|Departamento Documento de Identidad|Amazonas|
//|Primer Nombre|juan|
//|Segundo Nombre|prueba|
//|Primer Apellido|prueba|
//
//* Given el usuario quiere ver la informacion "r1/CO-PRE/GOB/MINEDUCACION-0011/M2APWSALL/wsServicio/CC/" con headers "x-road-client:CO-PRE/GOB/AND-8309/CCD;Content-Type:application/json" de la entidad "MEN"
//* When el usuario "1152445938" realice la invocacion del servicio
//* Then el usuario visualizara la informacion del servicio


Scenario servicio REST CNTS 1 Antecedentes
--------------------------
* Valores servicios "respuestas"
|CAMPO|VALOR|
|id|103474|
|Número de Identificación|26230800|
|Primer Nombre|Luima|
|Segundo Nombre|Desyre|
|Primer Apellido|Martínez|
|Año de Grado|1997|
|Universidad|Universidad Del Sinú / Montería|

* Given el usuario quiere ver la informacion "r1/CO-PRE/GOB/CNTS-0001/EJERZO_LEGALMENTE/wsCertificadoAntecedentesServicio/CC/" con headers "x-road-client:CO-PRE/GOB/AND-8309/CCD;Content-Type:application/json" de la entidad "CNTS 1"
* When el usuario "26230800" realice la invocacion del servicio
* Then el usuario visualizara la informacion del servicio

Scenario servicio REST CNTS 2 Inscripción
--------------------------
* Valores servicios "respuestas"
|CAMPO|VALOR|
|id|103474|
|Número de Identificación|26230800|
|Primer Nombre|Luima|
|Segundo Nombre|Desyre|
|Primer Apellido|Martínez|
|Año de Grado|1997|
|Universidad|Universidad Del Sinú / Montería|

* Given el usuario quiere ver la informacion "r1/CO-PRE/GOB/CNTS-0001/EJERZO_LEGALMENTE/wsCertificadoInscripcionServicio/CC/" con headers "x-road-client:CO-PRE/GOB/AND-8309/CCD;Content-Type:application/json" de la entidad "CNTS 2"
* When el usuario "26230800" realice la invocacion del servicio
* Then el usuario visualizara la informacion del servicio