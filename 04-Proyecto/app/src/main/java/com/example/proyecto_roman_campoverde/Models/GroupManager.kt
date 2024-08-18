package com.example.proyecto_roman_campoverde.Models

class GrupoManager {
    companion object {
        // Lista estática de grupos
        private val listaGrupos = mutableListOf<Grupo>()

        // Método para obtener la lista completa de grupos
        fun obtenerGrupos(): List<Grupo> {
            return listaGrupos
        }

        // Método para añadir un grupo a la lista
        fun añadirGrupo(grupo: Grupo) {
            listaGrupos.add(grupo)
        }

        // Método para quitar un grupo de la lista por su ID
        fun quitarGrupoPorId(codUnico: Int) {
            listaGrupos.removeIf { it.codUnico == codUnico }
        }

        // Método para limpiar la lista de grupos
        fun limpiarGrupos() {
            listaGrupos.clear()
        }
    }
}