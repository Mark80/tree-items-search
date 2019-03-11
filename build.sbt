lazy val akkaHttpVersion = "10.1.7"
lazy val akkaVersion    = "2.5.21"

lazy val root = (project in file("."))
    .settings(
    Defaults.itSettings,
    inThisBuild(List(
      organization    := "nokia",
      scalaVersion    := "2.12.8"
    )),
    name := "tree-items-search",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka"    %% "akka-http"            % akkaHttpVersion,
      "com.typesafe.akka"    %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka"    %% "akka-stream"          % akkaVersion,

      "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     % Test,
      "org.scalatest"     %% "scalatest"            % "3.0.5"         % Test,
      "org.mockito"       %  "mockito-all"          % "1.10.19"       % Test)

  )