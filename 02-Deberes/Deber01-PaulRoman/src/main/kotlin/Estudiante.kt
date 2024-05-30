import java.util.Date;

class Estudiante (
    val nombre: String,
    val fechaNacimiento: Date,
    val carrera: String,
    val codigoUnico: Int,
    val IRA: Double,
) {
    override fun toString(): String {
        return "Estudiante(nombre='$nombre', fechaNacimiento='${Utils.formatDate(fechaNacimiento)}', carrera='$carrera', codigoUnico=$codigoUnico, IRA=$IRA)"
    }
}