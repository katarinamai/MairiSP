package scraping

import org.jsoup.Jsoup
import scala.collection.JavaConverters._

object BusScraper {
  val doc = Jsoup.connect("http://example.com").get()
  val links = doc.select("a[href]")
  val hrefs = links.asScala.map(_.attr("href"))
  println(hrefs)
}
