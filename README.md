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
│   │   ├── modelos/                       # Tablero, Partida, Usuario, Piezas, HistorialMovimientos, ArbolRanking, ColaMovimientos
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

---

## Historial de movimientos (Lista doblemente enlazada)

El historial de la partida se guarda en una **lista doblemente enlazada** implementada a mano, sin usar `ArrayList` ni `LinkedList` de Java.

### Clases

| Clase | Ubicación | Responsabilidad |
|---|---|---|
| `NodoMovimiento` | `src/dominio/modelos/` | Representa **un solo movimiento** (de blancas o de negras) |
| `HistorialMovimientos` | `src/dominio/modelos/` | La lista: mantiene la `cabeza`, la `cola` y el `tamanio` |

### El nodo

Cada `NodoMovimiento` guarda los datos del movimiento y dos referencias:

| Atributo | Descripción |
|---|---|
| `origen` / `destino` | Casillas de inicio y fin (ej. `e2` → `e4`) |
| `piezaMovida` | La pieza que se movió |
| `piezaCapturada` | La pieza que se comió (o `null` si no hubo captura) |
| `color` | Color del jugador que hizo el movimiento |
| `anterior` | Referencia al nodo del movimiento previo |
| `siguiente` | Referencia al nodo del movimiento posterior |

```
            cabeza                                        cola
              │                                             │
              ▼                                             ▼
null ◄── ┌─────────┐ ◄── ┌─────────┐ ◄── ┌─────────┐
         │ e2-e4   │     │ d7-d5   │     │ e4-d5   │
         │ Blancas │     │ Negras  │     │ Blancas │
         │ Peón    │     │ Peón    │     │ Peón    │
         └─────────┘ ──► └─────────┘ ──► └─────────┘ ──► null
```

- La **cabeza** es el primer movimiento de la partida; su `anterior` es `null`.
- La **cola** es el último movimiento; su `siguiente` es `null`.

### Operaciones

**`agregar(nodo)`**: se llama cada vez que un jugador hace un movimiento válido (`Partida.realizarMovimiento`).

1. Si la lista está vacía, el nodo pasa a ser la `cabeza`.
2. Si no, se enlaza al final: `cola.siguiente = nodo` y `nodo.anterior = cola`.
3. El nodo pasa a ser la nueva `cola` y aumenta el `tamanio`.

**`eliminarUltimo()`**: se usa al **regresar un movimiento**.

1. Se toma la `cola` actual.
2. La nueva `cola` es `cola.anterior`. Gracias al enlace hacia atrás, esto se hace en un paso, sin recorrer la lista.
3. Si la nueva cola es `null`, la lista queda vacía (`cabeza = null`). Si no, se corta su enlace: `cola.siguiente = null`.
4. Disminuye el `tamanio` y se devuelve el nodo eliminado.

**Recorrido**: para mostrar el historial en pantalla, se empieza en la `cabeza` y se avanza por `siguiente` hasta llegar a `null`. Cada nodo es una fila de la tabla:

| Nº | Jugador | Pieza | Movimiento |
|---|---|---|---|
| 1 | Blancas | Peón | e2-e4 |
| 2 | Negras | Peón | d7-d5 |
| 3 | Blancas | Peón | e4-d5 |

### Regresar movimiento

Cada jugador puede **regresar su movimiento hasta 2 veces por partida** (`Partida.MAX_RETROCESOS`).

- Después de mover, aparece el botón **"Regresar movimiento (n)"** en el panel de ese jugador, donde `n` son los retrocesos que le quedan.
- Solo se puede regresar el último movimiento propio, es decir, antes de que el rival responda (la `cola` debe ser de su color).
- Al presionarlo:
  1. `HistorialMovimientos.eliminarUltimo()` quita la `cola`.
  2. `Tablero.deshacerMovimiento(nodo)` regresa la pieza a su `origen` y, si hubo captura, repone la `piezaCapturada` en el `destino`.
  3. El turno vuelve al jugador y se descuenta uno de sus retrocesos.
- En partidas **online**, la lista de movimientos se actualiza en la base de datos y el rival detecta que es más corta que la suya, así que deshace el movimiento en su tablero.

---

## Ranking de jugadores (Árbol binario de búsqueda)

El ranking se ordena con un **árbol binario de búsqueda (BST)** implementado a mano, sin usar `Collections.sort`, `stream().sorted()` ni `ORDER BY`.

### Clases

