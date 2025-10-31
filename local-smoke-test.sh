#!/usr/bin/env bash
./run-scala-format.sh
sbt -DrunLocal=true -Dperftest.runSmokeTest=true \
-DjourneysToRun.0='nilMonthlyReturn' \
Gatling/test
