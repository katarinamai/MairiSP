val scala3Version = "3.3.1"
val munitVersion = "0.7.29"
val sttp = "4.0.0-M1"
val jsoup = "1.17.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "mairiSp",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % munitVersion % Test,
      "com.softwaremill.sttp.client4" %% "core" % sttp,
      "org.jsoup" % "jsoup" % jsoup,
      "io.circe" %% "circe-core" % "0.14.6",
      "io.circe" %% "circe-generic" % "0.14.6",
      "io.circe" %% "circe-parser" % "0.14.6",
      "org.apache.commons" % "commons-csv" % "1.10.0"
    )
  )
