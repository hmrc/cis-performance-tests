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

object AddCompanySubcontractorRequests extends ServicesConfiguration with CisPerformanceTestBase {

  val getCompanyNamePage: HttpRequestBuilder =
    http("[get ] What is the company name page")
      .get(cisContractorFrontendUrl + "/add/company/company-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postCompanyNamePage(companyName: String): HttpRequestBuilder =
    http("[post] What is the company name page")
      .post(cisContractorFrontendUrl + "/add/company/company-name")
      .formParam("value", companyName)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddCompanyAddressPage: HttpRequestBuilder =
    http("[get ] Do you want to add an address for this company page")
      .get(cisContractorFrontendUrl + "/add/company/check-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddCompanyAddressPage(option: String): HttpRequestBuilder =
    http("[post] Do you want to add an address for this company page")
      .post(cisContractorFrontendUrl + "/add/company/check-address")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCompanyAddressPage: HttpRequestBuilder =
    http("[get ] What is the address for this company page")
      .get(cisContractorFrontendUrl + "/add/company/address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postCompanyAddressPage(
    addressLine1: String,
    addressLine2: String,
    town: String,
    county: String,
    postalCode: String,
    country: String
  ): HttpRequestBuilder =
    http("[post] What is the address for this company page")
      .post(cisContractorFrontendUrl + "/add/company/address")
      .formParam("addressLine1", addressLine1)
      .formParam("addressLine2", addressLine2)
      .formParam("addressLine3", town)
      .formParam("addressLine4", county)
      .formParam("postalCode", postalCode)
      .formParam("country", country)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCompanyContactMethodPage: HttpRequestBuilder =
    http("[get ] What is the contact method for this company page")
      .get(cisContractorFrontendUrl + "/add/company/choose-contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postCompanyContactMethodPage(contactOption: String): HttpRequestBuilder =
    http("[post] What is the contact method for this company page")
      .post(cisContractorFrontendUrl + "/add/company/choose-contact-details")
      .formParam("value", contactOption)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCompanyEmailAddressPage: HttpRequestBuilder =
    http("[get ] What is the company email address page")
      .get(cisContractorFrontendUrl + "/add/company/email-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postCompanyEmailAddressPage(email: String): HttpRequestBuilder =
    http("[post] What is the company email address page")
      .post(cisContractorFrontendUrl + "/add/company/email-address")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCompanyPhoneNumberPage: HttpRequestBuilder =
    http("[get ] What is the company phone number page")
      .get(cisContractorFrontendUrl + "/add/company/phone-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postCompanyPhoneNumberPage(phoneNumber: String): HttpRequestBuilder =
    http("[post] What is the company phone number page")
      .post(cisContractorFrontendUrl + "/add/company/phone-number")
      .formParam("value", phoneNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCompanyMobileNumberPage: HttpRequestBuilder =
    http("[get ] What is the company mobile number page")
      .get(cisContractorFrontendUrl + "/add/company/mobile-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postCompanyMobileNumberPage(mobileNumber: String): HttpRequestBuilder =
    http("[post] What is the company mobile number page")
      .post(cisContractorFrontendUrl + "/add/company/mobile-number")
      .formParam("value", mobileNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddCompanyUTRPage: HttpRequestBuilder =
    http("[get ] Does this company have a UTR page")
      .get(cisContractorFrontendUrl + "/add/company/company-has-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddCompanyUTRPage(option: String): HttpRequestBuilder =
    http("[post] Does this company have a UTR page")
      .post(cisContractorFrontendUrl + "/add/company/company-has-utr")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCompanyUTRPage: HttpRequestBuilder =
    http("[get ] What is the UTR for this company page")
      .get(cisContractorFrontendUrl + "/add/company/company-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postCompanyUTRPage(utr: String): HttpRequestBuilder =
    http("[post] What is the UTR for this company page")
      .post(cisContractorFrontendUrl + "/add/company/company-utr")
      .formParam("value", utr)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddCompanyCrnPage: HttpRequestBuilder =
    http("[get ] Does this company have a company registration number page")
      .get(cisContractorFrontendUrl + "/add/company/company-has-registration-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddCompanyCrnPage(option: String): HttpRequestBuilder =
    http("[post] Does this company have a company registration number page")
      .post(cisContractorFrontendUrl + "/add/company/company-has-registration-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCompanyCrnPage: HttpRequestBuilder =
    http("[get ] What is the company registration number")
      .get(cisContractorFrontendUrl + "/add/company/company-registration-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postCompanyCrnPage(Crn: String): HttpRequestBuilder =
    http("[post] What is the company registration number")
      .post(cisContractorFrontendUrl + "/add/company/company-registration-number")
      .formParam("value", Crn)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddCompanyWrnPage: HttpRequestBuilder =
    http("[get ] Is there a works reference number associated with this company page")
      .get(cisContractorFrontendUrl + "/add/company/company-has-works-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddCompanyWrnPage(option: String): HttpRequestBuilder =
    http("[post] Is there a works reference number associated with this company page")
      .post(cisContractorFrontendUrl + "/add/company/company-has-works-reference")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCompanyWrnPage: HttpRequestBuilder =
    http("[get ] What is the works reference number for this company page")
      .get(cisContractorFrontendUrl + "/add/company/company-works-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postCompanyWrnPage(worksRefNumber: String): HttpRequestBuilder =
    http("[post] What is the works reference number for this company page")
      .post(cisContractorFrontendUrl + "/add/company/company-works-reference")
      .formParam("value", worksRefNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCompanyCheckYourAnswersPage: HttpRequestBuilder =
    http("[get ] Check your answers before adding this company page")
      .get(cisContractorFrontendUrl + "/add/company/check-your-answers")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postCompanyCheckYourAnswersPage: HttpRequestBuilder =
    http("[post] Check your answers before adding this company page")
      .post(cisContractorFrontendUrl + "/add/company/check-your-answers")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeCompanyNamePage: HttpRequestBuilder =
    http("[get ] What is the company name page")
      .get(cisContractorFrontendUrl + "/add/company/change-company-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeCompanyNamePage(companyName: String): HttpRequestBuilder =
    http("[post] What is the company name page")
      .post(cisContractorFrontendUrl + "/add/company/change-company-name")
      .formParam("value", companyName)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeAddCompanyAddressPage: HttpRequestBuilder =
    http("[get ] Do you want to add an address for this subcontractor page")
      .get(cisContractorFrontendUrl + "/add/company/change-check-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeAddCompanyAddressPage(option: String): HttpRequestBuilder =
    http("[post] Do you want to add an address for this subcontractor page")
      .post(cisContractorFrontendUrl + "/add/company/change-check-address")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeCompanyAddressPage: HttpRequestBuilder =
    http("[get ] What is the address for this company page")
      .get(cisContractorFrontendUrl + "/add/company/change-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeCompanyAddressPage(
    addressLine1: String,
    addressLine2: String,
    town: String,
    county: String,
    postalCode: String,
    country: String
  ): HttpRequestBuilder =
    http("[post] What is the address for this company page")
      .post(cisContractorFrontendUrl + "/add/company/change-address")
      .formParam("addressLine1", addressLine1)
      .formParam("addressLine2", addressLine2)
      .formParam("addressLine3", town)
      .formParam("addressLine4", county)
      .formParam("postalCode", postalCode)
      .formParam("country", country)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeCompanyContactMethodPage: HttpRequestBuilder =
    http("[get ] What is the contact method for this company page")
      .get(cisContractorFrontendUrl + "/add/company/change-choose-contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeCompanyContactMethodPage(contactOption: String): HttpRequestBuilder =
    http("[post] What is the contact method for this company page")
      .post(cisContractorFrontendUrl + "/add/company/change-choose-contact-details")
      .formParam("value", contactOption)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeCompanyMobileNumberPage: HttpRequestBuilder =
    http("[get ] What is the company mobile number page")
      .get(cisContractorFrontendUrl + "/add/company/change-mobile-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeCompanyMobileNumberPage(mobileNumber: String): HttpRequestBuilder =
    http("[post] What is the company mobile number page")
      .post(cisContractorFrontendUrl + "/add/company/change-mobile-number")
      .formParam("value", mobileNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeAddCompanyUTRPage: HttpRequestBuilder =
    http("[get ] Change does this company have a UTR page")
      .get(cisContractorFrontendUrl + "/add/company/change-company-has-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeAddCompanyUTRPage(option: String): HttpRequestBuilder =
    http("[post] Change does this company have a UTR page")
      .post(cisContractorFrontendUrl + "/add/company/change-company-has-utr")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeCompanyUTRPage: HttpRequestBuilder =
    http("[get ] What is the UTR for this company page")
      .get(cisContractorFrontendUrl + "/add/company/change-company-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeCompanyUTRPage(utr: String): HttpRequestBuilder =
    http("[post] What is the UTR for this company page")
      .post(cisContractorFrontendUrl + "/add/company/change-company-utr")
      .formParam("value", utr)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeAddCompanyCrnPage: HttpRequestBuilder =
    http("[get ] Does this company have a company registration number page")
      .get(cisContractorFrontendUrl + "/add/company/change-company-has-registration-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeAddCompanyCrnPage(option: String): HttpRequestBuilder =
    http("[post] Does this company have a company registration number page")
      .post(cisContractorFrontendUrl + "/add/company/change-company-has-registration-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeCompanyCrnPage: HttpRequestBuilder =
    http("[get ] What is the company registration number")
      .get(cisContractorFrontendUrl + "/add/company/change-company-registration-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeCompanyCrnPage(companyCrn: String): HttpRequestBuilder =
    http("[post] What is the company registration number")
      .post(cisContractorFrontendUrl + "/add/company/change-company-registration-number")
      .formParam("value", companyCrn)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeAddCompanyWrnPage: HttpRequestBuilder =
    http("[get ] Is there a works reference number associated with this company page")
      .get(cisContractorFrontendUrl + "/add/company/change-company-has-works-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeAddCompanyWrnPage(option: String): HttpRequestBuilder =
    http("[post] Is there a works reference number associated with this company page")
      .post(cisContractorFrontendUrl + "/add/company/change-company-has-works-reference")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeCompanyWrnPage: HttpRequestBuilder =
    http("[get ] What is the works reference number for this company page")
      .get(cisContractorFrontendUrl + "/add/company/change-company-works-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeCompanyWrnPage(worksRefNumber: String): HttpRequestBuilder =
    http("[post] What is the works reference number for this company page")
      .post(cisContractorFrontendUrl + "/add/company/change-company-works-reference")
      .formParam("value", worksRefNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getCompanySubcontractorAddedPage: HttpRequestBuilder =
    http("[get ] Company subcontractor added")
      .get(cisContractorFrontendUrl + "/add/company/subcontractor-added")
      .check(status.is(200))
}
