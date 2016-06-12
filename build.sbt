name := """cycle-antenna"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "mysql" % "mysql-connector-java" % "5.1.39",
  "org.flywaydb" %% "flyway-play" % "3.0.0",
  "org.scalikejdbc" %% "scalikejdbc"                  % "2.4.0",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "2.4.0",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.5.1"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
