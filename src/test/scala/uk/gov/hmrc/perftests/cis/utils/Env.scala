/*
 * Copyright 2025 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.cis.utils

object Env {
  val USE_STUB: String = (Option(System.getProperty("environment")), Option(System.getenv("JENKINS_HOME"))) match {
    case (Some(env), Some(jenkinsHome)) =>
      println(s"Environment property found: $env, running in Jenkins environment: $jenkinsHome")
      env match {
        case "local"   => "false"
        case "staging" => "true"
        case _         => "true"
      }
    case (Some(env), None)              =>
      println(s"Environment property found: $env, not running in Jenkins.")
      env match {
        case "local"   => "false"
        case "staging" => "true"
        case _         => "true"
      }
    case (None, Some(jenkinsHome))      =>
      println(
        s"Environment property not found, but running in Jenkins environment: $jenkinsHome. Defaulting to 'staging'."
      )
      "true"
    case (None, None)                   =>
      println("Environment property not found and not running in Jenkins. Defaulting to 'false'.")
      "false"
  }
}
