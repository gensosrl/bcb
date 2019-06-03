trait CustomerReport {

  val list = List(CustomerSample.customer1,
    CustomerSample.customer2,
    CustomerSample.customer3,
    CustomerSample.customer4)

  val dataSource= new CustomerSource(list)

  lazy val parameters: java.util.Map[String, AnyRef] = {
    val p: java.util.Map[String, AnyRef] = new java.util.HashMap
    p.put(JRParameter.REPORT_VIRTUALIZER, fileVirtualizer)
    p
  }

  val temporalPath:String  = System.getProperty("user.home") + java.io.File.separator + "tmp"
  val temporalDir:java.io.File  = new java.io.File(temporalPath)
  if (!temporalDir.exists() || !temporalDir.isDirectory()) {
    temporalDir.mkdir()
  }

  lazy val fileVirtualizer: JRFileVirtualizer = new JRFileVirtualizer(3, temporalDir.getAbsolutePath )
}

class DownloadReportCustomer() extends CustomerReport {
  def downloadReport(reportType: String) = {
    generateReport(dataSource, parameters, reportType)
  }

  private def generateReport(source: CustomerSource,
                          parameters: util.Map[String, AnyRef],
                          reportType: String) = {

    val report: JasperReport = JasperCompileManager.compileReport("src/main/resources/reports/customers.jrxml")
    val jasperPrint: JasperPrint = JasperFillManager.fillReport(report, parameters
      ,dataSource)

    val exporter: JRExporter = reportType match {
      case "pdf" => new JRPdfExporter
      case "xlsx" => new JRXlsxExporter
      case "csv" => new JRCsvExporter
      case _ => new JRXhtmlExporter
    }

    val out: java.io.ByteArrayOutputStream = new java.io.ByteArrayOutputStream()

    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint)
    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out)

    exporter.exportReport()
    out
  }
}


object CustomerSample {

  val customer1 = new Customer
  customer1.firstname = "Pedro"
  customer1.lastname = "Mendez"
  customer1.email = "pedro@mail.com"
  customer1.birthday = java.util.Calendar.getInstance().getTime

  val customer2 = new Customer
  customer2.firstname = "Ana"
  customer2.lastname = "Perez"
  customer2.email = "ana@mail.com"
  customer2.birthday = java.util.Calendar.getInstance().getTime

  val customer3 = new Customer
  customer3.firstname = "Juan"
  customer3.lastname = "Quispe"
  customer3.email = "juano@mail.com"
  customer3.birthday = java.util.Calendar.getInstance().getTime

  val customer4 = new Customer
  customer4.firstname = "Mauricio"
  customer4.lastname = "Claros"
  customer4.email = "claros@mail.com"
  customer4.birthday = java.util.Calendar.getInstance().getTime


}

class CustomerSource(val lsBonos: List[Customer]) extends JRDataSource{
  var indiceActual: Int = -1

  def getFieldValue(jrField: JRField) = jrField.getName match {
    case "FIRSTNAME" => lsBonos(indiceActual).firstname
    case "LASTNAME" => lsBonos(indiceActual).lastname
    case "EMAIL" => lsBonos(indiceActual).email
    case "BIRTHDATE" => lsBonos(indiceActual).birthday.toString

  }

  def next():Boolean = {
    indiceActual = indiceActual + 1
    indiceActual < lsBonos.size
  }

}
