name := "licence-key-generator"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

assemblyOutputPath in assembly := file("jar/licence-key.jar")

enablePlugins(SbtProguard)

proguardMerge in Proguard := false
proguardVersion in Proguard := "6.0"
proguardInputFilter in Proguard := {file => None}

proguardOptions in Proguard ++= Seq(

  "-keepclasseswithmembers public class * { \n    public static void main(java.lang.String[]); \n} ",
  "-keep class * { \n    ** MODULE$; \n} ",
  "-keep class scala.** { *; }",
  "-keep interface scala.** { *; }",
  "-keep enum scala.** { *; }",
  "-dontwarn scala.** ",
  "-dontnote"
)

proguardInputs in Proguard := Seq( (assemblyOutputPath in assembly).value )
proguardOutputs in Proguard := Seq(file("jar/licence-key-encoded.jar"))

proguard in Proguard := (proguard in Proguard).dependsOn(assembly).value