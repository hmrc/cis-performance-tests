#!/usr/bin/env bash
./run-scala-format.sh
sbt -DrunLocal=false -Dperftest.runSmokeTest=true \
-DjourneysToRun.0='nilMonthlyReturn' \
Gatling/test
