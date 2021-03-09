Integración servicios de entidades
===============
Created by aramirez on 04/03/2021

Scenario servicio REST MEN
--------------------------
* Given el usuario quiere ver la informacion "http://34.231.111.66/r1/CO-QA/GOB/MINEDUCACION-0011/M2APWSALL/wsServicio/CC/" con headers "x-road-client:CO-QA/GOB/AND-8309/CARPETA;Content-Type:application/json" de la entidad "MEN"
* When el usuario "1152445938" realice la invocacion del servicio
* Then el usuario visualizara la informacion del servicio

Scenario servicio REST CNTS 1 Antecedentes
--------------------------
* Given el usuario quiere ver la informacion "http://34.231.111.66/r1/CO-QA/GOB/CNTS-0001/EJERZO_LEGALMENTE/wsCertificadoAntecedentesServicio/CC/" con headers "x-road-client:CO-QA/GOB/AND-8309/CARPETA;Content-Type:application/json" de la entidad "CNTS 1"
* When el usuario "26230800" realice la invocacion del servicio
* Then el usuario visualizara la informacion del servicio

Scenario servicio REST CNTS 2 Inscripción
--------------------------
* Given el usuario quiere ver la informacion "http://34.231.111.66/r1/CO-QA/GOB/CNTS-0001/EJERZO_LEGALMENTE/wsCertificadoInscripcionServicio/CC/" con headers "x-road-client:CO-QA/GOB/AND-8309/CARPETA;Content-Type:application/json" de la entidad "CNTS 2"
* When el usuario "26230800" realice la invocacion del servicio
* Then el usuario visualizara la informacion del servicio