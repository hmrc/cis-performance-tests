#!/usr/bin/env bash
sbt -DrunLocal=true -Dperftest.runSmokeTest=true \
-DjourneysToRun.0='nilMonthlyReturn' \
Gatling/test
