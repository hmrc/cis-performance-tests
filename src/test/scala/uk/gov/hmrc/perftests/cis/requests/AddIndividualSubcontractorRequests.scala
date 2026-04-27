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

object AddIndividualSubcontractorRequests extends ServicesConfiguration with CisPerformanceTestBase {

  val getAddSubcontractor: HttpRequestBuilder =
    http("[get ] Contractor Frontend")
      .get(cisContractorFrontendUrl)
      .check(status.is(303))

  val getWhatTypeOfSubcontractorAreYouAdding: HttpRequestBuilder =
    http("[get ] What Type Of Subcontractor Are You Adding page")
      .get(cisContractorFrontendUrl + "/add/type-of-subcontractor")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatTypeOfSubcontractorAreYouAdding(option: String): HttpRequestBuilder =
    http("[post] What Type of Subcontractor Are You Adding page")
      .post(cisContractorFrontendUrl + "/add/type-of-subcontractor")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddTradingName: HttpRequestBuilder =
    http("[get ] Does The Individual Use A Trading Name page")
      .get(cisContractorFrontendUrl + "/add/check-trading-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddTradingName(option: String): HttpRequestBuilder =
    http("[post] Does The Individual Use A Trading Name page")
      .post(cisContractorFrontendUrl + "/add/check-trading-name")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getTradingName: HttpRequestBuilder =
    http("[get ] What Is The Individual's Trading Name page")
      .get(cisContractorFrontendUrl + "/add/trading-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postTradingName(name: String): HttpRequestBuilder =
    http("[post] What Is The Individual's Trading Name page")
      .post(cisContractorFrontendUrl + "/add/trading-name")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddIndividualAddressPage: HttpRequestBuilder =
    http("[get ] Do You Want To Add The Individual's Address page")
      .get(cisContractorFrontendUrl + "/add/check-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddIndividualAddressPage(option: String): HttpRequestBuilder =
    http("[post] Do You Want To Add The Individual's Address page")
      .post(cisContractorFrontendUrl + "/add/check-address")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getIndividualAddressPage: HttpRequestBuilder =
    http("[get ] What Is The Individual's Address page")
      .get(cisContractorFrontendUrl + "/add/address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postIndividualAddressPage(
    line1: String,
    line2: String,
    town: String,
    county: String,
    postcode: String,
    country: String
  ): HttpRequestBuilder =
    http("[post] What Is The Individual's Address page")
      .post(cisContractorFrontendUrl + "/add/address")
      .formParam("addressLine1", line1)
      .formParam("addressLine2", line2)
      .formParam("addressLine3", town)
      .formParam("addressLine4", county)
      .formParam("postalCode", postcode)
      .formParam("country", country)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddIndividualNinoPage: HttpRequestBuilder =
    http("[get ] Do You Have A National Insurance Number page")
      .get(cisContractorFrontendUrl + "/add/check-national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddIndividualNinoPage(option: String): HttpRequestBuilder =
    http("[post] Do You Have A National Insurance page")
      .post(cisContractorFrontendUrl + "/add/check-national-insurance-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getIndividualNinoPage: HttpRequestBuilder =
    http("[get ] What Is The Individual's National Insurance Number page")
      .get(cisContractorFrontendUrl + "/add/national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postIndividualNinoPage(name: String): HttpRequestBuilder =
    http("[post] What Is The Individual's National Insurance Number page")
      .post(cisContractorFrontendUrl + "/add/national-insurance-number")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddIndividualUtrPage: HttpRequestBuilder =
    http("[get ] Do You Have A Unique Taxpayer Reference page")
      .get(cisContractorFrontendUrl + "/add/check-unique-taxpayer-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddIndividualUtrPage(option: String): HttpRequestBuilder =
    http("[post] Do You Have A Unique Taxpayer Reference page")
      .post(cisContractorFrontendUrl + "/add/check-unique-taxpayer-reference")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getIndividualUtrPage: HttpRequestBuilder =
    http("[get ] What Is The Individual's Unique Taxpayer Reference page")
      .get(cisContractorFrontendUrl + "/add/unique-taxpayer-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postIndividualUtrPage(utr: String): HttpRequestBuilder =
    http("[post] What Is The Individual's Unique Taxpayer Reference page")
      .post(cisContractorFrontendUrl + "/add/unique-taxpayer-reference")
      .formParam("value", utr)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddIndividualWrnPage: HttpRequestBuilder =
    http("[get ] Do You Have A Works Reference Number page")
      .get(cisContractorFrontendUrl + "/add/check-works-reference-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddIndividualWrnPage(option: String): HttpRequestBuilder =
    http("[post] Do You Have A Works Reference Number page")
      .post(cisContractorFrontendUrl + "/add/check-works-reference-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getIndividualWrnPage: HttpRequestBuilder =
    http("[get ] What Is The Works Reference Number page")
      .get(cisContractorFrontendUrl + "/add/works-reference-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postIndividualWrnPage(name: String): HttpRequestBuilder =
    http("[post] What Is The Works Reference Number page")
      .post(cisContractorFrontendUrl + "/add/works-reference-number")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getIndividualContactMethodPage: HttpRequestBuilder =
    http("[get ] What is the contact method for this individual page")
      .get(cisContractorFrontendUrl + "/add/check-contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postIndividualContactMethodPage(contactOption: String): HttpRequestBuilder =
    http("[post] What is the contact method for this individual page")
      .post(cisContractorFrontendUrl + "/add/check-contact-details")
      .formParam("value", contactOption)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getIndividualEmailAddressPage: HttpRequestBuilder =
    http("[get ] What is the individual email address page")
      .get(cisContractorFrontendUrl + "/add/email-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postIndividualEmailAddressPage(email: String): HttpRequestBuilder =
    http("[post] What is the individual email address page")
      .post(cisContractorFrontendUrl + "/add/email-address")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getIndividualPhoneNumberPage: HttpRequestBuilder =
    http("[get ] What is the individual phone number page")
      .get(cisContractorFrontendUrl + "/add/phone-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postIndividualPhoneNumberPage(phoneNumber: String): HttpRequestBuilder =
    http("[post] What is the individual phone number page")
      .post(cisContractorFrontendUrl + "/add/phone-number")
      .formParam("value", phoneNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getIndividualMobileNumberPage: HttpRequestBuilder =
    http("[get ] What is the individual mobile number page")
      .get(cisContractorFrontendUrl + "/add/mobile-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postIndividualMobileNumberPage(mobileNumber: String): HttpRequestBuilder =
    http("[post] What is the individual mobile number page")
      .post(cisContractorFrontendUrl + "/add/mobile-number")
      .formParam("value", mobileNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getIndividualCheckYourAnswersPage: HttpRequestBuilder =
    http("[get ] Check your answers before adding this individual page")
      .get(cisContractorFrontendUrl + "/add/check-your-answers")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postIndividualCheckYourAnswersPage: HttpRequestBuilder =
    http("[post] Check your answers before adding this individual page")
      .post(cisContractorFrontendUrl + "/add/check-your-answers")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeTradingNamePage: HttpRequestBuilder =
    http("[get ] Change What Is The Individual's Trading Name page")
      .get(cisContractorFrontendUrl + "/add/change-trading-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeTradingNamePage(name: String): HttpRequestBuilder =
    http("[post] Change What Is The Individual's Trading Name page")
      .post(cisContractorFrontendUrl + "/add/change-trading-name")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeAddTradingNamePage: HttpRequestBuilder =
    http("[get ] Change Does The Individual Use A Trading Name page")
      .get(cisContractorFrontendUrl + "/add/change-check-trading-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeAddTradingNamePage(option: String): HttpRequestBuilder =
    http("[post] Change Does The Individual Use A Trading Name page")
      .post(cisContractorFrontendUrl + "/add/change-check-trading-name")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeIndividualNamePage: HttpRequestBuilder =
    http("[get ] Change What Is The Individual's Name page")
      .get(cisContractorFrontendUrl + "/add/change-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeIndividualNamePage(first: String, middle: String, last: String): HttpRequestBuilder =
    http("[post] Change What Is The Individual's Name page")
      .post(cisContractorFrontendUrl + "/add/change-name")
      .formParam("firstName", first)
      .formParam("middleName", middle)
      .formParam("lastName", last)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeIndividualAddressPage: HttpRequestBuilder =
    http("[get ] Change What Is The Individual's Address page")
      .get(cisContractorFrontendUrl + "/add/change-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeIndividualAddressPage(
    line1: String,
    line2: String,
    town: String,
    county: String,
    postcode: String,
    country: String
  ): HttpRequestBuilder =
    http("[post] Change What Is The Individual's Address page")
      .post(cisContractorFrontendUrl + "/add/change-address")
      .formParam("addressLine1", line1)
      .formParam("addressLine2", line2)
      .formParam("addressLine3", town)
      .formParam("addressLine4", county)
      .formParam("postalCode", postcode)
      .formParam("country", country)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeAddIndividualAddressPage: HttpRequestBuilder =
    http("[get ] Change Do You Want To Add The Individual's Address page")
      .get(cisContractorFrontendUrl + "/add/change-check-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeAddIndividualAddressPage(option: String): HttpRequestBuilder =
    http("[post] Change Do You Want To Add The Individual's Address page")
      .post(cisContractorFrontendUrl + "/add/change-check-address")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeIndividualContactMethodPage: HttpRequestBuilder =
    http("[get ] What is the contact method for this individual page")
      .get(cisContractorFrontendUrl + "/add/change-check-contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeIndividualContactMethodPage(contactOption: String): HttpRequestBuilder =
    http("[post] What is the contact method for this individual page")
      .post(cisContractorFrontendUrl + "/add/change-check-contact-details")
      .formParam("value", contactOption)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeIndividualEmailAddressPage: HttpRequestBuilder =
    http("[get ] What is the individual email address page")
      .get(cisContractorFrontendUrl + "/add/change-email-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeIndividualEmailAddressPage(email: String): HttpRequestBuilder =
    http("[post] What is the individual email address page")
      .post(cisContractorFrontendUrl + "/add/change-email-address")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeIndividualPhoneNumberPage: HttpRequestBuilder =
    http("[get ] What is the individual phone number page")
      .get(cisContractorFrontendUrl + "/add/change-phone-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeIndividualPhoneNumberPage(phoneNumber: String): HttpRequestBuilder =
    http("[post] What is the individual phone number page")
      .post(cisContractorFrontendUrl + "/add/change-phone-number")
      .formParam("value", phoneNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeIndividualMobileNumberPage: HttpRequestBuilder =
    http("[get ] What is the individual mobile number page")
      .get(cisContractorFrontendUrl + "/add/change-mobile-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeIndividualMobileNumberPage(mobileNumber: String): HttpRequestBuilder =
    http("[post] What is the individual mobile number page")
      .post(cisContractorFrontendUrl + "/add/change-mobile-number")
      .formParam("value", mobileNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeIndividualNinoPage: HttpRequestBuilder =
    http("[get ] Change What Is The Individual's National Insurance Number page")
      .get(cisContractorFrontendUrl + "/add/change-national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeIndividualNinoPage(name: String): HttpRequestBuilder =
    http("[post] Change What Is The Individual's National Insurance Number page")
      .post(cisContractorFrontendUrl + "/add/change-national-insurance-number")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeAddIndividualNinoPage: HttpRequestBuilder =
    http("[get ] Change Do You Have A National Insurance Number page")
      .get(cisContractorFrontendUrl + "/add/change-check-national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeAddIndividualNinoPage(option: String): HttpRequestBuilder =
    http("[post] Change Do You Have A National Insurance page")
      .post(cisContractorFrontendUrl + "/add/change-check-national-insurance-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeIndividualUtrPage: HttpRequestBuilder =
    http("[get ] Change What Is The Individual's Unique Taxpayer Reference page")
      .get(cisContractorFrontendUrl + "/add/change-unique-taxpayer-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeIndividualUtrPage(name: String): HttpRequestBuilder =
    http("[post] Change What Is The Individual's Unique Taxpayer Reference page")
      .post(cisContractorFrontendUrl + "/add/change-unique-taxpayer-reference")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeAddIndividualUtrPage: HttpRequestBuilder =
    http("[get ] Change Do You Have A Unique Taxpayer Reference page")
      .get(cisContractorFrontendUrl + "/add/change-check-unique-taxpayer-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeAddIndividualUtrPage(option: String): HttpRequestBuilder =
    http("[post] Change Do You Have A Unique Taxpayer Reference page")
      .post(cisContractorFrontendUrl + "/add/change-check-unique-taxpayer-reference")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeIndividualWrnPage: HttpRequestBuilder =
    http("[get ] Change What Is The Works Reference Number page")
      .get(cisContractorFrontendUrl + "/add/change-works-reference-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeIndividualWrnPage(name: String): HttpRequestBuilder =
    http("[post] Change What Is The Works Reference Number page")
      .post(cisContractorFrontendUrl + "/add/change-works-reference-number")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeAddIndividualWrnPage: HttpRequestBuilder =
    http("[get ] Change Do You Have A Works Reference Number page")
      .get(cisContractorFrontendUrl + "/add/change-check-works-reference-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeAddIndividualWrnPage(option: String): HttpRequestBuilder =
    http("[post] Change Do You Have A Works Reference Number page")
      .post(cisContractorFrontendUrl + "/add/change-check-works-reference-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getIndividualSubcontractorAddedPage: HttpRequestBuilder =
    http("[get ] Individual subcontractor Added")
      .get(cisContractorFrontendUrl + "/add/individual/subcontractor-added")
      .check(status.is(200))
}
