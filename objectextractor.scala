import java.time.{LocalDate, LocalDateTime, LocalTime}


object DateTime {
  def unapply(dt: LocalDateTime): Some[(LocalDate, LocalTime)] =
    Some((dt.toLocalDate, dt.toLocalTime))
}

object Date {
  def unapply(d: LocalDate): Some[(Int, Int, Int)] =
    Some((d.getYear, d.getMonthValue, d.getDayOfMonth))
}

object Time {
  def unapply(t: LocalTime): Some[(Int, Int, Int)] =
    Some((t.getHour, t.getMinute, t.getSecond))
}

// uso de los deconstructores
val Date(year, month, day) = LocalDate.now
val Time(hour, minute, second) = LocalTime.now

val Date(_, month, day) = LocalDate.now
val DateTime(Date(y, m, d), Time(h, mm, s)) = LocalDateTime.now
