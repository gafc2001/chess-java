# ♟️ Chess Java

Juego de ajedrez desarrollado en Java con interfaz gráfica Swing y persistencia en MySQL.

## Requisitos previos

Asegúrate de tener instalado lo siguiente antes de continuar:

| Herramienta | Versión mínima | Verificar con |
|---|---|---|
| Java JDK | 17+ | `java -version` |
| Docker + Docker Compose | Cualquier versión reciente | `docker -v` |

---

## 1. Clonar el repositorio

```bash
git clone https://github.com/gafc2001/chess-java.git    
cd chess-java
```

---

## 2. Levantar la base de datos con Docker

El proyecto incluye un `docker-compose.yml` que levanta una instancia de **MySQL 8.0** automáticamente.

```bash
docker-compose up -d
```

Esto crea:
- Un contenedor llamado `chess_db`
- La base de datos `chess_game` en el puerto **3308** del host
- Las credenciales configuradas por defecto:

| Campo    | Valor  |
|----------|--------|
| Host     | `localhost` |
| Puerto   | `3308` |
| Base de datos | `chess_game` |
| Usuario root | `root` |
| Contraseña root | *(ver `DB.java`)* |

> **Nota:** El puerto expuesto es el **3308** (no el 3306 estándar) para evitar conflictos con instalaciones locales de MySQL.

---

## 3. Inicializar la base de datos

Ejecuta el script de migración para crear las tablas e insertar el usuario administrador inicial:

```bash
docker exec -i chess_db mysql -uroot -p<PASSWORD> chess_game < resources/migraciones/bd.sql
```

O bien conéctate directamente al contenedor:

```bash
docker exec -it chess_db mysql -uroot -p
```

Y ejecuta manualmente el contenido de `resources/migraciones/bd.sql`.

### Usuario administrador por defecto

| Campo | Valor |
|---|---|
| Nombre de usuario | `gfarfan` |
| Contraseña | `123456` |

---

## 4. Compilar el proyecto

Desde la raíz del proyecto, ejecuta el siguiente comando para compilar **todos** los archivos fuente de forma recursiva:

**Windows (PowerShell):**
```powershell
javac -cp "lib\mysql-connector-j-8.3.0.jar" -d out $(Get-ChildItem -Path src -Recurse -Filter *.java | % FullName)
```

**Linux / macOS (Bash):**
```bash
find src -name "*.java" > sources.txt
javac -cp "lib/mysql-connector-j-8.3.0.jar" -d out @sources.txt
```

Los archivos `.class` compilados se guardan en la carpeta `out/`.

---

## 5. Ejecutar la aplicación

**Windows (PowerShell):**
```powershell
java -cp "out;lib\mysql-connector-j-8.3.0.jar" Main
```

**Linux / macOS (Bash):**
```bash
java -cp "out:lib/mysql-connector-j-8.3.0.jar" Main
```

Se abrirá la ventana de inicio de sesión del juego.

---

## Estructura del proyecto

```
chess-java/
├── src/
│   ├── Main.java                          # Punto de entrada
│   ├── aplicacion/
│   │   ├── Sesion.java                    # Sesión del usuario (Singleton)
│   │   └── casosuso/
│   │       ├── GestionarJuego.java        # Lógica del juego
│   │       ├── IniciarSesion.java         # Caso de uso: login
│   │       └── RegistrarUsuario.java      # Caso de uso: registro
│   ├── dominio/
│   │   ├── enums/                         # Color, TipoPieza, EstadoJuego, etc.
│   │   ├── modelos/                       # Tablero, Partida, Usuario, Piezas
│   │   └── puertos/                       # Interfaces / contratos
│   ├── infraestructura/
│   │   ├── mapper/                        # UsuarioMapper (ResultSet → Modelo)
│   │   └── persistencia/                  # DB (Singleton), RepositorioUsuarioMySQL
│   └── presentacion/
│       ├── controladores/                 # ControladorLogin
│       └── vistas/                        # Vistas Swing (Login, Juego, Ranking, etc.)
├── lib/
│   └── mysql-connector-j-8.3.0.jar       # Driver JDBC de MySQL
├── resources/
│   ├── assets/                            # Imágenes y recursos gráficos
│   └── migraciones/
│       └── bd.sql                         # Script de creación de tablas
├── docker-compose.yml                     # Configuración del contenedor MySQL
└── out/                                   # Clases compiladas (generado al compilar)
```
