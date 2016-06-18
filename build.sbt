name := """cycle-antenna"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,  
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "mysql" % "mysql-connector-java" % "5.1.39",
  "org.flywaydb" %% "flyway-play" % "3.0.0",
  "org.scalikejdbc" %% "scalikejdbc"                  % "2.4.0",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "2.4.0",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.5.1",
  "org.scalikejdbc" %% "scalikejdbc-test"             % "2.2.0"  % "test",
  "org.scalikejdbc" %% "scalikejdbc-play-fixture-plugin" % "2.3.2",
  "joda-time" % "joda-time" % "2.7",
  "org.joda" % "joda-convert" % "1.7"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
