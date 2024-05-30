package org.example

import Estudiante
import EstudianteMateria
import EstudianteMateriaService
import EstudianteService
import Materia
import MateriaService
import java.util.*


fun main() {
    val reader = Scanner(System.`in`)
    val estudianteService = EstudianteService()
    val materiaService = MateriaService()
    val relacionService = EstudianteMateriaService()

    //Setup
    val estudiantesLoaded = estudianteService.cargarDesdeCSV()
    val materiasLoaded = materiaService.cargarDesdeCSV()
    val relacionesLoaded = relacionService.cargarDesdeCSV()

    val materias = materiasLoaded.toMutableList()
    val estudiantes = estudiantesLoaded.toMutableList()
    val relaciones = relacionesLoaded.toMutableList()

    relaciones.forEach { relacion ->
        val materiaFound = materias.find { materia ->
            materia.codigo == relacion.codigoMateria
        }
        if (materiaFound != null){
            val estudianteFound = estudiantes.filter { estudiante ->
                estudiante.codigoUnico == relacion.codigoUnico
            }
            materiaFound.estudiantes = estudianteFound.toMutableList()
        }
    }

    var menuOption:Int = 0

    while (menuOption != 8){
        printMenu()
        menuOption = reader.nextInt()
        when(menuOption){
            1 -> {
            println("Ingresa los datos de la materia:")
            print("Nombre: ")
            val nombre = readln()
            print("Código: ")
            val codigo = readln()
            print("CodigoProfesor (número): ")
            val codigoProfesor = reader.nextInt()
            val materiaACrear = Materia(nombre,codigo,true,codigoProfesor)
            materias.add(materiaACrear)
            println("Materia Creada!")
        }
            2 -> {
                printMateriasSinEstudiantes(materias)
                print("Digita el codigo de la materia que quieres modificar: ")
                val codigoInput = readln()
                val materiaToUpdate = materias.find{ it.codigo == codigoInput }
                materiaToUpdate?.let { materia ->
                    val indexMateria = materias.indexOf(materia)
                    print("Nombre actual: ${materiaToUpdate.nombre} \nNuevo nombre:")
                    val nombre = readln()
                    print("CodigoProfesor actual: ${materiaToUpdate.codigoProfesor} \nNuevo codigoProfesor(número):")
                    val codigoProfesor = reader.nextInt()
                    print("Estado: ${materiaToUpdate.activo} \nNuevo estado (y/N):")
                    val estadoInput = readln()[0] ?: 'N'
                    val estado = estadoInput == 'Y'|| estadoInput == 'y'
                    materias[indexMateria] = Materia(nombre,codigoInput,estado,codigoProfesor)
                    println("Materia Actualizada!")
                } ?: println("No se encontró la materia")
            }
            3 -> {
                printMaterias(materias)
                print("Digita el codigo de la materia que quieres eliminar: ")
                val codigoInput = reader.next()
                val removed = materias.removeIf { it.codigo == codigoInput }
                if(removed) println("Material Eliminada") else println("Material No se pudo eliminar ")
            }
            4 -> {
                println("Ingresa los datos del Estudiante:")
                print("Nombre: ")
                val nombre = readln()
                print("Fecha Nacimiento (dd-MM-YYYY): ")
                val fechaNacimiento = readln()
                print("Codigo único (número): ")
                val codigoUnico = reader.nextInt()
                print("Carrera: ")
                val carrera = readln()
                print("IRA: Indice de Rendimiento Académico (Decimal): ")
                val IRA = reader.nextDouble()
                val estudianteACrear = Estudiante(nombre, Utils.parseDate(fechaNacimiento),carrera,codigoUnico,IRA)

                println("¿En que materia se inscribirá el alumno?")
                printMateriasSinEstudiantes(materias)
                print("Ingresa el codigo de la materia: ")
                val codigoMateriaIngresado = readln();
                val materia = materias.find { it.codigo == codigoMateriaIngresado }
                materia?.let{
                    materia.estudiantes.add(estudianteACrear)
                    estudiantes.add(estudianteACrear)
                    relaciones.add(EstudianteMateria(codigoUnico,codigoMateriaIngresado))
                    println("Estudiante Creado!")
                }?:println("No se encontró la materia, no se guardó el estudiante")
            }
            5 -> {
                println("En que materia está el estudiante?")
                printMateriasSinEstudiantes(materias)
                print("Digita el codigo de la materia en la que se encuentre el estudiante: ")

                val codigoMateriaUpdate = readln()
                val materiaDelEstudiante = materias.find { it.codigo == codigoMateriaUpdate }

                materiaDelEstudiante?.let { materia ->
                    materia.estudiantes.forEach { println(it) }
                    val indexmateriaDelEstudiante = materias.indexOf(materia)

                    print("Digita el código único del estudiante que quieres actualizar: ")
                    val codigoUnicoUpdate = reader.nextInt()

                    val estudianteUpdate = materia.estudiantes.find { it.codigoUnico == codigoUnicoUpdate }

                    estudianteUpdate?.let { estudiante ->
                        val indexOfEstudiante = materia.estudiantes.indexOf(estudiante)

                        print("Nombre actual: ${estudianteUpdate.nombre} \nNuevo nombre:")
                        val nombre = readln()
                        print("Fecha Nacimiento actual: ${Utils.formatDate(estudianteUpdate.fechaNacimiento)}\n" +
                                "Nueva fecha (dd-MM-YYYY):")
                        val fechaNacimiento = readln()
                        print("Codigo único actual: ${estudianteUpdate.codigoUnico} \nNuevo código único(número):")
                        val codigoUnico = reader.nextInt()
                        print("Carrera actual: ${estudianteUpdate.carrera}\nNueva carrera:")
                        val carrera = readln()
                        print("IRA actual: \nNuevo IRA (Decimal):")
                        val IRA = reader.nextDouble()

                        val estudianteActualizado = Estudiante(nombre,Utils.parseDate(fechaNacimiento),carrera,codigoUnico,IRA)
                        materias[indexmateriaDelEstudiante].estudiantes[indexOfEstudiante] = estudianteActualizado

                        println("Estudiante Actualizado!")

                    } ?: println("Estudiante no encontrado.")
                } ?: println("Materia no encontrada.")
            }
            6 -> {
                println("En que materia está el estudiante?")
                printMateriasSinEstudiantes(materias)
                print("Digita el codigo de la materia en la que se encuentre el estudiante: ")
                val codigoMateriaUpdate = readln()
                val materiaDelEstudiante = materias.find { it.codigo == codigoMateriaUpdate }


                materiaDelEstudiante?.let { materia ->
                    val indexmateriaDelEstudiante = materias.indexOf(materia)
                    materia.estudiantes.forEach { println(it) }
                    print("Digita el codigo único del estudiante a eliminar: ")
                    val codigoUnicoInput = reader.nextInt()
                    val removed = materias[indexmateriaDelEstudiante].estudiantes.removeIf { it.codigoUnico == codigoUnicoInput }
                    if(removed) println("Material Eliminada") else println("Material No se pudo eliminar ")
                }?: println("Materia no encontrada.")
            }
            7-> printMaterias(materias)
            8-> break
            else -> continue
        }
    }
    println("Guardando registros")
    materiaService.guardarMateriasEnCSV(materias)
    estudianteService.guardaEstudiantesEnCSV(estudiantes)
    relacionService.guardaEstudianteMateriaEnCSV(relaciones)
    println("Saliendo...")

}

