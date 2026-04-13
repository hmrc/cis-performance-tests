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

  val getFileYourMonthlyCISReturn: HttpRequestBuilder =
    http("[get ] File your monthly CIS return page")
      .get(cisFrontendUrl + "/monthly-return/file-your-monthly-return")
      .check(status.is(200))

  val getWhichTaxMonthAndYearAreYouFilingAReturnFor: HttpRequestBuilder =
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

  def postWhichTaxMonthAndYearAreYouFilingAReturnFor: HttpRequestBuilder = {
    val (month, year) = randomValidMonthYear()
    http("[post] Which tax month and year are you filing a return for page")
      .post(cisFrontendUrl + "/monthly-return/date-confirm-payments")
      .formParam("value.month", month)
      .formParam("value.year", year)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))
  }

  val getSelectSubcontractors: HttpRequestBuilder =
    http("[get ] Select Subcontractors page")
      .get(cisFrontendUrl + "/monthly-return/select-subcontractors")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val getSelectAllSelectSubcontractors: HttpRequestBuilder =
    http("[get ] Select Subcontractors page")
      .get(cisFrontendUrl + "/monthly-return/select-subcontractors")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val getDeselectAllSelectSubcontractors: HttpRequestBuilder =
    http("[get ] Select Subcontractors page")
      .get(cisFrontendUrl + "/monthly-return/select-subcontractors")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postSelectSubcontractors(options: String*): HttpRequestBuilder =
    http("[post] Select Subcontractors page")
      .post(cisFrontendUrl + "/monthly-return/select-subcontractors")
      .formParamSeq(options.zipWithIndex.map { case (value, index) =>
        s"subcontractorsToInclude.$index" -> value
      } :+ ("csrfToken" -> f"#{csrfToken}"))
      .check(status.is(303))

  val getYouHaveUnverifiedSubcontractors: HttpRequestBuilder =
    http("[get ] Unverified Subcontractors page")
      .get(cisFrontendUrl + "/monthly-return/unverified-subcontractors")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postYouHaveUnverifiedSubcontractors(option: String): HttpRequestBuilder =
    http("[post] Unverified Subcontractors page")
      .post(cisFrontendUrl + "/monthly-return/unverified-subcontractors")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getHowMuchDidYouPayToFirstSubcontractorInTotal: HttpRequestBuilder =
    http("[get ] How much did you pay to One Subcontractor in total?")
      .get(cisFrontendUrl + "/monthly-return/payment-details/1")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postHowMuchDidYouPayToFirstSubcontractorInTotal(amount: String): HttpRequestBuilder =
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

  val getChangeHowMuchDidYouPayToFirstSubcontractorInTotal: HttpRequestBuilder =
    http("[get ] Change How much did you pay to One Subcontractor in total?")
      .get(cisFrontendUrl + "/monthly-return/change-payment-details/1")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeHowMuchDidYouPayToFirstSubcontractorInTotal(amount: String): HttpRequestBuilder =
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

  val getYouHaveAddedDetailsForASingleSubcontractor: HttpRequestBuilder =
    http("[get ] You have added details for a single subcontractor page")
      .get(cisFrontendUrl + "/monthly-return/subcontractor-details-added")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postYouHaveAddedDetailsForASingleSubcontractor(option: String): HttpRequestBuilder =
    http("[post] You have added details for a single subcontractor page")
      .post(cisFrontendUrl + "/monthly-return/subcontractor-details-added")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeFirstSubcontractorsDetails: HttpRequestBuilder =
    http("[get ] Change One Subcontractor’s details page")
      .get(cisFrontendUrl + "/monthly-return/change-subcontractor/1")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postChangeFirstSubcontractorsDetails: HttpRequestBuilder =
    http("[post] Change One Subcontractor’s details page")
      .post(cisFrontendUrl + "/monthly-return/check-answers-total-payments/1")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeHowMuchDidYouPayToFirstSubcontractorInTotalDetails: HttpRequestBuilder =
    http("[get ] Change How much did you pay to One Subcontractor in total?")
      .get(cisFrontendUrl + "/monthly-return/change-payment-details/1")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeHowMuchDidYouPayToFirstSubcontractorInTotalDetails(amount: String): HttpRequestBuilder =
    http("[post] Change How much did you pay to One Subcontractor in total?")
      .post(cisFrontendUrl + "/monthly-return/change-payment-details/1")
      .formParam("value", amount)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeHowMuchDidFirstSubcontractorPayInMaterialCostsDetails: HttpRequestBuilder =
    http("[get ] Change How much did One Subcontractor pay in material costs?")
      .get(cisFrontendUrl + "/monthly-return/change-materials-cost/1")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeHowMuchDidFirstSubcontractorPayInMaterialCostsDetails(amount: String): HttpRequestBuilder =
    http("[post] Change How much did One Subcontractor pay in material costs?")
      .post(cisFrontendUrl + "/monthly-return/change-materials-cost/1")
      .formParam("value", amount)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeHowMuchTaxInTotalDidYouDeductFromFirstSubcontractorDetails: HttpRequestBuilder =
    http("[get ] Change How much tax in total did you deduct from One Subcontractor?")
      .get(cisFrontendUrl + "/monthly-return/change-tax-deducted/1")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeHowMuchTaxInTotalDidYouDeductFromFirstSubcontractorDetails(amount: String): HttpRequestBuilder =
    http("[post] Change How much tax in total did you deduct from One Subcontractor?")
      .post(cisFrontendUrl + "/monthly-return/change-tax-deducted/1")
      .formParam("value", amount)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getWhichSubcontractorDoYouNeedToAddPaymentDetailsFor: HttpRequestBuilder =
    http("[get ] Which subcontractor do you need to add payment details for page")
      .get(cisFrontendUrl + "/monthly-return/add-subcontractor-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhichSubcontractorDoYouNeedToAddPaymentDetailsFor(option: String): HttpRequestBuilder =
    http("[post] Which subcontractor do you need to add payment details for page")
      .post(cisFrontendUrl + "/monthly-return/add-subcontractor-details")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getHowMuchDidYouPayToSecondSubcontractorInTotal: HttpRequestBuilder =
    http("[get ] How much did you pay to Two Subcontractor in total?")
      .get(cisFrontendUrl + "/monthly-return/payment-details/2")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postHowMuchDidYouPayToSecondSubcontractorInTotal(amount: String): HttpRequestBuilder =
    http("[post] How much did you pay to Two Subcontractor in total?")
      .post(cisFrontendUrl + "/monthly-return/payment-details/2")
      .formParam("value", amount)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getHowMuchDidSecondSubcontractorPayInMaterialCosts: HttpRequestBuilder =
    http("[get ] How much did Two Subcontractor pay in material costs?")
      .get(cisFrontendUrl + "/monthly-return/materials-cost/2")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postHowMuchDidSecondSubcontractorPayInMaterialCosts(amount: String): HttpRequestBuilder =
    http("[post] How much did Two Subcontractor pay in material costs?")
      .post(cisFrontendUrl + "/monthly-return/materials-cost/2")
      .formParam("value", amount)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getHowMuchTaxInTotalDidYouDeductFromSecondSubcontractor: HttpRequestBuilder =
    http("[get ] How much tax in total did you deduct from Two Subcontractor?")
      .get(cisFrontendUrl + "/monthly-return/tax-deducted/2")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postHowMuchTaxInTotalDidYouDeductFromSecondSubcontractor(amount: String): HttpRequestBuilder =
    http("[post] How much tax in total did you deduct from Two Subcontractor?")
      .post(cisFrontendUrl + "/monthly-return/tax-deducted/2")
      .formParam("value", amount)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCheckYourAnswersForSecondSubcontractor: HttpRequestBuilder =
    http("[get ] Check your answers for Two Subcontractor")
      .get(cisFrontendUrl + "/monthly-return/check-answers-total-payments/2")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postCheckYourAnswersForSecondSubcontractor: HttpRequestBuilder =
    http("[post] Check your answers for Two Subcontractor")
      .post(cisFrontendUrl + "/monthly-return/check-answers-total-payments/2")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getYouHaveAddedDetailsFor2Subcontractors: HttpRequestBuilder =
    http("[get ] You have added details for 2 subcontractors page")
      .get(cisFrontendUrl + "/monthly-return/subcontractor-details-added")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val getAreYouSureYouWantToRemoveFirstSubcontractor: HttpRequestBuilder =
    http("[get ] Are you sure you want to remove One Subcontractor page")
      .get(cisFrontendUrl + "/monthly-return/change-remove-subcontractor/1")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAreYouSureYouWantToRemoveFirstSubcontractor(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to remove One Subcontractor page")
      .post(cisFrontendUrl + "/monthly-return/change-remove-subcontractor/1")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getSummaryOfPaymentsMade: HttpRequestBuilder =
    http("[get ] Summary of payments made page")
      .get(cisFrontendUrl + "/monthly-return/summary-subcontractor-payments")
      .check(status.is(200))

  val getDoYouConfirmTheInformationInThisReturnIsCorrect: HttpRequestBuilder =
    http("[get ] Do you confirm the information in this return is correct page")
      .get(cisFrontendUrl + "/monthly-return/payment-details-confirmation")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoYouConfirmTheInformationInThisReturnIsCorrect(option: String): HttpRequestBuilder =
    http("[post] Do you confirm the information in this return is correct page")
      .post(cisFrontendUrl + "/monthly-return/payment-details-confirmation")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDeclarationOfEmploymentStatus: HttpRequestBuilder =
    http("[get ] Declaration of employment status page")
      .get(cisFrontendUrl + "/monthly-return/employment-status-declaration")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDeclarationOfEmploymentStatus(option: String): HttpRequestBuilder =
    http("[post] Declaration of employment status page")
      .post(cisFrontendUrl + "/monthly-return/employment-status-declaration")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDeclarationOfVerifiedStatus: HttpRequestBuilder =
    http("[get ] Declaration of verified status page")
      .get(cisFrontendUrl + "/monthly-return/verified-status-declaration")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDeclarationOfVerifiedStatus(option: String): HttpRequestBuilder =
    http("[post] Declaration of verified status page")
      .post(cisFrontendUrl + "/monthly-return/verified-status-declaration")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getSubmitInactivityRequest: HttpRequestBuilder =
    http("[get ] Submit inactivity request page")
      .get(cisFrontendUrl + "/monthly-return/submit-inactivity-request")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postSubmitInactivityRequest(option: String): HttpRequestBuilder =
    http("[post] Submit inactivity request page")
      .post(cisFrontendUrl + "/monthly-return/submit-inactivity-request")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDoYouWantConfirmationByEmailThatThisReturnHasBeenSuccessfullySubmitted: HttpRequestBuilder =
    http("[get ] Do you want confirmation by email that this return has been successfully submitted page")
      .get(cisFrontendUrl + "/monthly-return/do-you-want-email-confirmation")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoYouWantConfirmationByEmailThatThisReturnHasBeenSuccessfullySubmitted(option: String): HttpRequestBuilder =
    http("[post] Do you want confirmation by email that this return has been successfully submitted page")
      .post(cisFrontendUrl + "/monthly-return/do-you-want-email-confirmation")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCheckYourAnswersBeforeSubmittingYourReturn: HttpRequestBuilder =
    http("[get ] Check your answers before submitting your return page")
      .get(cisFrontendUrl + "/monthly-return/check-your-answers")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postCheckYourAnswersBeforeSubmittingYourReturn: HttpRequestBuilder =
    http("[post] Check your answers before submitting your return page")
      .post(cisFrontendUrl + "/monthly-return/check-your-answers")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeDeclarationOfEmploymentStatus: HttpRequestBuilder =
    http("[get ] Change declaration of employment status page")
      .get(cisFrontendUrl + "/monthly-return/change-employment-status-declaration")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeDeclarationOfEmploymentStatus(option: String): HttpRequestBuilder =
    http("[post] Change declaration of employment status page")
      .post(cisFrontendUrl + "/monthly-return/change-employment-status-declaration")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeDeclarationOfVerifiedStatus: HttpRequestBuilder =
    http("[get ] Change declaration of verified status page)")
      .get(cisFrontendUrl + "/monthly-return/change-verified-status-declaration")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeDeclarationOfVerifiedStatus(option: String): HttpRequestBuilder =
    http("[post] Change declaration of verified status page")
      .post(cisFrontendUrl + "/monthly-return/change-verified-status-declaration")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeSubmitInactivityRequest: HttpRequestBuilder =
    http("[get ] Change submit inactivity request page")
      .get(cisFrontendUrl + "/monthly-return/change-submit-inactivity-request")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeSubmitInactivityRequest(option: String): HttpRequestBuilder =
    http("[post] Change submit inactivity request page")
      .post(cisFrontendUrl + "/monthly-return/change-submit-inactivity-request")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getInactivityRequest: HttpRequestBuilder =
    http("[get ] Inactivity request page")
      .get(cisFrontendUrl + "/monthly-return/change-inactivity-request-warning")
      .check(status.is(200))

  val getChangeDoYouWantConfirmationByEmailThatThisReturnHasBeenSuccessfullySubmitted: HttpRequestBuilder =
    http("[get ] Change do you want confirmation by email that this return has been successfully submitted page")
      .get(cisFrontendUrl + "/monthly-return/change-do-you-want-email-confirmation")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeDoYouWantConfirmationByEmailThatThisReturnHasBeenSuccessfullySubmitted(
    option: String
  ): HttpRequestBuilder =
    http("[post] Change do you want confirmation by email that this return has been successfully submitted page")
      .post(cisFrontendUrl + "/monthly-return/change-do-you-want-email-confirmation")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterYourEmailAddress: HttpRequestBuilder =
    http("[get ] Enter your email address page")
      .get(cisFrontendUrl + "/monthly-return/change-enter-email-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterYourEmailAddress(email: String): HttpRequestBuilder =
    http("[post] Enter your email address page")
      .post(cisFrontendUrl + "/monthly-return/enter-email-address")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))
}
