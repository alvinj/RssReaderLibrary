name := "RssReaderLibrary"

version := "0.1"

scalaVersion := "2.12.4"

scalacOptions += "-deprecation"

libraryDependencies ++= Seq(
    "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
    "org.scalaj" %% "scalaj-http" % "2.3.0"
)


