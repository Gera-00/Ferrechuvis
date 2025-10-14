
# MODULO CONTROL DE INVENTARIO

    Clasificación: tipo_movimiento ("Ajuste Negativo, Perdida, Merma")


# CODIGOS

    Se agregara campo codigo a Movimiento, esto con la finalidad que el ID de lo operación sea alfanumerico,
    entonces por ejemplo para ventas tendra un formato como: "V00001" una salida "S00001", una merma"M00001"

    Entonces para esto la generación del ID o codigo lo haremos desde el software, esto debido a que la DB
    esta generada en SQLITE y la función TRIGGER está mas limitada, lo cual conlleva una mayor dificultad para hacerlo desde
    la DB


    Definir Implementación Agregar y Eliminar
varios proveedores para un producto





# COMENTARIOS DE NOTEBOOKLM

El Diagrama ER que usted ha compartido, centrado en las tablas `Movimientos` y `Detalle_Movimiento`, resulta **altamente funcional** para registrar todos los escenarios que ha planteado (ventas, ventas sobre pedido y salidas por ajustes/pérdidas), ya que está diseñado específicamente para cumplir con el requisito funcional de **Controlar el stock de productos (entradas/salidas)** (RF3).

A continuación, se detalla la funcionalidad de la estructura de las tablas para cada uno de sus requerimientos, basándose en la estructura del diagrama:

### Funcionalidad de la tabla `Movimientos` y `Detalle_Movimiento`

El registro de entradas y salidas de materiales se maneja a través de una relación uno a muchos entre `Movimientos` y `Detalle_Movimiento`.

| Tabla | Campo Clave | Propósito para Entradas/Salidas |
| :--- | :--- | :--- |
| **`Movimientos`** | `tipo_movimiento` | Permite clasificar la naturaleza del evento general (Ej. Venta, Compra, Ajuste). |
| | `motivo` | Sirve para registrar la razón principal de la transacción. |
| | `id_cliente` | Asocia el movimiento a un cliente específico, fundamental para ventas. |
| **`Detalle_Movimiento`** | `cantidad` | Registra el volumen que entra o sale del inventario. |
| | `observaciones` | Proporciona un campo para detalles adicionales que no caben en el campo `motivo` principal. |
| | `stock_anterior`, `stock_actual` | Documenta el impacto exacto del movimiento en el inventario del producto. |

### 1. Registro de Ventas (Salidas estándar)

La tabla `Movimientos` es perfectamente adecuada para registrar ventas:

*   **Identificación del movimiento:** Se registraría un nuevo registro en `Movimientos` donde el campo **`tipo_movimiento`** se definiría como "Venta" (o similar).
*   **Asociación al Cliente:** La clave foránea **`id_cliente`** permite enlazar la venta directamente con la tabla `Clientes`, cumpliendo el rol de una factura o recibo.
*   **Detalle de los Productos:** En la tabla `Detalle_Movimiento`, se especifican los productos que forman parte de esa venta (`id_producto`), la **`cantidad`** que salió y el **`precio_unitario`** de venta.

### 2. Ventas de materiales sobre pedido (Bajo Pedido)

El sistema tiene requisitos específicos para manejar productos **"bajo pedido"** (RF7, RF12). La tabla `Movimientos` gestiona la **salida física** de estos materiales una vez que el pedido se entrega:

*   **Registro de la Salida:** Al igual que una venta estándar, se registra un movimiento de tipo "Venta" o, alternativamente, "Salida por Pedido Especial" en `tipo_movimiento`.
*   **Trazabilidad del Pedido:** Aunque el diagrama no muestra una tabla de "Pedidos" separada, la funcionalidad de registrar materiales bajo pedido (asociando cliente, nombre y teléfono) es un requisito (RF12). Al momento de la salida (la entrega), se puede usar el campo **`motivo`** o **`observaciones`** en `Movimientos` o `Detalle_Movimiento` para hacer referencia al registro inicial del pedido, vinculando así la salida física con el compromiso de la orden.

### 3. Salidas por pérdida, robo u otros ajustes

La tabla `Movimientos` es muy funcional para manejar salidas que no son ventas (ajustes negativos):

*   **Clasificación:** Usted puede utilizar el campo **`tipo_movimiento`** para diferenciar estas salidas de las ventas (Ej. "Ajuste Negativo", "Pérdida", "Merma").
*   **Justificación Detallada:** El campo **`motivo`** en la tabla `Movimientos` y/o en `Detalle_Movimiento` (ya sea globalmente para el movimiento o por artículo afectado) es clave para registrar la causa específica de la salida, como "Robo", "Material dañado" o "Pérdida durante transporte".
*   **Observaciones:** El campo **`observaciones`** en `Detalle_Movimiento` permite añadir contexto adicional (quién autorizó la baja, fecha de descubrimiento, etc.).

En resumen, la combinación de los campos clasificatorios (`tipo_movimiento`, `motivo`) y los campos de impacto de stock (`cantidad`, `stock_anterior`, `stock_actual`) en las tablas `Movimientos` y `Detalle_Movimiento` asegura que el módulo de inventario pueda registrar, clasificar y justificar cualquier entrada o salida requerida por el sistema (RF3).


# MANEJAR CAMPOS CONDICIONALES A UN TIPO DE MOVIMIENTO

    Para solucionar esto, se puede haer simplemente el campo null, por ejemplo, para una entrada, no se tiene relación con un cliente, es por esto que se puede dejar vacio este campo.



    Aqui haciendo una prueba de grabacion de video como si estuvieramos codificando