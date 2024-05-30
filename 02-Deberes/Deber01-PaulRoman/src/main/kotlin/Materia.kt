import java.io.File

class Materia(
    val nombre: String,
    var codigo: String,
    var activo: Boolean,
    var codigoProfesor: Int
) {
    var estudiantes = mutableListOf<Estudiante>()

    override fun toString(): String {
        var out = "Materia(nombre='$nombre', codigo='$codigo', activo=$activo, codigoProfesor=$codigoProfesor)"
        if (estudiantes.isNotEmpty()){
            estudiantes.forEach{ estudiante ->
                out += '\n'+ "   " + estudiante.toString()
            }
        }
        return out
    }

    fun toStringSinEstudiantes():String {
        return "Materia(nombre='$nombre', codigo='$codigo', activo=$activo, codigoProfesor=$codigoProfesor)"
    }
}
