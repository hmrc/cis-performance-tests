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

object CisRequests extends ServicesConfiguration with CisPerformanceTestBase {

  val getConstructionIndustryScheme: HttpRequestBuilder =
    http("[get] Construction Industry Scheme")
      .get(cisFrontendUrl)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getConfirmNilReturnPage: HttpRequestBuilder =
    http("[get] Confirm nil return page")
      .get(cisFrontendUrl + "/monthly-return/date-confirm-nil-payments")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postConfirmNilReturnPage(month: String, year: String): HttpRequestBuilder =
    http("[post] Confirm nil return page")
      .post(cisFrontendUrl + "/monthly-return/date-confirm-nil-payments")
      .formParam("value.month", month)
      .formParam("value.year", year)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDoYouWantToSubmitAnInactivityRequestPage: HttpRequestBuilder =
    http("[get] Do You Want To Submit An Inactivity Request page")
      .get(cisFrontendUrl + "/monthly-return/submit-inactive-request")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoYouWantToSubmitAnInactivityRequestPage(option: String): HttpRequestBuilder =
    http("[post] Do You Want To Submit An Inactivity Request page")
      .post(cisFrontendUrl + "/monthly-return/submit-inactive-request")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getConfirmEmailAddressPage: HttpRequestBuilder =
    http("[get] Confirmation Email Address page")
      .get(cisFrontendUrl + "/monthly-return/confirm-email-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postConfirmEmailAddressPage(email: String): HttpRequestBuilder =
    http("[post] Confirmation Email Address page")
      .post(cisFrontendUrl + "/monthly-return/confirm-email-address")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDeclarationPage: HttpRequestBuilder =
    http("[get] Declaration page")
      .get(cisFrontendUrl + "/monthly-return/declaration")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDeclarationPage(option: String): HttpRequestBuilder =
    http("[post] Declaration page")
      .post(cisFrontendUrl + "/monthly-return/declaration")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getInactivityWarningPage: HttpRequestBuilder =
    http("[get] Inactivity Warning page")
      .get(cisFrontendUrl + "/monthly-return/nil-month-return-inactive")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postInactivityWarningPage: HttpRequestBuilder =
    http("[post] Inactivity Warning page")
      .post(cisFrontendUrl + "/monthly-return/nil-month-return-inactive")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCheckYourAnswersPage: HttpRequestBuilder =
    http("[get] Get Check your answers page")
      .get(cisFrontendUrl + "/monthly-return/check-your-answers")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postCheckYourAnswersPage: HttpRequestBuilder =
    http("[post] Post Check your answers")
      .post(cisFrontendUrl + "/monthly-return/check-your-answers")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeConfirmNilReturnPage: HttpRequestBuilder =
    http("[get] Change confirm nil return page")
      .get(cisFrontendUrl + "/monthly-return/change-date-confirm-nil-payments")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeConfirmNilReturnPage(month: String, year: String): HttpRequestBuilder =
    http("[post] Change confirm nil return page")
      .post(cisFrontendUrl + "/monthly-return/change-date-confirm-nil-payments")
      .formParam("value.month", month)
      .formParam("value.year", year)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeDoYouWantToSubmitAnInactivityRequestPage: HttpRequestBuilder =
    http("[get] Change Do You Want To Submit An Inactivity Request page")
      .get(cisFrontendUrl + "/monthly-return/change-submit-inactive-request")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeDoYouWantToSubmitAnInactivityRequestPage(option: String): HttpRequestBuilder =
    http("[post] Change Do You Want To Submit An Inactivity Request page")
      .post(cisFrontendUrl + "/monthly-return/change-submit-inactive-request")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeConfirmEmailAddressPage: HttpRequestBuilder =
    http("[get] Change Confirmation Email Address page")
      .get(cisFrontendUrl + "/monthly-return/change-confirm-email-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeConfirmEmailAddressPage(email: String): HttpRequestBuilder =
    http("[post] Change Confirmation Email Address page")
      .post(cisFrontendUrl + "/monthly-return/change-confirm-email-address")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getSendingSubmissionPage: HttpRequestBuilder =
    http("[get] Submission Sending page")
      .get(cisFrontendUrl + "/monthly-return/submission-send/polling")
      .check(status.is(303))

  val getPollingPage: HttpRequestBuilder =
    http("[get] Polling")
      .get(cisFrontendUrl + "/monthly-return/submission-send")
      .check(status.is(303))

  val getSuccessfulSubmissionPage: HttpRequestBuilder =
    http("[get] Successful Submission page")
      .get(cisFrontendUrl + "/monthly-return/confirmation")
      .check(status.is(200))
}
