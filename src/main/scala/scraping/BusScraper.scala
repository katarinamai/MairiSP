package scraping

import sttp.client4.quick.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object BusScraper {

  def result(urls: Seq[String]): Unit = {

    val busData = urls.map({ buses =>
      scrapeData(buses)
    })

    busData.foreach { bus =>
      val data = GetDataDeparture(bus)
      println(data)
      println("====================================")
    }

    busData.foreach { bus =>
      val data = GetDataReturn(bus)
      println(data)
      println("====================================")
    }
  }

  private def scrapeData(uri: String): String = {
    val request = quickRequest
      .get(uri"$uri")

    val response = request.send()
    val body = response.body

    body
  }

  private def GetDataDeparture(body: String): Seq[String] = {
    val document: Document = Jsoup.parse(body, "ISO-8859-1")
    val element = document.getElementById("horarioIda")
    val tarifa = document.select("td:contains(Tarifa Autorizada:)").first()
    val linha = document.getElementsByClass("destaque")

    if (element == null || linha == null) {
      Seq("Não há horários disponíveis")
    } else {
      val text = element.text()
      val linhaNumber = linha.text()
      val valorTarifa = tarifa.nextElementSibling().text()
      val departure = Seq(text, linhaNumber, valorTarifa)
      departure
    }
  }

  private def GetDataReturn(body: String): Seq[String] = {
    val document: Document = Jsoup.parse(body, "ISO-8859-1")

    val element = document.getElementById("horarioVolta")
    val linha = document.getElementsByClass("destaque")
    val tarifa = document.select("td:contains(Tarifa Autorizada:)").first()
    val names = document.getElementsByTag("b")

    if (element == null || linha == null) {
      Seq("Não há horários disponíveis")
    } else {
      val text = element.text()
      val linhaNumber = linha.text()
      val valorTarifa = tarifa.nextElementSibling().text()

      val diasUteisPadrao = raw"(?<=teis\s).*?(?=\sS|\sDomingos\s)".r
      val sabado = raw"(?<=bados\s).*?(?=\sDomingos\s)".r
      val domingo = raw"(?<=Domingos e Feriados\s).*".r

      val horariosDiasUteis = diasUteisPadrao.findFirstIn(text).getOrElse("")
      val horariosSabado = sabado.findFirstIn(text).getOrElse("")
      val horariosDomingo = domingo.findFirstIn(text).getOrElse("")

      val returnData =
        Seq(text, linhaNumber, valorTarifa, horariosDiasUteis)
      returnData
    }
  }
}
