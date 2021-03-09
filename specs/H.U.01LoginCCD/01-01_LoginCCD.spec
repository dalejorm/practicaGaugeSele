H.U.1.0_LoginCCD
===============
Created by aramirez on 12/11/2020

Scenario Funcionalidad de login correcto
--------------------------
* Given que el usuario quiere loguearse en CCD
* When el activa la opcion de ingresar
* And el complementa formulario con "1116545831"
* And el ingresa sus credenciales "Isabella11"
* Then el usuario "1116545831" se logea y observa las opciones de CCD

Scenario Funcionalidad de login incorrecto
--------------------------
* Given que el usuario quiere loguearse en CCD
* When el activa la opcion de ingresar
* And el complementa formulario con "10538204569"
* Then el usuario observa mensaje de usuario no registrado

//Scenario Funcionalidad de login correcto
//--------------------------
//* Given que el usuario "IGAC" quiero consultar el servicio de tramite
//* When el consuma el recurso rest ""
//* Then el servicio me retorna "" información