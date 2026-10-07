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

object AmendIndividualSubcontractorRequests extends ServicesConfiguration with CisPerformanceTestBase {

  def getSubcontractorInformationRedirect(subbieRef: String): HttpRequestBuilder =
    http("[get ] Redirect to Subcontractor Information page")
      .get(cisContractorFrontendUrl + s"/amend/start/$subbieRef/standard")
      .check(status.is(303))

  def getSubcontractorInformationPage(subbieRef: String): HttpRequestBuilder =
    http("[get ] Subcontractor Information")
      .get(cisContractorFrontendUrl + "/amend/subcontractor-information")
      .queryParam("subbieResourceRef", subbieRef)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val getSubcontractorInformationPageNoSubbieRef: HttpRequestBuilder =
    http("[get ] Subcontractor Information")
      .get(cisContractorFrontendUrl + "/amend/subcontractor-information")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postSubcontractorInformationPage(subbieRef: String): HttpRequestBuilder =
    http("[post] Subcontractor Information")
      .post(cisContractorFrontendUrl + "/amend/subcontractor-information")
      .queryParam("subbieResourceRef", subbieRef)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCancelChanges: HttpRequestBuilder =
    http("[get ] Cancel changes")
      .get(cisContractorFrontendUrl + "/amend/cancel")
      .check(status.is(303))

