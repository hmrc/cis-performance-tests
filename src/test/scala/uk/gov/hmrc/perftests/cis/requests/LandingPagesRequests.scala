/*
 * Copyright 2023 HM Revenue & Customs
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

package uk.gov.hmrc.perftests.cis.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

object LandingPagesRequests extends ServicesConfiguration with CisPerformanceTestBase {

  val getManageFrontend: HttpRequestBuilder =
    http("[get ] Manage Frontend")
      .get(cisManageFrontendUrl)
      .check(status.is(303))

  val getSignIntoCISPage: HttpRequestBuilder =
    http("[get ] Construction Industry Scheme (CIS) page")
      .get(cisManageFrontendUrl + "/sign-into-cis")
      .check(status.is(200))

  val getSignIntoCISRouting: HttpRequestBuilder =
    http("[get ] CIS routing")
      .get(cisManageFrontendUrl + "/sign-into-cis-routing")
      .check(status.is(303))

  val getCisReturnDashboardPage: HttpRequestBuilder =
    http("[get ] CIS Return Dashboard page")
      .get(cisManageFrontendUrl + "/org/cis-return-dashboard")
      .check(status.is(200))

  val getRetrieveClientList: HttpRequestBuilder =
    http("[get ] Retrieve Client List")
      .get(cisManageFrontendUrl + "/agent/retrieve-client-list")
      .check(status.is(200))

  val getStart: HttpRequestBuilder =
    http("[get ] Start Retrieve Client List")
      .get(cisManageFrontendUrl + "/agent/retrieve-client-list/start")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getFileMonthlyCISReturnPage: HttpRequestBuilder =
    http("[get ] File monthly CIS returns page")
      .get(cisManageFrontendUrl + "/agent/file-monthly-cis-returns")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postFilterFileMonthlyCISReturnPage: HttpRequestBuilder =
    http("[post] Filter File monthly CIS returns page")
      .post(cisManageFrontendUrl + "/agent/file-monthly-cis-returns")
      .check(status.is(303))

  val getFilterFileMonthlyCISReturnPage: HttpRequestBuilder =
    http("[get ] Filter File monthly CIS returns page")
      .get(cisManageFrontendUrl + "/agent/file-monthly-cis-returns")
      .formParam("value", "ABC")
      .check(status.is(200))

  val getAgentCisReturnDashboardPage: HttpRequestBuilder =
    http("[get ] Agent CIS Return Dashboard page")
      .get(cisManageFrontendUrl + "/agent/cis-return-dashboard/1")
      .check(status.is(200))

}
