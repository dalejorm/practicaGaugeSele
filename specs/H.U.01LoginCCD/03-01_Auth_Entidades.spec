Ingreso desde entidades
===============
Created by aramirez on 04/03/2021

Scenario Funcionalidad Nivel de confianza medio
--------------------------
* Valores usuario "userRegistro"
|CAMPO|VALOR|
|Tipo de Documento|CC|
|Documento de Identidad|115244593|
|Mes de Expedicion|January|
|Dia de Expedicion|1|
|Año de Expedicion|2010|
|Email|usertest@and.gov.co|
|Primer Nombre|N1Test|
|Segundo Nombre|N2Test|
|Primer Apellido|prueba1|
|Segundo Apellido|prueba2|
|Telefono|3214569789|
|Pais|Colombia|
|Departamento|Caldas|
|Municipio|Manizales|
|Direccion|Avenida siempre viva|

* Given que el usuario quiere loguearse en "https://qaclienttest.and.gov.co/login2"
* When el usuario realice el ciclo de registro en el nivel de confianza "medio"
* Then el usuario diligencia registro correctamente