# Api para consultar las tarifas aplicadas.

El api devuelve la tarifa aplicada bajo ciertos filtros: **"fecha de búsqueda"** , **"identificador del producto"**
, **"identificador de la cadena"**

## Primeros pasos

Clonar el repositorio en una carpeta local:

```
git clone https://github.com/vmplasencia91plasencia/Test-Product-Tarifa.git
```

### Prerrequisitos

Herramientas para compilar y ejecutar el proyecto:

**Git**

Para clonar el proyecto necesitarás tener instalado git. Puedes comprobarlo con este comando:

```
$ git --version
```

Para instalar git en Linux solo necesitas ejecutar:

```
$ sudo apt-get update
$ sudo apt install git-all
```

**Maven**

Para construir el proyecto, verifique si maven se ha instalado en su sistema

```
$ mvn --version
```

Instale maven escribiendo el siguiente comando:

```
$ sudo apt-get update
$ sudo apt install maven
```

**Java 11**

Es básico e imprescindible tener Java instalado en nuestro ordenador para ejecutar el api y los test.

Compruebe si Java está instalado:

```
$ java -version
```

Para instalarlo en una máquina Linux, ingresamos el siguiente script en la consola:

```
$ sudo apt-get update
$ sudo apt install openjdk-8-jdk openjdk-8-jre
```

### Ejecución en local

Para iniciar el servicio en la máquina local, simplemente ejecute el siguiente comando en una consola desde la carpeta
raíz del proyecto:

```
$ mvn spring-boot:run
```

Para comprobar si el servicio está funcionando, puede abrir un navegador web y acceder a la página de swagger
en http://localhost:8080

## Running the tests

Puede ejecutar una prueba unitaria ejecutando el siguiente comando

```
$ mvn clean install 
```


 

