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

object AmendCompanySubcontractorRequests extends ServicesConfiguration with CisPerformanceTestBase {

  def getCompanySubcontractorInformationPage(subbieRef: String): HttpRequestBuilder =
    http("[get ] Company subcontractor Information")
      .get(cisContractorFrontendUrl + "/amend/company/subcontractor-information")
      .queryParam("subbieResourceRef", subbieRef)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val getCompanySubcontractorInformationPageNoSubbieRef: HttpRequestBuilder =
    http("[get ] Company subcontractor Information")
      .get(cisContractorFrontendUrl + "/amend/company/subcontractor-information")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val getAmendCompanyName: HttpRequestBuilder =
    http("[get ] What is the name of the company?")
      .get(cisContractorFrontendUrl + "/amend/company/company-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAmendCompanyName(companyName: String): HttpRequestBuilder =
    http("[post] What is the name of the company?")
      .post(cisContractorFrontendUrl + "/amend/company/company-name")
      .formParam("value", companyName)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCompanyAddressChangeRedirect: HttpRequestBuilder =
    http("[get ] Redirect for company address change")
      .get(cisContractorFrontendUrl + "/amend/company/address-change")
      .check(status.is(303))

  val getCompanyAddressLookupAmendMode: HttpRequestBuilder =
    http("[get ] Company address lookup amend mode")
      .get(cisContractorFrontendUrl + "/amend/company/address")
      .queryParam("changeRoute", "change")
      .disableFollowRedirect
      .check(status.is(303))
      .check(headerRegex("Location", "(/lookup-address/[^/]+)/").saveAs("addressLookupBase"))

  val getReturnToCompanyAmendModeFrontendService: HttpRequestBuilder =
    http("[get ] Redirect to company frontend service in amend mode")
      .get(cisContractorFrontendUrl + "/amend/company/address-return/change")
      .queryParam("id", "#{id}")
      .check(status.is(303))

  val getAmendCompanyEmailAddress: HttpRequestBuilder =
    http("[get ] What is the email address for company subcontractor?")
      .get(cisContractorFrontendUrl + "/amend/company/email-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAmendCompanyEmailAddress(email: String): HttpRequestBuilder =
    http("[post] What is the email address for company subcontractor?")
      .post(cisContractorFrontendUrl + "/amend/company/email-address")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterCompanyUTR: HttpRequestBuilder =
    http("[get ] What is the Corporation Tax UTR for company subcontractor?")
      .get(cisContractorFrontendUrl + "/amend/company/company-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterCompanyUTR(utr: String): HttpRequestBuilder =
    http("[post] What is the Corporation Tax UTR for company subcontractor?")
      .post(cisContractorFrontendUrl + "/amend/company/company-utr")
      .formParam("value", utr)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterCompanyRegNumber: HttpRequestBuilder =
    http("[get ] What is the company registration number for subcontractor?")
      .get(cisContractorFrontendUrl + "/amend/company/company-registration-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterCompanyRegNumber(companyReg: String): HttpRequestBuilder =
    http("[post] What is the company registration number for company subcontractor?")
      .post(cisContractorFrontendUrl + "/amend/company/company-registration-number")
      .formParam("value", companyReg)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getEnterCompanyWorksRefNumber: HttpRequestBuilder =
    http("[get ] What is the works reference Number for company subcontractor?")
      .get(cisContractorFrontendUrl + "/amend/company/company-works-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postEnterCompanyWorksRefNumber(worksRef: String): HttpRequestBuilder =
    http("[post] What is the works reference number for subcontractor?")
      .post(cisContractorFrontendUrl + "/amend/company/company-works-reference")
      .formParam("value", worksRef)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val postCompanySubcontractorInformationPage: HttpRequestBuilder =
    http("[post] Company subcontractor information")
      .post(cisContractorFrontendUrl + "/amend/company/subcontractor-information")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCompanySubcontractorUpdated: HttpRequestBuilder =
    http("[get ] Company subcontractor details updated")
      .get(cisContractorFrontendUrl + "/amend/company/subcontractor-details-updated")
      .check(status.is(200))
}
