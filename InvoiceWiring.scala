// Listing: /net/liftweb/seventhings/snippet/InvoiceWiring.scala
  def subtotal = WiringUI.toNode(Info.subtotal)(doubleDraw)

  /**
   * Wire an element to taxable
   */
  def taxable = WiringUI.toNode(Info.taxable)(doubleDraw)

  def tax = WiringUI.toNode(Info.tax, JqWiringSupport.fade)(doubleDraw)

  def total = WiringUI.toNode(Info.total, JqWiringSupport.fade)(doubleDraw)
