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
    }

    busData.foreach { bus =>
      val data = GetDataReturn(bus)
      println(data)
    }
  }

  private def scrapeData(uri: String): String = {
    val request = quickRequest
      .get(uri"$uri")

    val response = request.send()
    val body = response.body

    body
  }

  private def GetDataDeparture(body: String): String = {
    val document: Document = Jsoup.parse(body, "UTF-8")
    val element = document.getElementById("horarioIda")
    val linha = document.getElementsByClass("destaque")

    if (element == null || linha == null) {
      "Não há horários disponíveis"
    } else {
      val text = element.text()
      val linhaNumber = linha.text()
      val departure = Seq(text, linhaNumber).mkString(" - ")
      departure
    }
  }

  private def GetDataReturn(body: String): String = {
    val document: Document = Jsoup.parse(body, "utf8")

    val element = document.getElementById("horarioVolta")
    val linha = document.getElementsByClass("destaque")
    val names = document.getElementsByTag("b")

    if (element == null || linha == null) {
      "Não há horários disponíveis"
    } else {
      val text = element.text()
      val linhaNumber = linha.text()
      val returnData = Seq(text, linhaNumber).mkString(" - ")
      returnData
    }
  }
}
