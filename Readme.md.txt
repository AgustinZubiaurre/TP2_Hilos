Diferencia entre runnable y thread:

Hay dos formas de crear hilos en java, extendiendo de la clase Thread o implementando la interfaz Runnable.
La forma correcta seria la segunda ya que java no soporta herencia multiple. 

Al extender de la clase Thread se crea un objeto unico y se asocia a la clase Thread con el mismo. En cambio al implementar la interfaz, ambos comparten
el mismo objeto.


Ciclo de vida de un thread:

En java los hilos tienen 5 estados

New: Se crea una instancia de la clase Thread previo a la invocacion del metodo start().
Runnable: Ya se ejecuto el metodo start() pero el hilo no esta corriendo en el cpu todavia.
Running: El hilo ya tiene un tiempo asignado en el cpu y esat ejecutando su lista de acciones.
Non-Runnable (Blocked): El hilo sigue vivo (no lo elimino el garbage collector) pero por algun motivo no es elegible
para serle asignado tiempo en el cpu.
Terminated: El metodo run() del hilo ya llego a su fin.


Explique los metodos start sleep yield join:

start(): Comienza la ejecucion del hilo llamando internamente al método run() y el estado pasa de New a Runnable.
sleep(): Pausa le ejecucion del hilo por una cantidad especifica de milisegundos.
yield(): Si un hilo no esta haciendo nada particularmente importante y otro hilo o proceso necesita el tiempo de cpu, entonces van a reemplazarlo. De lo contrario el hilo seguira su ejecucion. 
join(): Pone al hilo en wait hasta que que otro hilo complete su ejecucion.
