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
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

import java.time.format.TextStyle
import java.time.{LocalDate, YearMonth}
import java.util.Locale
import scala.concurrent.duration.DurationInt
import scala.util.Random

object NilMonthlyReturnRequests extends ServicesConfiguration with CisPerformanceTestBase {

  val getFileYourNilReturnPage: HttpRequestBuilder =
    http("[get ] File your nil return page")
      .get(cisFrontendUrl + "/monthly-return/file-your-nil-return/800")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postFileYourNilReturnPage: HttpRequestBuilder =
    http("[post] File your nil return page")
      .post(cisFrontendUrl + "/monthly-return/file-your-nil-return/800")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))
      .check(headerRegex("Location", "/date-confirm-payments/([^/?]+)").saveAs("flowId"))

  val getConfirmNilReturnDatePage: HttpRequestBuilder =
    http("[get ] Confirm nil return page")
      .get(cisFrontendUrl + "/monthly-return/date-confirm-payments/#{flowId}")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  private def randomValidMonthYear(): (String, String) = {
    val today        = LocalDate.now()
    val taxYearStart = LocalDate.of(today.getYear, 11, 20)

    val startingTaxYear =
      if (today.isBefore(taxYearStart)) today.getYear
      else today.getYear + 1

    val earliest = YearMonth.of(startingTaxYear - 10 + 1, 1)

    val (currentTaxMonth, currentTaxYear) = {
      val year  = today.getYear
      val month = today.getMonthValue
      if (today.getDayOfMonth >= 6) (month, year)
      else if (month == 1) (12, year - 1)
      else (month - 1, year)
    }
    val latest                            = YearMonth.of(currentTaxYear, currentTaxMonth).plusMonths(3)
    val allMonths                         = Iterator.iterate(earliest)(_.plusMonths(1)).takeWhile(!_.isAfter(latest)).toList
    val excluded                          = Set(
      YearMonth.of(2025, 1),
      YearMonth.of(2025, 2),
      YearMonth.of(2025, 3),
      YearMonth.of(2024, 6)
    )
    val validMonths                       = allMonths.filterNot(excluded.contains)
    val randomYM                          = validMonths(Random.nextInt(validMonths.length))
    val monthStyle                        =
      if (Random.nextBoolean()) TextStyle.SHORT
      else TextStyle.FULL
    val monthStr                          = randomYM.getMonth.getDisplayName(monthStyle, Locale.UK)
    val yearStr                           = randomYM.getYear.toString
    (monthStr, yearStr)
  }

  def postConfirmNilReturnDatePage: HttpRequestBuilder = {
    val (month, year) = randomValidMonthYear()
    http("[post] Confirm nil return page")
      .post(cisFrontendUrl + "/monthly-return/date-confirm-payments/#{flowId}")
      .formParam("value.month", month)
      .formParam("value.year", year)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))
  }

  val getDoYouWantToSubmitAnInactivityRequestPage: HttpRequestBuilder =
    http("[get ] Do You Want To Submit An Inactivity Request page")
      .get(cisFrontendUrl + "/monthly-return/submit-inactivity-request/#{flowId}")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoYouWantToSubmitAnInactivityRequestPage(option: String): HttpRequestBuilder =
    http("[post] Do You Want To Submit An Inactivity Request page")
      .post(cisFrontendUrl + "/monthly-return/submit-inactivity-request/#{flowId}")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDoYouWantEmailConfirmation: HttpRequestBuilder =
    http("[get ] Do you want email confirmation page")
      .get(cisFrontendUrl + "/monthly-return/do-you-want-email-confirmation/#{flowId}")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoYouWantEmailConfirmation(option: String): HttpRequestBuilder =
    http("[post] Do you want email confirmation page")
      .post(cisFrontendUrl + "/monthly-return/do-you-want-email-confirmation/#{flowId}")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getConfirmEmailAddressPage: HttpRequestBuilder =
    http("[get ] Confirmation Email Address page")
      .get(cisFrontendUrl + "/monthly-return/enter-email-address/#{flowId}")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postConfirmEmailAddressPage(email: String): HttpRequestBuilder =
    http("[post] Confirmation Email Address page")
      .post(cisFrontendUrl + "/monthly-return/enter-email-address/#{flowId}")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDeclarationPage: HttpRequestBuilder =
    http("[get ] Declaration page")
      .get(cisFrontendUrl + "/monthly-return/declaration-no-payments-made/#{flowId}")
      .check(status.is(200))

  val postDeclarationPage: HttpRequestBuilder =
    http("[post] Declaration page")
      .get(cisFrontendUrl + "/monthly-return/declaration-no-payments-made/continue/#{flowId}")
      .check(status.is(303))

  val getChangeInactivityWarningPage: HttpRequestBuilder =
    http("[get ] Inactivity Warning page")
      .get(cisFrontendUrl + "/monthly-return/change-inactivity-request-warning/#{flowId}")
      .check(status.is(200))

  val postChangeInactivityWarningPage: HttpRequestBuilder =
    http("[post] Inactivity Warning page")
      .post(cisFrontendUrl + "/monthly-return/change-inactivity-request-warning/#{flowId}")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getMNRFCheckYourAnswersPage: HttpRequestBuilder =
    http("[get ] Get Check your answers page")
      .get(cisFrontendUrl + "/monthly-return/check-your-answers/#{flowId}")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postMNRFCheckYourAnswersPage: HttpRequestBuilder =
    http("[post] Post Check your answers")
      .post(cisFrontendUrl + "/monthly-return/check-your-answers/#{flowId}")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeDoYouWantToSubmitAnInactivityRequestPage: HttpRequestBuilder =
    http("[get ] Change Do You Want To Submit An Inactivity Request page")
      .get(cisFrontendUrl + "/monthly-return/change-submit-inactivity-request/#{flowId}")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeDoYouWantToSubmitAnInactivityRequestPage(option: String): HttpRequestBuilder =
    http("[post] Change Do You Want To Submit An Inactivity Request page")
      .post(cisFrontendUrl + "/monthly-return/change-submit-inactivity-request/#{flowId}")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeEnterEmailAddressPage: HttpRequestBuilder =
    http("[get ] Change Confirmation Email Address page")
      .get(cisFrontendUrl + "/monthly-return/change-enter-email-address/#{flowId}")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeEnterEmailAddressPage(email: String): HttpRequestBuilder =
    http("[post] Change Confirmation Email Address page")
      .post(cisFrontendUrl + "/monthly-return/change-enter-email-address/#{flowId}")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val postSubmissionSendPage: HttpRequestBuilder =
    http("[post] Submission Sending page")
      .get(cisFrontendUrl + "/monthly-return/submission-send/#{flowId}")
      .check(status.is(303))

  val getPollingPage: HttpRequestBuilder =
    http("[get] Polling")
      .get(cisFrontendUrl + "/monthly-return/submission-send/polling/#{flowId}")
      .disableFollowRedirect
      .check(status.is(200))

  val postPollingPage: HttpRequestBuilder =
    http("[post] Polling")
      .get(cisFrontendUrl + "/monthly-return/submission-send/polling/#{flowId}")
      .disableFollowRedirect
      .check(status.in(200, 303).saveAs("pollStatus"))

  def pollUntilReady(pollingPage: HttpRequestBuilder): ChainBuilder =
    exec(session => session.set("pollStatus", 0))
      .asLongAs(
        session => session("pollStatus").as[Int] != 303 && session("attempt").as[Int] < 13,
        "attempt"
      ) {
        pause(5.second)
          .exec(pollingPage)
          .doIf(session => session("pollStatus").as[Int] != 303) {
            pause(5.second)
          }
      }
      .exec(session => if (session("pollStatus").as[Int] != 303) session.markAsFailed else session)

  val getSuccessfulSubmissionPage: HttpRequestBuilder =
    http("[get ] Successful Submission page")
      .get(cisFrontendUrl + "/monthly-return/confirmation/#{flowId}")
      .check(status.is(200))
}
