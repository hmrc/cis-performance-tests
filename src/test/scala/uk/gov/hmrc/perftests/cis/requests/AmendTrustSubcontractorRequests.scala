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

object AmendTrustSubcontractorRequests extends ServicesConfiguration with CisPerformanceTestBase {

  def getTrustSubcontractorInformationPage(subbieRef: String): HttpRequestBuilder =
    http("[get ] Trust subcontractor information")
      .get(cisContractorFrontendUrl + "/amend/trust/subcontractor-information")
      .queryParam("subbieResourceRef", subbieRef)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val getTrustSubcontractorInformationPageNoSubbieRef: HttpRequestBuilder =
    http("[get ] Subcontractor Information")
      .get(cisContractorFrontendUrl + "/amend/trust/subcontractor-information")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val getAmendTrustName: HttpRequestBuilder =
    http("[get ] What is the name of the trust?")
      .get(cisContractorFrontendUrl + "/amend/trust/trust-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAmendTrustName(trustName: String): HttpRequestBuilder =
    http("[post] What is the name of the trust?")
      .post(cisContractorFrontendUrl + "/amend/trust/trust-name")
      .formParam("value", trustName)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getTrustAddressChangeRedirect: HttpRequestBuilder =
    http("[get ] Redirect for trust address change")
      .get(cisContractorFrontendUrl + "/amend/trust/address-change")
      .check(status.is(303))

  val getTrustAddressLookupAmendMode: HttpRequestBuilder =
    http("[get ] Trust address lookup amend mode")
      .get(cisContractorFrontendUrl + "/amend/trust/address")
      .queryParam("changeRoute", "change")
      .disableFollowRedirect
      .check(status.is(303))
      .check(headerRegex("Location", "(/lookup-address/[^/]+)/").saveAs("addressLookupBase"))

  val getReturnToTrustAmendModeFrontendService: HttpRequestBuilder =
    http("[get ] Redirect to trust frontend service in amend mode")
      .get(cisContractorFrontendUrl + "/amend/trust/address-return/change")
      .queryParam("id", "#{id}")
      .check(status.is(303))

  val getSelectContactDetailsToAddForTrust: HttpRequestBuilder =
    http("[get ] Which contact details do you want to add for trust")
      .get(cisContractorFrontendUrl + "/amend/trust/select-contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postSelectContactDetailsToAddForTrust: HttpRequestBuilder =
    http("[post] Which contact details do you want to add for trust")
      .post(cisContractorFrontendUrl + "/amend/trust/select-contact-details")
      .formParamMap(
        Map(
          "value[1]"  -> "phone",
          "csrfToken" -> f"#{csrfToken}"
        )
      )
      .check(status.is(303))

  val getEnterTrustPhoneNumber: HttpRequestBuilder =
    http("[get ] What is the phone number for trust?")
      .get(cisContractorFrontendUrl + "/amend/trust/phone-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterTrustPhoneNumber(phone: String): HttpRequestBuilder =
    http("[post] What is the phone number for trust?")
      .post(cisContractorFrontendUrl + "/amend/trust/phone-number")
      .formParam("value", phone)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterTrustUTR: HttpRequestBuilder =
    http("[get ] What is the UTR for trust?")
      .get(cisContractorFrontendUrl + "/amend/trust/trust-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterTrustUTR(utr: String): HttpRequestBuilder =
    http("[post] What is the UTR for trust?")
      .post(cisContractorFrontendUrl + "/amend/trust/trust-utr")
      .formParam("value", utr)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterTrustWorksRefNumber: HttpRequestBuilder =
    http("[get ] What is the works reference number associated with nominated partner?")
      .get(cisContractorFrontendUrl + "/amend/trust/trust-works-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterTrustWorksRefNumber(worksRef: String): HttpRequestBuilder =
    http("[post] What is the works reference number associated with nominated partner?")
      .post(cisContractorFrontendUrl + "/amend/trust/trust-works-reference")
      .formParam("value", worksRef)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val postTrustSubcontractorInformationPage: HttpRequestBuilder =
    http("[post] Trust subcontractor information")
      .post(cisContractorFrontendUrl + "/amend/trust/subcontractor-information")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getTrustSubcontractorUpdated: HttpRequestBuilder =
    http("[get ] Trust subcontractor details updated")
      .get(cisContractorFrontendUrl + "/amend/trust/subcontractor-details-updated")
      .check(status.is(200))
}
