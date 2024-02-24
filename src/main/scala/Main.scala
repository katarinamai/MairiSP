import scraping.BusScraper
@main def main(): Unit = {
  val scraper = BusScraper
  scraper.scrapeData()
}