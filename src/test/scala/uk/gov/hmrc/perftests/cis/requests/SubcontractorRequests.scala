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

object SubcontractorRequests extends ServicesConfiguration with CisPerformanceTestBase {

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

  val getDoesTheSubcontractorUseATradingName: HttpRequestBuilder =
    http("[get ] Does The Subcontractor Use A Trading Name page")
      .get(cisContractorFrontendUrl + "/add/check-trading-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoesTheSubcontractorUseATradingName(option: String): HttpRequestBuilder =
    http("[post] Does The Subcontractor Use A Trading Name page")
      .post(cisContractorFrontendUrl + "/add/check-trading-name")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getWhatIsTheSubcontractorsTradingName: HttpRequestBuilder =
    http("[get ] What Is The Subcontractor's Trading Name page")
      .get(cisContractorFrontendUrl + "/add/trading-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatIsTheSubcontractorsTradingName(name: String): HttpRequestBuilder =
    http("[post] What Is The Subcontractor's Trading Name page")
      .post(cisContractorFrontendUrl + "/add/trading-name")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDoYouWantToAddTheSubcontractorsAddress: HttpRequestBuilder =
    http("[get ] Do You Want To Add The Subcontractor's Address page")
      .get(cisContractorFrontendUrl + "/add/check-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoYouWantToAddTheSubcontractorsAddress(option: String): HttpRequestBuilder =
    http("[post] Do You Want To Add The Subcontractor's Address page")
      .post(cisContractorFrontendUrl + "/add/check-address")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getWhatIsTheSubcontractorsAddress: HttpRequestBuilder =
    http("[get ] What Is The Subcontractor's Address page")
      .get(cisContractorFrontendUrl + "/add/address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatIsTheSubcontractorsAddress(
    line1: String,
    line2: String,
    town: String,
    country: String,
    postcode: String
  ): HttpRequestBuilder =
    http("[post] What Is The Subcontractor's Address page")
      .post(cisContractorFrontendUrl + "/add/address")
      .formParam("addressLine1", line1)
      .formParam("addressLine2", line2)
      .formParam("addressLine3", town)
      .formParam("addressLine4", country)
      .formParam("postCode", postcode)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDoYouHaveANationalInsuranceNumber: HttpRequestBuilder =
    http("[get ] Do You Have A National Insurance Number page")
      .get(cisContractorFrontendUrl + "/add/check-national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoYouHaveANationalInsuranceNumber(option: String): HttpRequestBuilder =
    http("[post] Do You Have A National Insurance page")
      .post(cisContractorFrontendUrl + "/add/check-national-insurance-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getWhatIsTheSubcontractorsNationalInsuranceNumber: HttpRequestBuilder =
    http("[get ] What Is The Subcontractor's National Insurance Number page")
      .get(cisContractorFrontendUrl + "/add/national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatIsTheSubcontractorsNationalInsuranceNumber(name: String): HttpRequestBuilder =
    http("[post] What Is The Subcontractor's National Insurance Number page")
      .post(cisContractorFrontendUrl + "/add/national-insurance-number")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDoYouHaveAUniqueTaxpayerReference: HttpRequestBuilder =
    http("[get ] Do You Have A Unique Taxpayer Reference page")
      .get(cisContractorFrontendUrl + "/add/check-unique-taxpayer-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoYouHaveAUniqueTaxpayerReference(option: String): HttpRequestBuilder =
    http("[post] Do You Have A Unique Taxpayer Reference page")
      .post(cisContractorFrontendUrl + "/add/check-unique-taxpayer-reference")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getWhatIsTheSubcontractorsUniqueTaxpayerReference: HttpRequestBuilder =
    http("[get ] What Is The Subcontractor's Unique Taxpayer Reference page")
      .get(cisContractorFrontendUrl + "/add/unique-taxpayer-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatIsTheSubcontractorsUniqueTaxpayerReference(name: String): HttpRequestBuilder =
    http("[post] What Is The Subcontractor's Unique Taxpayer Reference page")
      .post(cisContractorFrontendUrl + "/add/unique-taxpayer-reference")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDoYouHaveAWorksReferenceNumber: HttpRequestBuilder =
    http("[get ] Do You Have A Works Reference Number page")
      .get(cisContractorFrontendUrl + "/add/check-works-reference-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoYouHaveAWorksReferenceNumber(option: String): HttpRequestBuilder =
    http("[post] Do You Have A Works Reference Number page")
      .post(cisContractorFrontendUrl + "/add/check-works-reference-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getWhatIsTheWorksReferenceNumber: HttpRequestBuilder =
    http("[get ] What Is The Works Reference Number page")
      .get(cisContractorFrontendUrl + "/add/works-reference-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatIsTheWorksReferenceNumber(name: String): HttpRequestBuilder =
    http("[post] What Is The Works Reference Number page")
      .post(cisContractorFrontendUrl + "/add/works-reference-number")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDoYouWantToAddTheSubcontractorsContactDetails: HttpRequestBuilder =
    http("[get ] Do You Want To Add The Subcontractor's contact details page")
      .get(cisContractorFrontendUrl + "/add/check-contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoYouWantToAddTheSubcontractorsContactDetails(option: String): HttpRequestBuilder =
    http("[post] Do You Want To Add The Subcontractor's Contact Details page")
      .post(cisContractorFrontendUrl + "/add/check-contact-details")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getSubcontractorsContactDetails: HttpRequestBuilder =
    http("[get ] Subcontractor's Contact Details page")
      .get(cisContractorFrontendUrl + "/add/contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postSubcontractorsContactDetails(email: String, telephone: String): HttpRequestBuilder =
    http("[post] Subcontractor's Contact Details page")
      .post(cisContractorFrontendUrl + "/add/contact-details")
      .formParam("email", email)
      .formParam("telephone", telephone)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getSubcontractorCheckYourAnswersPage: HttpRequestBuilder =
    http("[get ] Get Check your answers page")
      .get(cisContractorFrontendUrl + "/add/check-your-answers")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postSubcontractorCheckYourAnswersPage: HttpRequestBuilder =
    http("[post] Post Check your answers")
      .post(cisContractorFrontendUrl + "/add/check-your-answers")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeWhatTypeOfSubcontractorAreYouAdding: HttpRequestBuilder =
    http("[get ] Change What Type Of Subcontractor Are You Adding page")
      .get(cisContractorFrontendUrl + "/add/change-type-of-subcontractor")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeWhatTypeOfSubcontractorAreYouAdding(option: String): HttpRequestBuilder =
    http("[post] Change What Type of Subcontractor Are You Adding page")
      .post(cisContractorFrontendUrl + "/add/change-type-of-subcontractor")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeWhatIsTheSubcontractorsTradingName: HttpRequestBuilder =
    http("[get ] Change What Is The Subcontractor's Trading Name page")
      .get(cisContractorFrontendUrl + "/add/change-trading-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeWhatIsTheSubcontractorsTradingName(name: String): HttpRequestBuilder =
    http("[post] Change What Is The Subcontractor's Trading Name page")
      .post(cisContractorFrontendUrl + "/add/change-trading-name")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeDoesTheSubcontractorUseATradingName: HttpRequestBuilder =
    http("[get ] Change Does The Subcontractor Use A Trading Name page")
      .get(cisContractorFrontendUrl + "/add/change-check-trading-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeDoesTheSubcontractorUseATradingName(option: String): HttpRequestBuilder =
    http("[post] Change Does The Subcontractor Use A Trading Name page")
      .post(cisContractorFrontendUrl + "/add/change-check-trading-name")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeWhatIsTheSubcontractorsName: HttpRequestBuilder =
    http("[get ] Change What Is The Subcontractor's Name page")
      .get(cisContractorFrontendUrl + "/add/change-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeWhatIsTheSubcontractorsName(first: String, middle: String, last: String): HttpRequestBuilder =
    http("[post] Change What Is The Subcontractor's Name page")
      .post(cisContractorFrontendUrl + "/add/change-name")
      .formParam("firstName", first)
      .formParam("middleName", middle)
      .formParam("lastName", last)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeWhatIsTheSubcontractorsAddress: HttpRequestBuilder =
    http("[get ] Change What Is The Subcontractor's Address page")
      .get(cisContractorFrontendUrl + "/add/change-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeWhatIsTheSubcontractorsAddress(
    line1: String,
    line2: String,
    town: String,
    country: String,
    postcode: String
  ): HttpRequestBuilder =
    http("[post] Change What Is The Subcontractor's Address page")
      .post(cisContractorFrontendUrl + "/add/change-address")
      .formParam("addressLine1", line1)
      .formParam("addressLine2", line2)
      .formParam("addressLine3", town)
      .formParam("addressLine4", country)
      .formParam("postCode", postcode)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeDoYouWantToAddTheSubcontractorsAddress: HttpRequestBuilder =
    http("[get ] Change Do You Want To Add The Subcontractor's Address page")
      .get(cisContractorFrontendUrl + "/add/change-check-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeDoYouWantToAddTheSubcontractorsAddress(option: String): HttpRequestBuilder =
    http("[post] Change Do You Want To Add The Subcontractor's Address page")
      .post(cisContractorFrontendUrl + "/add/change-check-address")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeWhatIsTheSubcontractorsNationalInsuranceNumber: HttpRequestBuilder =
    http("[get ] Change What Is The Subcontractor's National Insurance Number page")
      .get(cisContractorFrontendUrl + "/add/change-national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeWhatIsTheSubcontractorsNationalInsuranceNumber(name: String): HttpRequestBuilder =
    http("[post] Change What Is The Subcontractor's National Insurance Number page")
      .post(cisContractorFrontendUrl + "/add/change-national-insurance-number")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeDoYouHaveANationalInsuranceNumber: HttpRequestBuilder =
    http("[get ] Change Do You Have A National Insurance Number page")
      .get(cisContractorFrontendUrl + "/add/change-check-national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeDoYouHaveANationalInsuranceNumber(option: String): HttpRequestBuilder =
    http("[post] Change Do You Have A National Insurance page")
      .post(cisContractorFrontendUrl + "/add/change-check-national-insurance-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeWhatIsTheSubcontractorsUniqueTaxpayerReference: HttpRequestBuilder =
    http("[get ] Change What Is The Subcontractor's Unique Taxpayer Reference page")
      .get(cisContractorFrontendUrl + "/add/change-unique-taxpayer-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeWhatIsTheSubcontractorsUniqueTaxpayerReference(name: String): HttpRequestBuilder =
    http("[post] Change What Is The Subcontractor's Unique Taxpayer Reference page")
      .post(cisContractorFrontendUrl + "/add/change-unique-taxpayer-reference")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeDoYouHaveAUniqueTaxpayerReference: HttpRequestBuilder =
    http("[get ] Change Do You Have A Unique Taxpayer Reference page")
      .get(cisContractorFrontendUrl + "/add/change-check-unique-taxpayer-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeDoYouHaveAUniqueTaxpayerReference(option: String): HttpRequestBuilder =
    http("[post] Change Do You Have A Unique Taxpayer Reference page")
      .post(cisContractorFrontendUrl + "/add/change-check-unique-taxpayer-reference")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeWhatIsTheWorksReferenceNumber: HttpRequestBuilder =
    http("[get ] Change What Is The Works Reference Number page")
      .get(cisContractorFrontendUrl + "/add/change-works-reference-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeWhatIsTheWorksReferenceNumber(name: String): HttpRequestBuilder =
    http("[post] Change What Is The Works Reference Number page")
      .post(cisContractorFrontendUrl + "/add/change-works-reference-number")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeDoYouHaveAWorksReferenceNumber: HttpRequestBuilder =
    http("[get ] Change Do You Have A Works Reference Number page")
      .get(cisContractorFrontendUrl + "/add/change-check-works-reference-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeDoYouHaveAWorksReferenceNumber(option: String): HttpRequestBuilder =
    http("[post] Change Do You Have A Works Reference Number page")
      .post(cisContractorFrontendUrl + "/add/change-check-works-reference-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeSubcontractorsContactDetails: HttpRequestBuilder =
    http("[get ] Change Subcontractor's Contact Details page")
      .get(cisContractorFrontendUrl + "/add/change-contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeSubcontractorsContactDetails(email: String, telephone: String): HttpRequestBuilder =
    http("[post] Change Subcontractor's Contact Details page")
      .post(cisContractorFrontendUrl + "/add/change-contact-details")
      .formParam("email", email)
      .formParam("telephone", telephone)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeDoYouWantToAddTheSubcontractorsContactDetails: HttpRequestBuilder =
    http("[get ] Change Do You Want To Add The Subcontractor's contact details page")
      .get(cisContractorFrontendUrl + "/add/change-check-contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeDoYouWantToAddTheSubcontractorsContactDetails(option: String): HttpRequestBuilder =
    http("[post] Change Do You Want To Add The Subcontractor's Contact Details page")
      .post(cisContractorFrontendUrl + "/add/change-check-contact-details")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))
}