| Clase | Ubicación | Responsabilidad |
|---|---|---|
| `NodoRanking` | `src/dominio/modelos/` | Guarda un `Usuario` y las referencias `izquierdo` y `derecho` |
| `ArbolRanking` | `src/dominio/modelos/` | El árbol: mantiene la `raiz` y el `tamanio` |

La clave de ordenamiento son las **partidas ganadas** del usuario:

- Menos victorias → subárbol **izquierdo**.
- Más victorias → subárbol **derecho**.
- **Empates** → subárbol **izquierdo**, para que en el recorrido se respete el orden en que llegaron desde la base de datos.

```
Insertando: ana(5), beto(9), caro(2), dani(5), eva(7), fede(0)

                 ana(5)
               /        \
          caro(2)      beto(9)
          /     \       /
     fede(0)  dani(5) eva(7)
```

### Operaciones

**`insertar(usuario)`** (recursivo): se compara con el nodo actual y se baja por la izquierda o por la derecha hasta encontrar un lugar vacío (`null`), donde se crea el nuevo nodo.

**`aRanking()`**: **recorrido inorden inverso** (derecho → raíz → izquierdo). Devuelve los usuarios de **mayor a menor** cantidad de victorias:

```
beto(9) → eva(7) → ana(5) → dani(5) → caro(2) → fede(0)
```

`VistaRanking` inserta todos los usuarios en el árbol y dibuja las filas en el orden de `aRanking()`, con medallas para los 3 primeros.

### Complejidad

| Caso | Inserción | Motivo |
|---|---|---|
| Promedio | O(log n) | El árbol queda razonablemente balanceado |
| Peor caso | O(n) | Si los usuarios llegan ya ordenados por victorias, el árbol degenera en una lista |

El recorrido completo siempre es O(n).

---

## Movimientos pendientes online (Cola)

En partidas **online**, cada cliente consulta la base de datos cada segundo. Si hay **latencia** (red lenta, la ventana se congeló un momento o el jugador se reconecta), pueden llegar **varios movimientos del rival juntos**. En vez de aplicarlos todos de golpe, se guardan en una **cola (FIFO)** implementada a mano y se aplican de a uno.

### Clases

| Clase | Ubicación | Responsabilidad |
|---|---|---|
| `NodoMovimientoPendiente` | `src/dominio/modelos/` | Un movimiento recibido: `origen`, `destino` y la referencia `siguiente` |
| `ColaMovimientos` | `src/dominio/modelos/` | La cola: mantiene el `frente`, el `fin` y el `tamanio` |

```
  frente                          fin
    │                              │
    ▼                              ▼
┌─────────┐    ┌─────────┐    ┌─────────┐
│ e7-e5   │ ─► │ g1-f3   │ ─► │ b8-c6   │ ─► null
└─────────┘    └─────────┘    └─────────┘
 sale primero                  entró último
```

¿Por qué una cola y no una pila? Porque los movimientos se deben aplicar **en el mismo orden en que se jugaron**: el primero que llega es el primero que sale. Con una pila (LIFO) se aplicaría primero la última jugada y el tablero quedaría inconsistente.

### Operaciones

| Operación | Descripción |
|---|---|
| `encolar(nodo)` | Agrega por el `fin`. Si la cola está vacía, el nodo es también el `frente` |
| `desencolar()` | Quita y devuelve el `frente` (o `null` si está vacía) |
| `vaciar()` | Deja la cola vacía |
| `getFrente()` | Permite recorrer la cola sin modificarla (para mostrarla en la tabla) |

### Flujo en `VistaJuego`

1. **Sincronización (cada 1 s)**: lee la lista de movimientos de la base de datos. Si tiene más movimientos que el historial local, **reconstruye la cola** con los que faltan aplicar. La base de datos es la fuente de verdad: si el rival regresó un movimiento que todavía no se había aplicado, ese movimiento desaparece de la cola.
2. **Aplicación (cada 500 ms)**: se desencola **un** movimiento y se aplica al tablero. Así cada jugada se ve una por una.
3. **Tabla de movimientos**: después de los movimientos ya jugados (lista doblemente enlazada), se muestran los que esperan en la cola con la pieza **"Pendiente"**. Cuando se aplican, pasan a ser filas normales:

| Nº | Jugador | Pieza | Movimiento |
|---|---|---|---|
| 1 | Blancas | Peón | e2-e4 |
| 2 | Negras | Pendiente | e7-e5 |
| 3 | Blancas | Pendiente | g1-f3 |

4. Si el rival **regresa un movimiento ya aplicado**, la cola se vacía y se deshace ese movimiento en el tablero, como antes.
