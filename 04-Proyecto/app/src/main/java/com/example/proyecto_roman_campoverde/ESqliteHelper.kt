package com.example.proyecto_roman_campoverde

import android.content.ContentValues
import com.example.proyecto_roman_campoverde.Models.*;
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.deber02_paulroman.Models.Materia

class ESqliteHelper(context: Context) : SQLiteOpenHelper(
    context,
    "moviles",
    null,
    1
) {

    init {
        // Ensure the context is the application context to avoid memory leaks
        if (INSTANCE == null) {
            INSTANCE = this
        }
    }

    companion object {
        private var INSTANCE: ESqliteHelper? = null

        fun getInstance(context: Context): ESqliteHelper {
            if (INSTANCE == null) {
                synchronized(ESqliteHelper::class) {
                    if (INSTANCE == null) {
                        INSTANCE = ESqliteHelper(context.applicationContext)
                    }
                }
            }
            return INSTANCE!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaMateria =
            """
            CREATE TABLE MATERIA(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                codigo VARCHAR(50)
            )
        """.trimIndent()

        val scriptSQLCrearTablaGrupos =
            """
    CREATE TABLE GRUPO(
        codUnico INTEGER PRIMARY KEY AUTOINCREMENT,
        nombre VARCHAR(10),
        hora_inicio_lunes INTEGER,
        hora_fin_lunes INTEGER,
        hora_inicio_martes INTEGER,
        hora_fin_martes INTEGER,
        hora_inicio_miercoles INTEGER,
        hora_fin_miercoles INTEGER,
        hora_inicio_jueves INTEGER,
        hora_fin_jueves INTEGER,
        hora_inicio_viernes INTEGER,
        hora_fin_viernes INTEGER,
        idMateria INTEGER,
        FOREIGN KEY (idMateria) REFERENCES Materia(id)
    )
    """.trimIndent()

        db?.execSQL(scriptSQLCrearTablaMateria)
        db?.execSQL(scriptSQLCrearTablaGrupos)

        // Insertar 5 elementos en la tabla MATERIA
        val insertMaterias = """
    INSERT INTO MATERIA (nombre, codigo) VALUES
    ('Matemáticas', 'MAT101'),
    ('Física', 'FIS101'),
    ('Química', 'QUI101'),
    ('Biología', 'BIO101'),
    ('Historia', 'HIS101');
    """.trimIndent()


        db?.execSQL(insertMaterias)
        Log.d("DB_DEBUG", "Materias insertadas correctamente")

        // Insertar estudiantes en la tabla ESTUDIANTE
        val insertGrupos = """
        INSERT INTO GRUPO (nombre, hora_inicio_lunes, hora_fin_lunes, hora_inicio_martes, hora_fin_martes, 
                           hora_inicio_miercoles, hora_fin_miercoles, hora_inicio_jueves, hora_fin_jueves,
                           hora_inicio_viernes, hora_fin_viernes, idMateria) VALUES
        ('G1', 800, 1000, 0, 0, 800, 1000, 0, 0, 800, 1000, 1),
        ('G2', 0, 0, 1000, 1200, 0, 0, 1000, 1200, 0, 0, 2),
        ('G3', 900, 1100, 900, 1100, 0, 0, 900, 1100, 0, 0, 3),
        ('G4', 0, 0, 1100, 1300, 1100, 1300, 0, 0, 1100, 1300, 4),
        ('G5', 1200, 1400, 0, 0, 1200, 1400, 0, 0, 0, 0, 5);
        """.trimIndent()


        db?.execSQL(insertGrupos)
        Log.d("DB_DEBUG", "Estudiantes insertados correctamente")
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS GRUPO")
        db.execSQL("DROP TABLE IF EXISTS MATERIA")
        onCreate(db)
    }

    // Método para obtener todas las materias
    fun obtenerMaterias(): List<Materia> {
        val materias = mutableListOf<Materia>()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM MATERIA", null)
        if (cursor.moveToFirst()) {
            do {
                val materia = Materia(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    codigo = cursor.getString(cursor.getColumnIndexOrThrow("codigo"))
                )
                materias.add(materia)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return materias
    }

    // Método para obtener todos los grupos
    fun obtenerGrupos(): List<Grupo> {
        val grupos = mutableListOf<Grupo>()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM GRUPO", null)
        if (cursor.moveToFirst()) {
            do {
                val grupo = Grupo(
                    codUnico = cursor.getInt(cursor.getColumnIndexOrThrow("codUnico")),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    horaInicioLunes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_lunes")),
                    horaFinLunes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_lunes")),
                    horaInicioMartes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_martes")),
                    horaFinMartes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_martes")),
                    horaInicioMiercoles = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_miercoles")),
                    horaFinMiercoles = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_miercoles")),
                    horaInicioJueves = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_jueves")),
                    horaFinJueves = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_jueves")),
                    horaInicioViernes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_viernes")),
                    horaFinViernes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_viernes")),
                    idMateria = cursor.getInt(cursor.getColumnIndexOrThrow("idMateria"))
                )
                grupos.add(grupo)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return grupos
    }

    // Método para buscar una materia por nombre
    fun obtenerGruposPorNombreParcial(nombreParcial: String): List<Grupo> {
        val grupos = mutableListOf<Grupo>()
        val db = this.readableDatabase

        // Usar LIKE en la consulta para búsqueda parcial
        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM GRUPO WHERE nombre LIKE ?",
            arrayOf("%$nombreParcial%")
        )

        if (cursor.moveToFirst()) {
            do {
                val grupo = Grupo(
                    codUnico = cursor.getInt(cursor.getColumnIndexOrThrow("codUnico")),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    horaInicioLunes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_lunes")),
                    horaFinLunes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_lunes")),
                    horaInicioMartes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_martes")),
                    horaFinMartes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_martes")),
                    horaInicioMiercoles = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_miercoles")),
                    horaFinMiercoles = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_miercoles")),
                    horaInicioJueves = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_jueves")),
                    horaFinJueves = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_jueves")),
                    horaInicioViernes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_viernes")),
                    horaFinViernes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_viernes")),
                    idMateria = cursor.getInt(cursor.getColumnIndexOrThrow("idMateria"))
                )
                grupos.add(grupo)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return grupos
    }

    // Método para obtener una materia por su ID
    fun obtenerMateriaPorId(id: Int): Materia? {
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM MATERIA WHERE id = ?", arrayOf(id.toString()))

        return if (cursor.moveToFirst()) {
            val materia = Materia(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("codigo"))
            )
            cursor.close()
            materia
        } else {
            cursor.close()
            null
        }
    }


    // Método para obtener todos los grupos de una materia por idMateria
    fun obtenerGruposPorIdMateria(idMateria: Int): List<Grupo> {
        val grupos = mutableListOf<Grupo>()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM GRUPO WHERE idMateria = ?", arrayOf(idMateria.toString()))
        if (cursor.moveToFirst()) {
            do {
                val grupo = Grupo(
                    codUnico = cursor.getInt(cursor.getColumnIndexOrThrow("codUnico")),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    horaInicioLunes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_lunes")),
                    horaFinLunes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_lunes")),
                    horaInicioMartes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_martes")),
                    horaFinMartes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_martes")),
                    horaInicioMiercoles = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_miercoles")),
                    horaFinMiercoles = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_miercoles")),
                    horaInicioJueves = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_jueves")),
                    horaFinJueves = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_jueves")),
                    horaInicioViernes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_viernes")),
                    horaFinViernes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_viernes")),
                    idMateria = cursor.getInt(cursor.getColumnIndexOrThrow("idMateria"))
                )
                grupos.add(grupo)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return grupos
    }

    fun obtenerGrupoPorId(codUnico: Int): Grupo? {
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM GRUPO WHERE codUnico = ?", arrayOf(codUnico.toString()))

        return if (cursor.moveToFirst()) {
            val grupo = Grupo(
                codUnico = cursor.getInt(cursor.getColumnIndexOrThrow("codUnico")),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                horaInicioLunes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_lunes")),
                horaFinLunes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_lunes")),
                horaInicioMartes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_martes")),
                horaFinMartes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_martes")),
                horaInicioMiercoles = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_miercoles")),
                horaFinMiercoles = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_miercoles")),
                horaInicioJueves = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_jueves")),
                horaFinJueves = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_jueves")),
                horaInicioViernes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_inicio_viernes")),
                horaFinViernes = cursor.getInt(cursor.getColumnIndexOrThrow("hora_fin_viernes")),
                idMateria = cursor.getInt(cursor.getColumnIndexOrThrow("idMateria"))
            )
            cursor.close()
            grupo
        } else {
            cursor.close()
            null
        }
    }

}