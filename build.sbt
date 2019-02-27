name := "licence-key-generator"

version := "0.1"

scalaVersion := "2.12.8"

val jar = "jar/license-key.jar"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

assemblyOutputPath in assembly := file(jar)

proguardInputs in Proguard := Seq(baseDirectory.value / jar)
proguardLibraries in Proguard := Seq()
proguardInputFilter in Proguard := { file => None }
proguardMerge in Proguard := false

proguard in Proguard := (proguard in Proguard).dependsOn(assembly).value