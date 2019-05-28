package com
{
  package tedneward
  {
    package scala
    {
        // ...
      package mathfun
      {
        object App
        {
          import java.math.BigInteger
          import BigInteger._
         
          def factorial(arg : BigInteger) : BigInteger =
          {
            if (arg == ZERO) ONE
            else arg multiply (factorial (arg subtract ONE))
          }
         
          def main(args : Array[String]) : Unit =
          {
            if (args.length > 0)
              System.out.println("factorial " + args(0) +
                " = " + factorial(new BigInteger(args(0))))
            else
              System.out.println("factorial 0 = 1")
          }
        }
      }
    }
  }
}
