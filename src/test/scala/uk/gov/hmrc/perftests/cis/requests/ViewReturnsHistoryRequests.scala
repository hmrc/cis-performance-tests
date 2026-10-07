/*
 * Copyright 2026 HM Revenue & Customs
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

object ViewReturnsHistoryRequests extends ServicesConfiguration with CisPerformanceTestBase {

  val getIncompleteReturnPage: HttpRequestBuilder =
    http("[get ] Incomplete returns")
      .get(cisManageFrontendUrl + "/manage-your-cis-return/incomplete-returns")
      .check(status.is(200))

  val getTaxYearToViewPage: HttpRequestBuilder =
    http("[get ] Which tax year do you want to view")
      .get(cisManageFrontendUrl + "/history/view-tax-year-submission-history")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postTaxYearToViewPage(year: String): HttpRequestBuilder =
    http("[post] Which tax year do you want to view")
      .post(cisManageFrontendUrl + "/history/view-tax-year-submission-history")
      .formParam("value", year)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  def getMonthlyReturnHistorySingleYear(year: String): HttpRequestBuilder =
    http("[get ] Monthly return history for single tax year")
      .get(cisManageFrontendUrl + "/history/monthly-return-history-tax-year/" + year)
      .check(status.is(200))

  val getMonthlyReturnHistoryAllYears: HttpRequestBuilder =
    http("[get ] Monthly return history for all tax years")
      .get(cisManageFrontendUrl + "/history/monthly-return-history-all-tax-years")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

}
