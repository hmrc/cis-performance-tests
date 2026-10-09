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

object ViewVerificationHistoryRequests extends ServicesConfiguration with CisPerformanceTestBase {

  val getSelectTaxYear: HttpRequestBuilder =
    http("[get ] Which tax year do you want to view verification requests from?")
      .get(cisManageFrontendUrl + "/verify/history/select-tax-year")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postSelectTaxYear(option: String): HttpRequestBuilder =
    http("[post] Which tax year do you want to view verification requests from?")
      .post(cisManageFrontendUrl + "/verify/history/select-tax-year")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAllTaxYear: HttpRequestBuilder =
    http("[get ] Verification request history for all tax years")
      .get(cisManageFrontendUrl + "/verify/history/all")
      .check(status.is(200))

  def getViewVerificationRequest(batchId: String): HttpRequestBuilder =
    http("[get ] Verification request")
      .get(cisManageFrontendUrl + "/verify/verification-request")
      .queryParam("verificationBatchId", batchId)
      .check(status.is(200))

  def getViewSubmissionReceipt(batchId: String): HttpRequestBuilder =
    http("[get ] Submission receipt")
      .get(cisManageFrontendUrl + "/verify/submission-receipt")
      .queryParam("verificationBatchId", batchId)
      .check(status.is(200))
}
