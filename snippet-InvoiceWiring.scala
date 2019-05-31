//Listing: /net/liftweb/seventhings/snippet/InvoiceWiring.scala
  private object Info {
    val invoices = ValueCell(List(newLine))
    val taxRate = ValueCell(0.05d)
    val subtotal = invoices.lift(_.foldLeft(0d)(_ + _.price))
    val taxable = invoices.lift(_.filter(_.taxable).
                                foldLeft(0D)(_ + _.price))

    val tax = taxRate.lift(taxable) {_ * _}
https://github.com/gensosrl/bcb
    val total = subtotal.lift(tax) {_ + _}    
  }
