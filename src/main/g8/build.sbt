Global / onChangedBuildSource := ReloadOnSourceChanges

scalaVersion := "3.2.2"

enablePlugins(ScalaNativePlugin)

// set to Debug for compilation details (Info is default)
logLevel := Level.Info

// import to add Scala Native options
import scala.scalanative.build._

// defaults set with common options shown
nativeConfig ~= { c =>
  c.withLTO(LTO.none) // thin
    .withMode(Mode.debug) // releaseFast
    .withGC(GC.immix) // commix
}

def sttp3Version = "3.8.15"
def jsoniterVersion = "2.23.0"

libraryDependencies ++= Seq(
  "com.softwaremill.sttp.client3" %%% "core" % sttp3Version, // Contains CurlBackend for scala-native
  "com.softwaremill.sttp.client3" %%% "jsoniter" % sttp3Version,
)

libraryDependencies ++= Seq(
  // Use the %%% operator instead of %% for Scala.js and Scala Native
  "com.github.plokhotnyuk.jsoniter-scala" %%% "jsoniter-scala-core"   % jsoniterVersion,
  // Use the "provided" scope instead when the "compile-internal" scope is not supported  
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % jsoniterVersion % "compile-internal"
)

