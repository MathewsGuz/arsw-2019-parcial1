# Parcial 1 ARSW-T1
Parcial de primer tercio
# Introducción
Con el fin de de contribuir a programas estadísticos cada vez más robustos se requiere crear una plataforma distribuida que permita la búsqueda de identificadores únicos. Se le ha encargado la tarea de modificar el programa existente debido a que la compañia ha reportado lentitud en la búsqueda de dichos identificadores. Personal técnico de la compañia ha identificado que el programa no está aprovechando las capacidades multinucleo de las máquinas en dónde está desplegada la solución.
# Nota
Punto 1 y 2 Proyecto GuidFinderDesktop Punto 3 Proyecto GuidFinderAPI
# 1
Haga que el programa divida y paralelice la búsqueda  en exactamente 4 hilos de ejecución.
# 2
Haga que el programa mantenga inactivos los hilos (sin esperas activas!), hasta tanto se detecte que el computador no tiene actividad (en este caso, cuando se completen 10 segundos sin detectar actividad en el teclado). Del mismo modo, una vez se detecte actividad del teclado, los hilos deberían nuevamente suspenderse (de nuevo, hasta tanto se detece la inactividad del teclado por al menos 10 segundos)
# 3
En el proyecto GuidFinderAPI construya un API rest que registre y consulte historicos de las busquedas realizadas. Cada vez que finaliza una búsqueda el sistema debería registrar (por medio del api rest )  los siguientes datos
Fecha de busqueda, UUID buscado,cantidad encontrada

De igual manera el API rest debería permitir la consulta de todas las busquedas de UUID´s utilizando la siguiente representación JSON 
`
{
"Fecha":"2019-02-21T05:10:00",
"Guid":"d0692660-c39a-4d73-9496-d9df0c4ebdf3",
"Count":0
}
`

Almacene dicha información en memoria volatil
Utilice el nombre del recurso "uuid" es decir cuando se trate de acceder al recurso para consultarlo la url debería ser
` http://localhost:8080/primes` utilizando el verbo GET
y para registrarlo debería ser la misma pero usando el verbo POST

# Preguntas Teoría
# Subida