fun printMenu(){
    println("Menu")
    println("1. Ingresa una nueva materia")
    println("2. Modifica una materia")
    println("3. Elimina una materia")
    println("4. Ingresa un nuevo estudiante")
    println("5. Modificar un estudiante")
    println("6. Eliminar un estudiante")
    println("7. Imprimir registros")
    println("8. Salir")
    print("Ingresa el número de la opción seleccionada: ")
}

fun printMaterias(materias : List<Materia>){
    materias.forEach { materia ->
        println(materia.toString())
    }
}

fun printMateriasSinEstudiantes(materias : List<Materia>){
    materias.forEach { materia ->
        println(materia.toStringSinEstudiantes())
    }
}





    /*
    val estudiantes = estudianteService.cargarDesdeCSV()

    val brother = Estudiante(
        "Ana Campoverde",
         Utils.parseDate("12-12-202"),
        "Mecanica",
        201820773,
        36.87 )

    val brother2 = Estudiante(
        "Pedro Sanchez",
        Utils.parseDate("12-12-202"),
        "Mecanica",
        201820773,
        36.87 )

    estudiantes.forEach{estudiante -> println(estudiante.toString())}
    val listStudents = arrayListOf(brother, brother2)
    estudianteService.guardaEstudiantesEnCSV(listStudents)


    val materiaAIngresar = Materia("Auditoria Informática", "ISW225",  true, 28148721);

    val listaMaterias = arrayListOf(materiaAIngresar)
    materiaService.guardarMateriasEnCSV(listaMaterias)

    val materiasRecibidas = materiaService.cargarDesdeCSV()
    materiasRecibidas.forEach{
        materia -> println(materia.toString())
    }

    val relacionAIngresar = EstudianteMateria(201820773, "ISW225");

    val listaMaterias = arrayListOf(relacionAIngresar)
    relacionService.guardaEstudianteMateriaEnCSV(listaMaterias)

    val materiasRecibidas = relacionService.cargarDesdeCSV()
    materiasRecibidas.forEach{
            materia -> println(materia.codigoMateria + "CU"+ materia.codigoUnico)
    }
    */

