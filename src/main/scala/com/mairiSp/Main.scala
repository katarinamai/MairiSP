package com.mariSp

import scraping.BusScraper // Importe a classe BusScraper corretamente

object Main extends App {
  def main(args: Array[String]): Unit = {
    // Criando uma instância do BusScraper
    val scraper = new BusScraper()

    // Chamando o método de scraping
    val busSchedules = scraper.scrapeBusSchedules()

    // Exibindo os horários de ônibus
    busSchedules.foreach(println)
  }
}
