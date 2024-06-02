import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.todayIn

class GreetingNewYear {

    public fun bevan() : String {
        return dayPhrase()
    }

    private fun dayPhrase() : String = "there are only ${daysUntilNewYear()} days left until new year"

    private fun daysUntilNewYear(): Int {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val closestNewYear = LocalDate(today.year + 1, 1,1)
        return  today.daysUntil(closestNewYear)
    }
}