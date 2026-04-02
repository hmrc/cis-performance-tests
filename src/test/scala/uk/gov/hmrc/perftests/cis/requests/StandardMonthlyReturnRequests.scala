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

import java.time.{LocalDate, YearMonth}
import scala.util.Random

object StandardMonthlyReturnRequests extends ServicesConfiguration with CisPerformanceTestBase {

  val getFileYourMonthlyCISReturnPage: HttpRequestBuilder =
    http("[get ] File your monthly CIS return page")
      .get(cisFrontendUrl + "/monthly-return/file-your-monthly-return")
      .check(status.is(200))

  val getWhichTaxMonthAndYearAreYouFilingAReturnForPage: HttpRequestBuilder =
    http("[get ] Which tax month and year are you filing a return for page")
      .get(cisFrontendUrl + "/monthly-return/date-confirm-payments")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  private def randomValidMonthYear(): (String, String) = {
    val earliest                          = YearMonth.of(2007, 5) // May 2007
    val today                             = LocalDate.now()
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
      YearMonth.of(2025, 3)
    )
    val validMonths                       = allMonths.filterNot(excluded.contains)
    val randomYM                          = validMonths(Random.nextInt(validMonths.length))
    val monthStr                          = f"${randomYM.getMonthValue}%02d"
    val yearStr                           = randomYM.getYear.toString
    (monthStr, yearStr)
  }

  def postWhichTaxMonthAndYearAreYouFilingAReturnForPage: HttpRequestBuilder = {
    val (month, year) = randomValidMonthYear()
    http("[post] Which tax month and year are you filing a return for page")
      .post(cisFrontendUrl + "/monthly-return/date-confirm-payments")
      .formParam("value.month", month)
      .formParam("value.year", year)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))
  }

  val getSelectSubcontractorsPage: HttpRequestBuilder =
    http("[get ] Select Subcontractors page")
      .get(cisFrontendUrl + "/monthly-return/select-subcontractors")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postSelectSubcontractorsPage(option: String): HttpRequestBuilder =
    http("[post] Select Subcontractors page")
      .post(cisFrontendUrl + "/monthly-return/select-subcontractors")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getYouHaveUnverifiedSubcontractorsPage: HttpRequestBuilder =
    http("[get ] Unverified Subcontractors page")
      .get(cisFrontendUrl + "/monthly-return/unverified-subcontractors")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postYouHaveUnverifiedSubcontractorsPage(option: String): HttpRequestBuilder =
    http("[post] Unverified Subcontractors page")
      .post(cisFrontendUrl + "/monthly-return/unverified-subcontractors")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getHowMuchDidYouPayToFirstSubcontractorInTotalPage: HttpRequestBuilder =
    http("[get ] How much did you pay to One Subcontractor in total?")
      .get(cisFrontendUrl + "/monthly-return/payment-details/1")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postHowMuchDidYouPayToFirstSubcontractorInTotalPage(amount: String): HttpRequestBuilder =
    http("[post] How much did you pay to One Subcontractor in total?")
      .post(cisFrontendUrl + "/monthly-return/payment-details/1")
      .formParam("value", amount)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getHowMuchDidFirstSubcontractorPayInMaterialCosts: HttpRequestBuilder =
    http("[get ] How much did One Subcontractor pay in material costs?")
      .get(cisFrontendUrl + "/monthly-return/materials-cost/1")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postHowMuchDidFirstSubcontractorPayInMaterialCosts(amount: String): HttpRequestBuilder =
    http("[post] How much did One Subcontractor pay in material costs?")
      .post(cisFrontendUrl + "/monthly-return/materials-cost/1")
      .formParam("value", amount)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getHowMuchTaxInTotalDidYouDeductFromFirstSubcontractor: HttpRequestBuilder =
    http("[get ] How much tax in total did you deduct from One Subcontractor?")
      .get(cisFrontendUrl + "/monthly-return/tax-deducted/1")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postHowMuchTaxInTotalDidYouDeductFromFirstSubcontractor(amount: String): HttpRequestBuilder =
    http("[post] How much tax in total did you deduct from One Subcontractor?")
      .post(cisFrontendUrl + "/monthly-return/tax-deducted/1")
      .formParam("value", amount)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCheckYourAnswersForFirstSubcontractor: HttpRequestBuilder =
    http("[get ] Check your answers for One Subcontractor")
      .get(cisFrontendUrl + "/monthly-return/check-answers-total-payments/1")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postCheckYourAnswersForFirstSubcontractor: HttpRequestBuilder =
    http("[post] Check your answers for One Subcontractor")
      .post(cisFrontendUrl + "/monthly-return/check-answers-total-payments/1")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeHowMuchDidYouPayToFirstSubcontractorInTotalPage: HttpRequestBuilder =
    http("[get ] Change How much did you pay to One Subcontractor in total?")
      .get(cisFrontendUrl + "/monthly-return/change-payment-details/1")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeHowMuchDidYouPayToFirstSubcontractorInTotalPage(amount: String): HttpRequestBuilder =
    http("[post] Change How much did you pay to One Subcontractor in total?")
      .post(cisFrontendUrl + "/monthly-return/change-payment-details/1")
      .formParam("value", amount)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeHowMuchDidFirstSubcontractorPayInMaterialCosts: HttpRequestBuilder =
    http("[get ] Change How much did One Subcontractor pay in material costs?")
      .get(cisFrontendUrl + "/monthly-return/change-materials-cost/1")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeHowMuchDidFirstSubcontractorPayInMaterialCosts(amount: String): HttpRequestBuilder =
    http("[post] Change How much did One Subcontractor pay in material costs?")
      .post(cisFrontendUrl + "/monthly-return/change-materials-cost/1")
      .formParam("value", amount)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeHowMuchTaxInTotalDidYouDeductFromFirstSubcontractor: HttpRequestBuilder =
    http("[get ] Change How much tax in total did you deduct from One Subcontractor?")
      .get(cisFrontendUrl + "/monthly-return/change-tax-deducted/1")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeHowMuchTaxInTotalDidYouDeductFromFirstSubcontractor(amount: String): HttpRequestBuilder =
    http("[post] Change How much tax in total did you deduct from One Subcontractor?")
      .post(cisFrontendUrl + "/monthly-return/change-tax-deducted/1")
      .formParam("value", amount)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

}