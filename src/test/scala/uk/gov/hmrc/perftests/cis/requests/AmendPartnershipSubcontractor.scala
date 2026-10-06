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

object AmendPartnershipSubcontractor extends ServicesConfiguration with CisPerformanceTestBase {
  def getPartnershipSubcontractorInformationPage(subbieRef: String): HttpRequestBuilder =
    http("[get ] Partnership subcontractor Information")
      .get(cisContractorFrontendUrl + "/amend/partnership/partnership-information")
      .queryParam("subbieResourceRef", subbieRef)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val getPartnershipSubcontractorInformationPageNoSubbieRef: HttpRequestBuilder =
    http("[get ] Subcontractor Information")
      .get(cisContractorFrontendUrl + "/amend/partnership/partnership-information")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val getRemovePartnershipAddress: HttpRequestBuilder =
    http("[get ] Are you sure you want to remove the address for partnership")
      .get(cisContractorFrontendUrl + "/amend/partnership/remove/information/address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postRemovePartnershipAddress(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to remove the address for partnership")
      .post(cisContractorFrontendUrl + "/amend/partnership/remove/information/address")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getRemovePartnershipContactDetails: HttpRequestBuilder =
    http("[get ] Are you sure you want to remove the contact details for partnership")
      .get(cisContractorFrontendUrl + "/amend/partnership/remove/information/contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postRemovePartnershipContactDetails(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to remove the contact details for partnership")
      .post(cisContractorFrontendUrl + "/amend/partnership/remove/information/contact-details")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getRemovePartnershipUTR: HttpRequestBuilder =
    http("[get ] Are you sure you want to remove the UTR for partnership")
      .get(cisContractorFrontendUrl + "/amend/partnership/remove/information/utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postRemovePartnershipUTR(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to remove the UTR for partnership")
      .post(cisContractorFrontendUrl + "/amend/partnership/remove/information/utr")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getRemoveNominatedPartnerUTR: HttpRequestBuilder =
    http("[get ] Are you sure you want to remove the nominated partner’s UTR")
      .get(cisContractorFrontendUrl + "/amend/partnership/remove/information/nominated-partner-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postRemoveNominatedPartnerUTR(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to remove the nominated partner’s UTR")
      .post(cisContractorFrontendUrl + "/amend/partnership/remove/information/nominated-partner-utr")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getRemoveNominatedPartnerNino: HttpRequestBuilder =
    http("[get ] Are you sure you want to remove the nominated partner’s NINO")
      .get(cisContractorFrontendUrl + "/amend/partnership/remove/information/nominated-partner-nino")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postRemoveNominatedPartnerNINO(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to remove the nominated partner’s NINO")
      .post(cisContractorFrontendUrl + "/amend/partnership/remove/information/nominated-partner-nino")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getRemoveNominatedPartnerCRN: HttpRequestBuilder =
    http("[get ] Are you sure you want to remove the nominated partner’s company registration number")
      .get(
        cisContractorFrontendUrl + "/amend/partnership/remove/information/nominated-partner-company-registration-number"
      )
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postRemoveNominatedPartnerCRN(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to remove the nominated partner’s company registration number")
      .post(
        cisContractorFrontendUrl + "/amend/partnership/remove/information/nominated-partner-company-registration-number"
      )
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getRemovePartnershipWRN: HttpRequestBuilder =
    http("[get ] Are you sure you want to remove the works reference number for partnership")
      .get(cisContractorFrontendUrl + "/amend/partnership/remove/information/works-reference-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postRemovePartnershipWRN(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to remove the works reference number for partnership")
      .post(cisContractorFrontendUrl + "/amend/partnership/remove/information/works-reference-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAmendPartnershipName: HttpRequestBuilder =
    http("[get ] What is the name of the partnership?")
      .get(cisContractorFrontendUrl + "/amend/partnership/partnership-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAmendPartnershipName(partnershipName: String): HttpRequestBuilder =
    http("[post] What is the name of the partnership?")
      .post(cisContractorFrontendUrl + "/amend/partnership/partnership-name")
      .formParam("value", partnershipName)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddPartnershipAddress: HttpRequestBuilder =
    http("[get ] Do you want to add address for partnership")
      .get(cisContractorFrontendUrl + "/amend/partnership/check-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddPartnershipAddress(option: String): HttpRequestBuilder =
    http("[post] Do you want to add address for partnership")
      .post(cisContractorFrontendUrl + "/amend/partnership/check-address")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getPartnershipAddressChangeRedirect: HttpRequestBuilder =
    http("[get ] Redirect for partnership address change")
      .get(cisContractorFrontendUrl + "/amend/partnership/address-change")
      .check(status.is(303))

  val getPartnershipAddressLookupAmendMode: HttpRequestBuilder =
    http("[get ] Partnership address lookup amend mode")
      .get(cisContractorFrontendUrl + "/amend/partnership/address")
      .queryParam("changeRoute", "change")
      .disableFollowRedirect
      .check(status.is(303))
      .check(headerRegex("Location", "(/lookup-address/[^/]+)/").saveAs("addressLookupBase"))

  val getReturnToPartnershipAmendModeFrontendService: HttpRequestBuilder =
    http("[get ] Redirect to partnership frontend service in amend mode")
      .get(cisContractorFrontendUrl + "/amend/partnership/address-return/change")
      .queryParam("id", "#{id}")
      .check(status.is(303))

  val getAddPartnershipContactDetails: HttpRequestBuilder =
    http("[get ] Do you want to add contact details for partnership")
      .get(cisContractorFrontendUrl + "/amend/partnership/add-contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddPartnershipContactDetails(option: String): HttpRequestBuilder =
    http("[post] Do you want to add contact details for partnership")
      .post(cisContractorFrontendUrl + "/amend/partnership/add-contact-details")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getSelectContactDetailsToAddForPartnership: HttpRequestBuilder =
    http("[get ] Which contact details do you want to add for partnership")
      .get(cisContractorFrontendUrl + "/amend/partnership/select-contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postSelectContactDetailsToAddForPartnership: HttpRequestBuilder =
    http("[post] Which contact details do you want to add for partnership")
      .post(cisContractorFrontendUrl + "/amend/partnership/select-contact-details")
      .formParamMap(
        Map(
          "value[0]"  -> "email",
          "value[1]"  -> "phone",
          "value[2]"  -> "mobile",
          "csrfToken" -> f"#{csrfToken}"
        )
      )
      .check(status.is(303))

  val getEnterPartnershipEmailAddress: HttpRequestBuilder =
    http("[get ] What is the email address for partnership?")
      .get(cisContractorFrontendUrl + "/amend/partnership/email-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterPartnershipEmailAddress(email: String): HttpRequestBuilder =
    http("[post] What is the email address for partnership?")
      .post(cisContractorFrontendUrl + "/amend/partnership/email-address")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterPartnershipPhoneNumber: HttpRequestBuilder =
    http("[get ] What is the phone number for partnership?")
      .get(cisContractorFrontendUrl + "/amend/partnership/phone-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterPartnershipPhoneNumber(phone: String): HttpRequestBuilder =
    http("[post] What is the phone number for partnership?")
      .post(cisContractorFrontendUrl + "/amend/partnership/phone-number")
      .formParam("value", phone)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterPartnershipMobileNumber: HttpRequestBuilder =
    http("[get ] What is the mobile number for partnership?")
      .get(cisContractorFrontendUrl + "/amend/partnership/mobile-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterPartnershipMobileNumber(mobile: String): HttpRequestBuilder =
    http("[post] What is the mobile number for partnership?")
      .post(cisContractorFrontendUrl + "/amend/partnership/mobile-number")
      .formParam("value", mobile)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddPartnershipUTR: HttpRequestBuilder =
    http("[get ] Do you know the Unique Taxpayer Reference for partnership")
      .get(cisContractorFrontendUrl + "/amend/partnership/partnership-has-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddPartnershipUTR(option: String): HttpRequestBuilder =
    http("[post] Do you know the Unique Taxpayer Reference for partnership")
      .post(cisContractorFrontendUrl + "/amend/partnership/partnership-has-utr")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterPartnershipUTR: HttpRequestBuilder =
    http("[get ] What is the UTR for partnership?")
      .get(cisContractorFrontendUrl + "/amend/partnership/partnership-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterPartnershipUTR(utr: String): HttpRequestBuilder =
    http("[post] What is the UTR for partnership?")
      .post(cisContractorFrontendUrl + "/amend/partnership/partnership-utr")
      .formParam("value", utr)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAmendNominatedPartnerName: HttpRequestBuilder =
    http("[get ] Who is the nominated partner for partnership?")
      .get(cisContractorFrontendUrl + "/amend/partnership/nominated-partner")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAmendNominatedPartnerName(name: String): HttpRequestBuilder =
    http("[post] Who is the nominated partner for partnership?")
      .post(cisContractorFrontendUrl + "/amend/partnership/nominated-partner")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddNominatedPartnerUTR: HttpRequestBuilder =
    http("[get ] Do you know the Unique Taxpayer Reference for nominated partner")
      .get(cisContractorFrontendUrl + "/amend/partnership/nominated-partner-has-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddNominatedPartnerUTR(option: String): HttpRequestBuilder =
    http("[post] Do you know the Unique Taxpayer Reference for nominated partner")
      .post(cisContractorFrontendUrl + "/amend/partnership/nominated-partner-has-utr")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterNominatedPartnershipUTR: HttpRequestBuilder =
    http("[get ] What is the UTR for nominated partner?")
      .get(cisContractorFrontendUrl + "/amend/partnership/nominated-partner-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterNominatedPartnershipUTR(utr: String): HttpRequestBuilder =
    http("[post] What is the UTR for nominated partner?")
      .post(cisContractorFrontendUrl + "/amend/partnership/nominated-partner-utr")
      .formParam("value", utr)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddNominatedPartnerNino: HttpRequestBuilder =
    http("[get ] Do you know the National Insurance number for nominated partner")
      .get(cisContractorFrontendUrl + "/amend/partnership/nominated-partner-has-national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddNominatedPartnerNINO(option: String): HttpRequestBuilder =
    http("[post] Do you know the National Insurance number for nominated partner")
      .post(cisContractorFrontendUrl + "/amend/partnership/nominated-partner-has-national-insurance-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterNominatedPartnerNINO: HttpRequestBuilder =
    http("[get ] What is the National Insurance Number for nominated partner?")
      .get(cisContractorFrontendUrl + "/amend/partnership/nominated-partner-national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterNominatedPartnerNINO(nino: String): HttpRequestBuilder =
    http("[post] What is the National Insurance number for nominated partner?")
      .post(cisContractorFrontendUrl + "/amend/partnership/nominated-partner-national-insurance-number")
      .formParam("value", nino)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddNominatedPartnerCRN: HttpRequestBuilder =
    http("[get ] Does nominated partner have a company registration number?")
      .get(cisContractorFrontendUrl + "/amend/partnership/nominated-partner-has-company-registration-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddNominatedPartnerCRN(option: String): HttpRequestBuilder =
    http("[post] Does nominated partner have a company registration number?")
      .post(cisContractorFrontendUrl + "/amend/partnership/nominated-partner-has-company-registration-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterNominatedPartnerCRN: HttpRequestBuilder =
    http("[get ] What is the company registration number for nominated partner?")
      .get(cisContractorFrontendUrl + "/amend/partnership/nominated-partner-company-registration-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterNominatedPartnerCRN(crn: String): HttpRequestBuilder =
    http("[post] What is the company registration number for nominated partner?")
      .post(cisContractorFrontendUrl + "/amend/partnership/nominated-partner-company-registration-number")
      .formParam("value", crn)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddPartnershipWRN: HttpRequestBuilder =
    http("[get ] Is there a works reference number associated with nominated partner")
      .get(cisContractorFrontendUrl + "/amend/partnership/partnership-has-works-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddPartnershipWRN(option: String): HttpRequestBuilder =
    http("[post] Is there a works reference number associated with nominated partner")
      .post(cisContractorFrontendUrl + "/amend/partnership/partnership-has-works-reference")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterPartnershipWorksRefNumber: HttpRequestBuilder =
    http("[get ] What is the works reference number associated with nominated partner?")
      .get(cisContractorFrontendUrl + "/amend/partnership/partnership-works-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterPartnershipWorksRefNumber(worksRef: String): HttpRequestBuilder =
    http("[post] What is the works reference number associated with nominated partner?")
      .post(cisContractorFrontendUrl + "/amend/partnership/partnership-works-reference")
      .formParam("value", worksRef)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val postPartnershipSubcontractorInformationPage: HttpRequestBuilder =
    http("[post] Partnership subcontractor information")
      .post(cisContractorFrontendUrl + "/amend/partnership/partnership-information")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getPartnershipSubcontractorUpdated: HttpRequestBuilder =
    http("[get ] Partnership subcontractor details updated")
      .get(cisContractorFrontendUrl + "/amend/partnership/subcontractor-details-updated")
      .check(status.is(200))
}
