Prueba de Ingreso Seti: (Desarrollo de Api-Rest Spring WebFlux)
------------------------------

Ejercicio # 1:
--------------------
Se requiere construir un API para manejar una lista de franquicias. Una franquicia se
compone por un nombre y un listado de sucursales y, a su vez, una sucursal está
compuesta
por un nombre y un listado de productos ofertados en la sucursal. Un producto se
componente de un nombre y una cantidad de stock.

Criterios de Aceptacion:
-------------------------------
1 Projecto desarrollado en SpringBoot usando programacion reactiva (WebFlux) sobre Arquitectura Hexagonal
-----------------------------------------------------------------------------------------------------------

* Se desarrolló bajo Patron de Arquitectura: Hexagonal.
La estructura arquitectónica del proyecto es asi:

<img width="496" height="724" alt="image" src="https://github.com/user-attachments/assets/05f5a868-ec63-423d-88f6-ca98f84fc63c" />

* Todo el sistema API-Rest fue desarrollado bajo WebFlux, para eso lo primero es asegurarse que en el POM,Xml del proyecto se tenga la dependencia de Spring WebFlux 

<img width="716" height="129" alt="image" src="https://github.com/user-attachments/assets/5fd9fd0d-3d1a-4b35-8a12-b2366e9a2b2a" />

2 Base de datos seleccionada: MySQL:
---------------------------------------

Como el desarrollo es bajo WebFlux (Rx), es fundmental trabajar con R2DBC (Reactive Relational Database Connectivity), puesto que MySQL por default gestiona con JDBC (el cual es bloquente) pero WebFlux es NO bloqueante
osea que R2DBC es el mecanismo usado por webflux para garantizar consultas a Bases de Datos de manera No bloqueantes (Soportando Mono / Flux) elementos fundamentales de RxJava y Project Reactor de Spring

<img width="1209" height="441" alt="image" src="https://github.com/user-attachments/assets/38545387-7b9f-4209-915d-51502d3f548c" />

Modelo Entidad Relacion (MER)

CREATE DATABASE IF NOT EXISTS seti_db;
USE seti_db;

CREATE TABLE `franchises` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `franchises` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `stock` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `branch_id` (`branch_id`),
  CONSTRAINT `branch_id` FOREIGN KEY (`branch_id`) REFERENCES `branches` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




3 Las respuestas de los servicios deben ser encadenadas con operadores MAP, FLATMAP, MERGE, Etc.
---------------------------------------------------------------------------------------------------

Todo el funcionamiento de los servicios de la capa Aplicaction (Services: FranchiseService, BranchService, ProductService) trabajan con streams encadenando la respuesta a Map, FlatMap, segun sea necesario

4 Use correctamente los señales onNext, onError, onComplete.
---------------------------------------------------------------

Todo el funcionamiento de los servicios de la capa Aplicaction (Services: FranchiseService, BranchService, ProductService) trabajan con streams encadenando la respuesta a Map, FlatMap, segun sea necesario

5 Pruebas Unitarias en Servicios (deben superar el 80% de cobertura):
-------------------------------------------------------------------------

Todos los servicios de la capa Application tienen cobertura del 85%, pruebas unitarias realizadas con Junit & Mockito, gracias al plugin de Jacoco, se puede observar la cobertura en formato HTML del site.
Comando para generar .jar incluyendo generacion de Site con Cobertura de Pruebas Unitarias:

Desde consola git_bash (preferible): ./mvnw clean install
------

<img width="1907" height="404" alt="image" src="https://github.com/user-attachments/assets/e3350c13-e283-4e9c-beb4-58ff3378f0d8" />


Funcionalidad:
----------------

Para garantizar la funcionalidad del proyecto, se deben ejecutar los siguientes comandos:

./mvnw clean install

Este comando generará el archivo .jar (Ejecutable) de la Api.jar dentro de la carpeta /target del pryecto

java -jar [prueba-0.0.1-SNAPSHOT.jar] (archivo .jar generado)

<img width="1785" height="531" alt="image" src="https://github.com/user-attachments/assets/618b237f-ebe5-44ff-a17c-aa9a0d96cd79" />

Ejecución del sistema:
---

Para la correcta ejecución del sistema (webFlux API), se creó configuración de Docker la cual levanta dos contenedores:
Contenedor 1: BD MySQL
Contenedor 2: API (Propiamente).

Ambos interconectados 

Pasos para la ejecución correcta:

En primer lugar, se debe descargar el proyecto desde el repositorio de GitHub (Clonar Proyecto) y ejecugtar los siguientes comandos desde una terminal (CMD, GitBash, etc)

