#!/usr/bin/env bash
./run-scala-format.sh
sbt -Denvironment=staging -DrunLocal=false -Dperftest.runSmokeTest=true Gatling/test
