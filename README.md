# Kava

Aplicación web desarrollada con **Spring Boot** y **Maven**.

---

## Requisitos

* **Java 21+**
* **Maven**

---

## Configuración Inicial 
###   crea el archivo .env  en la raiz

```bash
DB_HOST=servidor
DB_PORT=puerto
DB_NAME=nombre_base_de_datos
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña_secreta
```


---

## Ejecutar

Para iniciar la aplicación en modo desarrollo:

```bash
mvn spring-boot:run
```

---

## Compilar

Para limpiar y generar la aplicación:

```bash
mvn clean package
```

El archivo `.jar` se genera en:

```text
target/
```

---

## Ejecutar la aplicación compilada

Después de compilar:

```bash
java -jar target/kava-*.jar
```

---

## Estructura

```text
kava/
├── src/
├── pom.xml
├── .gitignore
└── README.md
```
