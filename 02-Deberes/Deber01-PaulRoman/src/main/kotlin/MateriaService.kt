import java.io.File

class MateriaService {
    fun guardarMateriasEnCSV(materias: List<Materia>) {
        val csvHeader = "Nombre,Codigo,Activo,CodigoProfesor\n"
        val file = File(Constants.MATERIASFILEPATH)

        if (!file.exists()) {
            file.createNewFile()
        }
        file.writeText(csvHeader)
        materias.forEach { materia ->
            val csvContent = "${materia.nombre},${materia.codigo},${materia.activo},${materia.codigoProfesor}\n"
            file.appendText(csvContent)
        }
    }

    fun cargarDesdeCSV(): List<Materia> {
        val materias = mutableListOf<Materia>()
        var isFirstLine = true
        File(Constants.MATERIASFILEPATH).forEachLine { line ->
            if (isFirstLine) {
                isFirstLine = false // Skip header line
            } else {
                val fields = line.split(",")
                if (fields.size >= 4) {
                    val nombre = fields[0]
                    val codigo = fields[1]
                    val activo = fields[2].toBoolean()
                    val codigoProfesor = fields[3].toInt()
                    materias.add(Materia(nombre, codigo, activo, codigoProfesor))
                }
            }
        }
        return materias
    }
}
