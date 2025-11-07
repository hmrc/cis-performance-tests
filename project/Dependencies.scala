import sbt._

object Dependencies {

  val test = Seq(
    "uk.gov.hmrc"          %% "performance-test-runner"   % "6.3.0"         % Test,
  "org.mongodb.scala" %% "mongo-scala-driver" % "5.6.1",
  "com.oracle.database.jdbc" % "ojdbc8" % "19.14.0.0" % Test // Oracle JDBC driver
  )

}
