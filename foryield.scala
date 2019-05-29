/** Ejemplo for-yield Lista de pares.
* La expression for en la función introduce una nueva variable i de tipo Int la que representa 
* todos los valores de la lista List.range.
* La condición if i % 2 == 0 filtra los números impares
* La función retorna una lista de número pares en el rango especificado por los parámetros 
* desde - hasta
**/
object ComprehensionTest1 extends App {
  def pares(desde: Int, hasta: Int): List[Int] =
    for (i <- List.range(desde, hasta) if i % 2 == 0) yield i

    println(pares(0, 20))
}


// Ejemplo for-yield – Pares
// Armar pares de todos de números entre 0 y n-1, cuya suma es igual a un número dado v
 
object ComprehensionTest2 extends App {
  def foo(n: Int, v: Int) =
    for (i <- 0 until n;
         j <- i until n if i + j == v) yield
      Pair(i, j);
  foo(20, 32) foreach {
    case (i, j) =>
      println("(" + i + ", " + j + ")")
  }
}

// Ejemplo for – Omitiendo yield
// Los enumeradores o filtros son utilizados con fines colaterales (modificaciones en este caso)

object ComprehensionTest3 extends App {
  for (i <- Iterator.range(0, 20);
       j <- Iterator.range(i, 20) if i + j == 32)
    println("(" + i + ", " + j + ")")
}
