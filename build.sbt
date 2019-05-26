name := "search-coding-challenge"

version := "0.1"

scalaVersion := "2.12.8"

scalacOptions += "-Ypartial-unification"
scalacOptions += "-Xfatal-warnings"

libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0-M1"

val circeVersion = "0.10.0"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "4.3.4" % "test")

scalacOptions in Test ++= Seq("-Yrangepos")