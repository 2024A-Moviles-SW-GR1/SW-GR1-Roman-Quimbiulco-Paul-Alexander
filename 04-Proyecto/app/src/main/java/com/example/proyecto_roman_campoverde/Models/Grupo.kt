package com.example.proyecto_roman_campoverde.Models
import android.os.Parcel
import android.os.Parcelable

data class Grupo(
    val codUnico: Int,
    val nombre: String,
    val horaInicioLunes: Int,
    val horaFinLunes: Int,
    val horaInicioMartes: Int,
    val horaFinMartes: Int,
    val horaInicioMiercoles: Int,
    val horaFinMiercoles: Int,
    val horaInicioJueves: Int,
    val horaFinJueves: Int,
    val horaInicioViernes: Int,
    val horaFinViernes: Int,
    val idMateria: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        codUnico = parcel.readInt(),
        nombre = parcel.readString() ?: "",
        horaInicioLunes = parcel.readInt(),
        horaFinLunes = parcel.readInt(),
        horaInicioMartes = parcel.readInt(),
        horaFinMartes = parcel.readInt(),
        horaInicioMiercoles = parcel.readInt(),
        horaFinMiercoles = parcel.readInt(),
        horaInicioJueves = parcel.readInt(),
        horaFinJueves = parcel.readInt(),
        horaInicioViernes = parcel.readInt(),
        horaFinViernes = parcel.readInt(),
        idMateria = parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(codUnico)
        parcel.writeString(nombre)
        parcel.writeInt(horaInicioLunes)
        parcel.writeInt(horaFinLunes)
        parcel.writeInt(horaInicioMartes)
        parcel.writeInt(horaFinMartes)
        parcel.writeInt(horaInicioMiercoles)
        parcel.writeInt(horaFinMiercoles)
        parcel.writeInt(horaInicioJueves)
        parcel.writeInt(horaFinJueves)
        parcel.writeInt(horaInicioViernes)
        parcel.writeInt(horaFinViernes)
        parcel.writeInt(idMateria)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Grupo> {
        override fun createFromParcel(parcel: Parcel): Grupo {
            return Grupo(parcel)
        }

        override fun newArray(size: Int): Array<Grupo?> {
            return arrayOfNulls(size)
        }
    }

}
