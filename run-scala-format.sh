#!/bin/bash

sbt scalafmtAll scalafmtSbt clean compile Test/compile