  val getAmendNameOptions: HttpRequestBuilder =
    http("[get ] Which names do you want to add for this subcontractor?")
      .get(cisContractorFrontendUrl + "/amend/names")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAmendNameOptions(index: Int, option: String): HttpRequestBuilder =
    http("[post] Which names do you want to add for this subcontractor?")
      .post(cisContractorFrontendUrl + "/amend/names")
      .formParam(s"value[$index]", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAmendSubcontractorName: HttpRequestBuilder =
    http("[get ] What is the subcontractor’s name?")
      .get(cisContractorFrontendUrl + "/amend/name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAmendSubcontractorName(fName: String, mName: String, lName: String): HttpRequestBuilder =
    http("[post] What is the subcontractor’s name?")
      .post(cisContractorFrontendUrl + "/amend/name")
      .formParamMap(
        Map(
          "firstName"  -> fName,
          "middleName" -> mName,
          "lastName"   -> lName,
          "csrfToken"  -> f"#{csrfToken}"
        )
      )
      .check(status.is(303))

  val getAmendTradingName: HttpRequestBuilder =
    http("[get ] What is the subcontractor’s trading name?")
      .get(cisContractorFrontendUrl + "/amend/trading-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAmendTradingName(tradingName: String): HttpRequestBuilder =
    http("[post] What is the subcontractor’s trading name?")
      .post(cisContractorFrontendUrl + "/amend/trading-name")
      .formParam("value", tradingName)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getRemoveAddress: HttpRequestBuilder =
    http("[get ] Are you sure you want to remove the address for subcontractor")
      .get(cisContractorFrontendUrl + "/amend/remove/information/address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postRemoveAddress(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to remove the address for subcontractor")
      .post(cisContractorFrontendUrl + "/amend/remove/information/address")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddressChangeRedirect: HttpRequestBuilder =
    http("[get ] Redirect for address change")
      .get(cisContractorFrontendUrl + "/amend/address-change")
      .check(status.is(303))

  val getIndividualAddressLookupAmendMode: HttpRequestBuilder =
    http("[get ] Individual address lookup amend mode")
      .get(cisContractorFrontendUrl + "/amend/individual-address-lookup")
      .queryParam("changeRoute", "change")
      .disableFollowRedirect
      .check(status.is(303))
      .check(headerRegex("Location", "(/lookup-address/[^/]+)/").saveAs("addressLookupBase"))

  val getReturnToIndividualAmendModeFrontendService: HttpRequestBuilder =
    http("[get ] Redirect to individual frontend service in amend mode")
      .get(cisContractorFrontendUrl + "/amend/individual-address-return/change")
      .queryParam("id", "#{id}")
      .check(status.is(303))

  val getRemoveContactDetails: HttpRequestBuilder =
    http("[get ] Are you sure you want to remove the contact details for subcontractor")
      .get(cisContractorFrontendUrl + "/amend/remove/information/contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postRemoveContactDetails(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to remove the contact details for subcontractor")
      .post(cisContractorFrontendUrl + "/amend/remove/information/contact-details")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddContactDetails: HttpRequestBuilder =
    http("[get ] Are you sure you want to add contact details for subcontractor")
      .get(cisContractorFrontendUrl + "/amend/add-contact-details")
      .check(status.is(200))

  def postAddContactDetails(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to add the contact details for subcontractor")
      .post(cisContractorFrontendUrl + "/amend/add-contact-details")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getSelectContactDetailsToAdd: HttpRequestBuilder =
    http("[get ] Which contact details do you want to add for subcontractor")
      .get(cisContractorFrontendUrl + "/amend/select-contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postSelectContactDetailsToAdd: HttpRequestBuilder =
    http("[post] Which contact details do you want to add for subcontractor")
      .post(cisContractorFrontendUrl + "/amend/select-contact-details")
      .formParamMap(
        Map(
          "value[0]"  -> "email",
          "value[1]"  -> "phone",
          "value[2]"  -> "mobile",
          "csrfToken" -> f"#{csrfToken}"
        )
      )
      .check(status.is(303))

  val getEnterEmailAddress: HttpRequestBuilder =
    http("[get ] What is the email address for subcontractor?")
      .get(cisContractorFrontendUrl + "/amend/email-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterEmailAddress(email: String): HttpRequestBuilder =
    http("[post] What is the email address for subcontractor?")
      .post(cisContractorFrontendUrl + "/amend/email-address")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterPhoneNumber: HttpRequestBuilder =
    http("[get ] What is the phone number for subcontractor?")
      .get(cisContractorFrontendUrl + "/amend/phone-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterPhoneNumber(phone: String): HttpRequestBuilder =
    http("[post] What is the phone number for subcontractor?")
      .post(cisContractorFrontendUrl + "/amend/phone-number")
      .formParam("value", phone)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterMobileNumber: HttpRequestBuilder =
    http("[get ] What is the mobile number for subcontractor?")
      .get(cisContractorFrontendUrl + "/amend/mobile-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterMobileNumber(mobile: String): HttpRequestBuilder =
    http("[post] What is the mobile number for subcontractor?")
      .post(cisContractorFrontendUrl + "/amend/mobile-number")
      .formParam("value", mobile)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterNINumber: HttpRequestBuilder =
    http("[get ] What is the National Insurance Number for subcontractor?")
      .get(cisContractorFrontendUrl + "/amend/national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterNINumber(nino: String): HttpRequestBuilder =
    http("[post] What is the National Insurance number for subcontractor?")
      .post(cisContractorFrontendUrl + "/amend/national-insurance-number")
      .formParam("value", nino)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterWorksRefNumber: HttpRequestBuilder =
    http("[get ] What is the works reference Number for subcontractor?")
      .get(cisContractorFrontendUrl + "/amend/works-reference-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterWorksRefNumber(worksRef: String): HttpRequestBuilder =
    http("[post] What is the works reference number for subcontractor?")
      .post(cisContractorFrontendUrl + "/amend/works-reference-number")
      .formParam("value", worksRef)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val postIndividualSubcontractorInformationPage: HttpRequestBuilder =
    http("[post] Individual subcontractor information")
      .post(cisContractorFrontendUrl + "/amend/subcontractor-information")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getIndividualSubcontractorUpdated: HttpRequestBuilder =
    http("[get ] Individual subcontractor details updated")
      .get(cisContractorFrontendUrl + "/amend/individual/subcontractor-details-updated")
      .check(status.is(200))
}
