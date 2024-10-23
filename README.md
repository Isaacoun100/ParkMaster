# Park Master - Manual de Usuario

Bienvenido a **Park Master**, una solución de gestión de estacionamientos que permite a administradores, clientes e inspectores interactuar de manera eficiente y sencilla. Aquí te explicamos cómo utilizar las funcionalidades principales para cada tipo de usuario.

## Usuarios en Park Master

El sistema cuenta con tres tipos de usuarios:
- **Admin** (Administrador)
- **Customer** (Cliente)
- **Inspector**

### Admin (Administrador)

El administrador tiene control total sobre la gestión del sistema. Aquí las principales funciones que puede realizar:

1. **Gestión de Administradores (CRUD):**
   - Puede crear, modificar y eliminar administradores con información como nombre, apellidos, teléfono, email, dirección de facturación, fecha de contratación, ID y PIN.
   - El PIN se envía al correo electrónico como un código de un solo uso (OTP) y no es visible en la aplicación por seguridad.

2. **Configuración del Estacionamiento:**
   - Puede definir las **horas de regulación** durante las cuales se cobra el estacionamiento.
   - Establecer el **precio por hora** y el **tiempo mínimo** que se puede comprar (por ejemplo, intervalos de 30 minutos).
   - Definir el **costo de las multas** cuando un vehículo se estaciona sin pagar.
   - **Agregar o eliminar espacios de estacionamiento** en grupos (por ejemplo, del 00100 al 00120).
   - Cualquier cambio en la configuración envía un correo de confirmación al administrador.

3. **Generación de Reportes:**
   - **Ingresos**: Informe de ingresos diarios y totales en un período seleccionado.
   - **Ingresos por Multas**: Detalle de ingresos por multas, mostrando cada día y el total.
   - **Estado de los Parqueos**: Muestra la lista de espacios ocupados y vacíos, con detalles como la placa, costo y tiempo de uso en caso de espacios ocupados.
   - **Historial de Ingresos y Multas**: Detalles por día en orden descendente.
   - **Estadísticas de Uso**: Reporte detallado y resumen del uso de espacios en un período específico.

### Customer (Cliente)

El cliente puede gestionar su información personal y la de sus vehículos, además de utilizar los servicios de estacionamiento.

1. **Gestión de la Información (CRUD):**
   - Puede registrar y actualizar su nombre, apellidos, teléfono, email, dirección de facturación y datos de tarjeta (número, fecha de vencimiento y CVV).
   - Puede añadir vehículos con información como la placa, marca y modelo.

2. **Parquear:**
   - El cliente puede seleccionar un espacio disponible y el vehículo que usará. El sistema confirma si el espacio está libre y permite elegir el tiempo de uso.

3. **Agregar Tiempo:**
   - Los clientes pueden extender el tiempo de estacionamiento en cualquier momento. Recibirán un comprobante por correo electrónico.

4. **Desparquear:**
   - Cuando el tiempo de estacionamiento termina, el espacio se libera automáticamente. El cliente también puede desocupar el espacio antes de que el tiempo termine, y el sistema registra esta acción.

5. **Reporte:**
   - El cliente puede consultar su historial de estacionamientos y multas.

### Inspector

Los inspectores se encargan de supervisar el estado de los estacionamientos y aplicar multas en caso necesario.

1. **Gestión de Inspectores (CRUD):**
   - Similar a los administradores, pero con un campo adicional: un **código de terminal** de 6 caracteres.

2. **Revisar Parqueo:**
   - Los inspectores verifican si los vehículos en los espacios han pagado el estacionamiento. Si no, se genera y envía una multa al dueño del vehículo.

3. **Reporte de Estado de Parqueos:**
   - Pueden visualizar la lista actual de todos los espacios, tanto ocupados como vacíos, para monitorear el uso en tiempo real.

---

¡Esperamos que Park Master facilite la gestión de tus estacionamientos y proporcione una experiencia fluida para todos los usuarios!
