import scraping.BusScraper
@main def main(): Unit = {
  val scraper = BusScraper

  val bus042 =
    "https://www.emtu.sp.gov.br/sistemas/linha/resultado1.htm?pag=origemdestino.htm&numlinha=43645&tipo=&rua="
  val bus042VP1 =
    "https://www.emtu.sp.gov.br/sistemas/linha/resultado1.htm?pag=origemdestino.htm&numlinha=43646&tipo=&rua="
  val bus240 =
    "https://www.emtu.sp.gov.br/sistemas/linha/resultado1.htm?pag=origemdestino.htm&numlinha=45065&tipo=&rua="
  val bus281 =
    "https://www.emtu.sp.gov.br/sistemas/linha/resultado1.htm?pag=origemdestino.htm&numlinha=41273&tipo=&rua="
  val bus375 =
    "https://www.emtu.sp.gov.br/sistemas/linha/resultado1.htm?pag=origemdestino.htm&numlinha=45066&tipo=&rua="
  val bus566 =
    "https://emtu.sp.gov.br/sistemas/linha/resultado1.htm?pag=origemdestino.htm&numlinha=45067&tipo=&rua="
  val bus822 =
    "https://www.emtu.sp.gov.br/sistemas/linha/resultado1.htm?pag=origemdestino.htm&numlinha=43521&tipo=&rua="

  val urls = Seq(bus042, bus042VP1, bus240, bus281, bus566, bus822)
  scraper.result(urls)
  sys.exit(0)
}