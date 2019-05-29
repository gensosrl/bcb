
object Simulation {
    def main(args: Array[String]): Unit= {

    trait A {
      var distance: Int = _
      def action = {
        distance = distance + 5
      }
    }

    trait B {
      var driverVar: Int = _
      def action = {
        driverVar = driverVar + 1
      }
    }

    class AB extends A with B {
     distance = 3;
     driverVar = 6;
     override def action = {
      super[A].action
      super[B].action
     }
    }

    var ab = new AB
    ab.action
    println(ab.driverVar)
    println(ab.distance)
  }
}
