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

object AmendedReturnRequests extends ServicesConfiguration with CisPerformanceTestBase {

  val getWhichTaxYearToView: HttpRequestBuilder =
    http("[get ] Which tax year do you want to view?")
      .get(cisManageFrontendUrl + "/history/view-tax-year-submission-history")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhichTaxYearToView(option: String): HttpRequestBuilder =
    http("[post] Which tax year do you want to view?")
      .post(cisManageFrontendUrl + "/history/view-tax-year-submission-history")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getMonthlyReturnHistoryForAllTaxYears: HttpRequestBuilder =
    http("[get ] Monthly return history for all tax years")
      .get(cisManageFrontendUrl + "/history/monthly-return-history-all-tax-years")
      .check(status.is(200))

  def getConfirmAmendmentRedirect(year: String, month: String): HttpRequestBuilder =
    http("[get ] Confirm amendment handoff redirect")
      .get(cisManageFrontendUrl + s"/history/amend/$year/$month")
      .check(status.is(303))
      .check(headerRegex("Location", "[?&]handoffId=([^&]+)").saveAs("id"))

  val getConfirmAmendmentPage: HttpRequestBuilder =
    http("[get ] Confirm amendment page")
      .get(cisFrontendUrl + "/manage-cis-return/amend-monthly-return/confirm-amendments")
      .queryParam("handoffId", "#{id}")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postConfirmAmendmentPage: HttpRequestBuilder =
    http("[post] Confirm amendment page")
      .post(cisFrontendUrl + "/manage-cis-return/amend-monthly-return/confirm-amendments")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getWhatDoYouWantToAmendNilReturn: HttpRequestBuilder =
    http("[get ] What do you want to amend? (Nil -> Standard)")
      .get(cisFrontendUrl + "/amend-monthly-return/what-do-you-want-to-amend-nil")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatDoYouWantToAmendNilReturn(option: String): HttpRequestBuilder =
    http("[get ] What do you want to amend? (Nil -> Standard)")
      .post(cisFrontendUrl + "/amend-monthly-return/what-do-you-want-to-amend-nil")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getWhatDoYouWantToAmendStandardReturn: HttpRequestBuilder =
    http("[get ] What do you want to amend? (Standard -> Nil)")
      .get(cisFrontendUrl + "/amend-monthly-return/what-do-you-want-to-amend-standard")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatDoYouWantToAmendStandardReturn(option: String): HttpRequestBuilder =
    http("[get ] What do you want to amend? (Standard -> Nil)")
      .post(cisFrontendUrl + "/amend-monthly-return/what-do-you-want-to-amend-standard")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getWhichSubcontractorDoYouWantToAdd: HttpRequestBuilder =
    http("[get ] What do you want to amend?")
      .get(cisFrontendUrl + "/amend-monthly-return/which-subcontractors-to-add")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postWhichSubcontractorDoYouWantToAdd: HttpRequestBuilder =
    http("[get ] What do you want to amend?")
      .post(cisFrontendUrl + "/amend-monthly-return/which-subcontractors-to-add")
      .formParam("value[]", "31001")
      .formParam("value[]", "31275")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val returnToMonthlyReturnLandingPageRedirect: HttpRequestBuilder =
    http("[get ] Return to monthly return landing page redirect")
      .get(cisFrontendUrl + "/monthly-return/manage-cis-return")
      .check(status.is(303))

  val getReturnToLandingPage: HttpRequestBuilder =
    http("[get ] Manage your CIS return")
      .get(cisManageFrontendUrl + "/manage-cis-return/800")
      .queryParam("contractorName", "PAL-355 Scheme")
      .check(status.is(200))

  val getWantToAmendStandardToNil: HttpRequestBuilder =
    http("[get ] Are you sure you want to amend this return to a nil return?")
      .get(cisFrontendUrl + "/amend-monthly-return/are-you-sure-you-want-to-amend")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWantToAmendStandardToNil(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to amend this return to a nil return?")
      .post(cisFrontendUrl + "/amend-monthly-return/are-you-sure-you-want-to-amend")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))
}
