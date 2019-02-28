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
  "-keep class * implements org.xml.sax.EntityResolver ",
  "-keep class * { \n    ** MODULE$; \n} ",
  "-keepclassmembernames class scala.concurrent.forkjoin.ForkJoinPool { \n   long eventCount; \n   int  workerCounts; \n   int  runControl; \n   scala.concurrent.forkjoin.ForkJoinPool$WaitQueueNode syncStack;  \n   scala.concurrent.forkjoin.ForkJoinPool$WaitQueueNode spareStack; \n} ",
  "-keepclassmembernames class scala.concurrent.forkjoin.ForkJoinWorkerThread { \n    int base; \n    int sp; \n    int runState; \n} ",
  "-keepclassmembernames class scala.concurrent.forkjoin.ForkJoinTask { \n    int status; \n} ",
  "-keepclassmembernames class scala.concurrent.forkjoin.LinkedTransferQueue { \n    scala.concurrent.forkjoin.LinkedTransferQueue$PaddedAtomicReference head;   \n    scala.concurrent.forkjoin.LinkedTransferQueue$PaddedAtomicReference tail; \n    scala.concurrent.forkjoin.LinkedTransferQueue$PaddedAtomicReference cleanMe; \n} ",
  "-keepattributes InnerClasses,EnclosingMethod,Signature,*Annotation*",
  "-keep class scala.** { *; }",
  "-keep interface scala.** { *; }",
  "-keep enum scala.** { *; }",
  "-dontoptimize",
  "-dontwarn scala.** "
)

proguardInputs in Proguard := Seq( (assemblyOutputPath in assembly).value )
proguardOutputs in Proguard := Seq(file("jar/licence-key-encoded.jar"))

proguard in Proguard := (proguard in Proguard).dependsOn(assembly).value