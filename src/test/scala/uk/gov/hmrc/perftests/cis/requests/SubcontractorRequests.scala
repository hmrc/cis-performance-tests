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

  val getWhatTypeOfSubcontractorAreYouAdding: HttpRequestBuilder =
    http("[get] What Type Of Subcontractor Are You Adding page")
      .get(cisContractorFrontendUrl + "/type-of-subcontractor")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatTypeOfSubcontractorAreYouAdding(option: String): HttpRequestBuilder =
    http("[post] What Type of Subcontractor Are You Adding page")
      .post(cisContractorFrontendUrl + "/type-of-subcontractor")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDoesTheSubcontractorUseATradingName: HttpRequestBuilder =
    http("[get] Does The Subcontractor Use A Trading Name page")
      .get(cisContractorFrontendUrl + "/check-trading-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoesTheSubcontractorUseATradingName(option: String): HttpRequestBuilder =
    http("[post] Does The Subcontractor Use A Trading Name page")
      .post(cisContractorFrontendUrl + "/check-trading-name")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getWhatIsTheSubcontractorsTradingName: HttpRequestBuilder =
    http("[get] What Is The Subcontractor's Trading Name page")
      .get(cisContractorFrontendUrl + "/trading-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatIsTheSubcontractorsTradingName(name: String): HttpRequestBuilder =
    http("[post] What Is The Subcontractor's Trading Name page")
      .post(cisContractorFrontendUrl + "/trading-name")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDoYouWantToAddTheSubcontractorsAddress: HttpRequestBuilder =
    http("[get] Do You Want To Add The Subcontractor's Address page")
      .get(cisContractorFrontendUrl + "/check-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoYouWantToAddTheSubcontractorsAddress(option: String): HttpRequestBuilder =
    http("[post] Do You Want To Add The Subcontractor's Address page")
      .post(cisContractorFrontendUrl + "/check-address")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getWhatIsTheSubcontractorsAddress: HttpRequestBuilder =
    http("[get] What Is The Subcontractor's Address page")
      .get(cisContractorFrontendUrl + "/address")
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
      .post(cisContractorFrontendUrl + "/address")
      .formParam("value.line1", line1)
      .formParam("value.line2", line2)
      .formParam("value.town", town)
      .formParam("value.country", country)
      .formParam("value.postcode", postcode)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDoYouHaveANationalInsuranceNumber: HttpRequestBuilder =
    http("[get] Do You Have A National Insurance Number page")
      .get(cisContractorFrontendUrl + "/check-national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoYouHaveANationalInsuranceNumber(option: String): HttpRequestBuilder =
    http("[post] Do You Have A National Insurance page")
      .post(cisContractorFrontendUrl + "/check-national-insurance-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getWhatIsTheSubcontractorsNationalInsuranceNumber: HttpRequestBuilder =
    http("[get] What Is The Subcontractor's National Insurance Number page")
      .get(cisContractorFrontendUrl + "/national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatIsTheSubcontractorsNationalInsuranceNumber(name: String): HttpRequestBuilder =
    http("[post] What Is The Subcontractor's National Insurance Number page")
      .post(cisContractorFrontendUrl + "/national-insurance-number")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDoYouHaveAUniqueTaxpayerReference: HttpRequestBuilder =
    http("[get] Do You Have A Unique Taxpayer Reference page")
      .get(cisContractorFrontendUrl + "/check-unique-taxpayer-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoYouHaveAUniqueTaxpayerReference(option: String): HttpRequestBuilder =
    http("[post] Do You Have A Unique Taxpayer Reference page")
      .post(cisContractorFrontendUrl + "/check-unique-taxpayer-reference")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getWhatIsTheSubcontractorsUniqueTaxpayerReference: HttpRequestBuilder =
    http("[get] What Is The Subcontractor's Unique Taxpayer Reference page")
      .get(cisContractorFrontendUrl + "/unique-taxpayer-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatIsTheSubcontractorsUniqueTaxpayerReference(name: String): HttpRequestBuilder =
    http("[post] What Is The Subcontractor's Unique Taxpayer Reference page")
      .post(cisContractorFrontendUrl + "/unique-taxpayer-reference")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDoYouHaveAWorksReferenceNumber: HttpRequestBuilder =
    http("[get] Do You Have A Works Reference Number page")
      .get(cisContractorFrontendUrl + "/check-works-reference-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoYouHaveAWorksReferenceNumber(option: String): HttpRequestBuilder =
    http("[post] Do You Have A Works Reference Number page")
      .post(cisContractorFrontendUrl + "/check-works-reference-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getWhatIsTheWorksReferenceNumber: HttpRequestBuilder =
    http("[get] What Is The Works Reference Number page")
      .get(cisContractorFrontendUrl + "/works-reference-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatIsTheWorksReferenceNumber(name: String): HttpRequestBuilder =
    http("[post] What Is The Works Reference Number page")
      .post(cisContractorFrontendUrl + "/works-reference-number")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDoYouWantToAddTheSubcontractorsContactDetails: HttpRequestBuilder =
    http("[get] Do You Want To Add The Subcontractor's contact details page")
      .get(cisContractorFrontendUrl + "/check-contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoYouWantToAddTheSubcontractorsContactDetails(option: String): HttpRequestBuilder =
    http("[post] Do You Want To Add The Subcontractor's Contact Details page")
      .post(cisContractorFrontendUrl + "/check-contact-details")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getSubcontractorsContactDetails: HttpRequestBuilder =
    http("[get] Subcontractor's Contact Details page")
      .get(cisContractorFrontendUrl + "/contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postSubcontractorsContactDetails(
                                          email: String,
                                          phone: String,
                                        ): HttpRequestBuilder =
    http("[post] Subcontractor's Contact Details page")
      .post(cisContractorFrontendUrl + "/contact-details")
      .formParam("value.email", email)
      .formParam("value.phone", phone)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))
}
