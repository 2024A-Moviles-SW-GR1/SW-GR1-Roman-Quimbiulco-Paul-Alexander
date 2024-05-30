import java.text.SimpleDateFormat
import java.util.*


class Utils{
    companion object{

        fun parseDate(dateString: String): Date {
            val pattern = "MM-dd-yyyy"
            val simpleDateFormat = SimpleDateFormat(pattern)
            val date: Date = simpleDateFormat.parse(dateString)
            return date;
        }

        fun formatDate(date: Date): String {
            val pattern = "MM-dd-yyyy"
            val simpleDateFormat = SimpleDateFormat(pattern)
            val dateFormatted = simpleDateFormat.format(date)
            return dateFormatted
        }
    }
}