Paso 1: docker compose up -d --build (Este comando permite construir los contenedores Docker de acuerdo con la configuración indicada en docker-compose.yml

Paso 2: Esperar el build de los contenedores (hasta que esté completado). como aparece a continuación:
        <img width="1342" height="651" alt="image" src="https://github.com/user-attachments/assets/927bd363-52b8-439f-82b4-f44a1ab880d5" />
		
Paso 3: docker ps (Este permite verificar la ejecución los procesos de docker). como aparece a continuación:
		<img width="1422" height="177" alt="image" src="https://github.com/user-attachments/assets/797414d8-8378-461d-928a-50f24dbacd69" />

Ya con esos pasos, los dos contendedores estan arriba y se pueden consumir los servicios del API desde Postman, Curl o cualquier cliente.  como aparece a continuación:		

Consumo servicios desde Postman:
--------
[Seti-API.postman_collection.json](https://github.com/user-attachments/files/22728870/Seti-API.postman_collection.json)

<img width="1280" height="701" alt="image" src="https://github.com/user-attachments/assets/9e4fead5-b125-4562-a374-2328bbfacf89" />

Todas las funcionalidades de la API están contempladas en los siguientes servicios:

Servicios de la API
------------------------

1	Adicionar una Franquicia:
----------------------------------

	Protocol: POST
	EndPoint: /localhost:8080/api/franchises"
   
	JsonRequest:
	{
		"name": "Nombre Franqucia"
	}
   
	JsonResponse:
	{
		"id": 17,
		"name": "Nombre Franquicia",
		"branches": []
	}
   
3 	Listar todas las franchises
-------------------------------------
	Protocol: GET
	EndPoint: /localhost:8080/api/franchises"
   
	JsonRequest:
	nada
   
	JsonResponse:
	{
		"id": 17,
		"name": "Nombre Franquicia",
		"branches": []
	}


4 	Adicioanr una Sucursal a una franquicia
----------------------------------------------
	Protocol: POST
	EndPoint: /localhost:8080/api/branches/{franchiseId}"
	
	Ejemplo: Ejemplo: http://localhost:8080/api/branches/17
   
	JsonRequest:
	{
		"name" : "Brach 1"
	}
   
	JsonResponse:
	{
		"id": null,
		"franchiseId": 17,
		"name": "Brach 1",
		"products": []
	}

5 	Actualiar nombre de Sucursal 
-------------------------------------
	Protocol: PUT
	EndPoint: http://localhost:8080/api/branches/{id}?name={nombre nuevo}"
	
	Ejemplo: http://localhost:8080/api/branches/4?name=prueba1
   
	JsonRequest:
	nada
   
	JsonResponse:
	{
		"id": 4,
		"name": "prueba1",
		"franchiseId": 17
	}

6 	Adicioanr un producto a una sucursal
------------------------------------------
	Protocol: POST
	EndPoint: http://localhost:8080/api/products/{branchId}"
	
	Ejemplo: http://localhost:8080/api/products/4
   
	JsonRequest:
	{
	  "name": "Producto 4",
	  "stock": 2500
	}
   
	JsonResponse:
	{
		"id": 8,
		"branchId": 4,
		"name": "Producto 4",
		"stock": 2500
	}
	
7 	Borrar un producto
--------------------------------
	Protocol: DEL
	EndPoint: http://localhost:8080/api/products/{id}"
	
	Ejemplo: http://localhost:8080/api/products/8
   
	JsonRequest:
	nada
   
	JsonResponse:
	nada, estatus= 200
	
	
8 	Modificar Stock de un producto
-------------------------------------
	Protocol: PUT
	EndPoint: http://localhost:8080/api/products/{id}/{Stock}"
	
	Ejemplo: http://localhost:8080/api/products/7/3000
   
	JsonRequest:
    nada
   
	JsonResponse:
	{
		"id": 7,
		"name": "Producto 7",
		"stock": 2100,
		"branchId": 4
	}
		
9 	Encontrar maximo stock de producto en una sucursl:
-----------------------------------------------------------
	Protocol: GET
	EndPoint: http://localhost:8080/api/products/max/{branchid}"
	
	Ejemplo: http://localhost:8080/api/products/max/4
   
	JsonRequest:
    nada
   
	JsonResponse:
	{
		"id": 7,
		"branchId": 4,
		"name": "Producto 7",
		"stock": 2100
	}
		
		
10 	Actualizar nombre de un producto
----------------------------------------
	Protocol: PUT
	EndPoint: http://localhost:8080/api/products/{id}?name={nuevo nombre}"
	
	Ejemplo: http://localhost:8080/api/products/7?name="nuevo nombre"
   
	JsonRequest:
	{
	  "name": "Producto X",
	  "stock": 2500
	}
   
	JsonResponse:
	{
		"id": 7,
		"branchId": 4,
		"name": "Producto 7",
		"stock": 2100
	}


Collection de Postman para su ejecución
------
Coleccion de Postman lista para ser copiada y pegada en Postman para consumir servicios de API.





Algunas ejecuciones de Sevicios desde Postman:

<img width="1253" height="722" alt="image" src="https://github.com/user-attachments/assets/890ecfb1-ff12-439a-941e-df6e94efbd04" />

<img width="1265" height="778" alt="image" src="https://github.com/user-attachments/assets/5d700dc2-2774-430b-8112-ebec8a2eb593" />









