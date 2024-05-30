import java.io.File
import java.sql.Date

class EstudianteService {
    fun guardaEstudiantesEnCSV(estudiantes: List<Estudiante>) {
        val csvHeader = "Nombre,FechaNacimiento,Carrera,CodigoUnico,IRA\n"
        val file = File(Constants.ESTUDIANTESFILEPATH)
        // Create the file with a header if it does not exist
        if (!file.exists()) {
            file.createNewFile()
        }
        file.writeText(csvHeader)
        estudiantes.forEach { estudiante ->
            val csvContent = "${estudiante.nombre},${Utils.formatDate(estudiante.fechaNacimiento)},${estudiante.carrera},${estudiante.codigoUnico},${estudiante.IRA}\n"
            file.appendText(csvContent)
        }
    }

    fun cargarDesdeCSV(filePath: String = Constants.ESTUDIANTESFILEPATH): List<Estudiante> {
        val estudiantes = mutableListOf<Estudiante>()
        var isFirstLine = true
        File(filePath).forEachLine { line ->
            if (isFirstLine) {
                isFirstLine = false // Skip header line
            } else {
                val fields = line.split(",")
                if (fields.size >= 5) {
                    val nombre = fields[0]
                    val fechaNamiento = fields[1]
                    val carrera = fields[2]
                    val codigoUnico = fields[3].toInt()
                    val IRA = fields[4].toDouble()
                    estudiantes.add(Estudiante(nombre, Utils.parseDate(fechaNamiento), carrera, codigoUnico,IRA ))
                }
            }
        }
        return estudiantes
    }
}