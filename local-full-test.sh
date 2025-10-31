#!/usr/bin/env bash
./run-scala-format.sh
sbt -DrunLocal=true -Dperftest.runSmokeTest=false Gatling/test
