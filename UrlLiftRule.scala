object ReportRules extends Serializable {

    def appendDispatchRules = LiftRules.dispatch.append {
    case Req("report" :: "customer" :: "list" :: reportType :: _, _, GetRequest) => {
      val customerReport = new DownloadReportCustomer()
      val data: Array[Byte] = customerReport.downloadReport(reportType).toByteArray
      val filename: String = "attachment; filename=customers_" + "." + reportType
      var contentType: String = reportType match {
        case "xlsx" => "vnd.ms-excel"
        case "pdf"  => "pdf"
        case "xls"  => "vnd.ms-excel"
        case _      => "none"
      }

      contentType match {
        case _ =>  contentType = "application/" + contentType
      }
      val headers: List[(String, String)] =
        ("Content-type" -> contentType) ::
          ("Content-length" -> data.length.toString) ::
          ("Content-disposition" -> filename) ::
          Nil

      () => Full(StreamingResponse( new java.io.ByteArrayInputStream(data), () => {}, data.length, headers, Nil, 200) )
    }
  }

}
