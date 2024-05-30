import java.io.File

class EstudianteMateriaService {
    fun guardaEstudianteMateriaEnCSV( estudianteMateria : List<EstudianteMateria>) {
        val csvHeader = "CODIGOMATERIA, CODIGOUNICO\n"
        val file = File(Constants.ESTUDIANTEMATERIAFILEPATH)
        if (!file.exists()) {
            file.createNewFile()
        }
        file.writeText(csvHeader)
        estudianteMateria.forEach { estudiante ->
            val csvContent = "${estudiante.codigoMateria},${estudiante.codigoUnico}\n"
            file.appendText(csvContent)
        }
    }

    fun cargarDesdeCSV(): List<EstudianteMateria> {
        val estudianteMateria = mutableListOf<EstudianteMateria>()
        var isFirstLine = true
        File(Constants.ESTUDIANTEMATERIAFILEPATH).forEachLine { line ->
            if (isFirstLine) {
                isFirstLine = false // Skip header line
            } else {
                val fields = line.split(",")
                if (fields.size >= 2) {
                    val codigoMateria = fields[0]
                    val codigoUnico = fields[1].toInt()
                    estudianteMateria.add(EstudianteMateria(codigoUnico, codigoMateria))
                }
            }
        }
        return estudianteMateria
    }
}