import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "example_playajaxform"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "play-ajaxform" % "play-ajaxform_2.10" % "0.1"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers += "Local Play Repository" at "file://C:/Users/Administrator/play_framework/play/repository/local"
  )

}
