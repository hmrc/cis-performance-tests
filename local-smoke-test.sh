#!/usr/bin/env bash
./run-scala-format.sh
sbt -Denvironment=local -DrunLocal=true -Dperftest.runSmokeTest=true Gatling/test
