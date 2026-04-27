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

object AddTrustSubcontractorRequests extends ServicesConfiguration with CisPerformanceTestBase {

  val getTrustNamePage: HttpRequestBuilder =
    http("[get ] What is the name of the trust page")
      .get(cisContractorFrontendUrl + "/add/trust/trust-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postTrustNamePage(trustName: String): HttpRequestBuilder =
    http("[post] What is the name of the trust page")
      .post(cisContractorFrontendUrl + "/add/trust/trust-name")
      .formParam("value", trustName)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddTrustAddressPage: HttpRequestBuilder =
    http("[get ] Do you want to add an address for this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/check-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddTrustAddressPage(option: String): HttpRequestBuilder =
    http("[post] Do you want to add an address for this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/check-address")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getTrustAddressPage: HttpRequestBuilder =
    http("[get ] What is the address for this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postTrustAddressPage(
    addressLine1: String,
    addressLine2: String,
    town: String,
    county: String,
    postalCode: String,
    country: String
  ): HttpRequestBuilder =
    http("[post] What is the address for this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/address")
      .formParam("addressLine1", addressLine1)
      .formParam("addressLine2", addressLine2)
      .formParam("addressLine3", town)
      .formParam("addressLine4", county)
      .formParam("postalCode", postalCode)
      .formParam("country", country)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getTrustContactMethodPage: HttpRequestBuilder =
    http("[get ] What are the contact details for this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/choose-contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postTrustContactMethodPage(contactOption: String): HttpRequestBuilder =
    http("[post] What are the contact details for this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/choose-contact-details")
      .formParam("value", contactOption)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getTrustEmailAddressPage: HttpRequestBuilder =
    http("[get ] What is email address for this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/email-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postTrustEmailAddressPage(email: String): HttpRequestBuilder =
    http("[post] What is email address for this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/email-address")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getTrustPhoneNumberPage: HttpRequestBuilder =
    http("[get ] What is the phone number for this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/phone-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postTrustPhoneNumberPage(phoneNumber: String): HttpRequestBuilder =
    http("[post] What is the phone number for this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/phone-number")
      .formParam("value", phoneNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getTrustMobileNumberPage: HttpRequestBuilder =
    http("[get ] What is the mobile number trust page")
      .get(cisContractorFrontendUrl + "/add/trust/mobile-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postTrustMobileNumberPage(mobileNumber: String): HttpRequestBuilder =
    http("[post] What is the mobile number trust page")
      .post(cisContractorFrontendUrl + "/add/trust/mobile-number")
      .formParam("value", mobileNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddTrustUTRPage: HttpRequestBuilder =
    http("[get ] Do you know the Unique Taxpayer Reference for this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/trust-has-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddTrustUTRPage(option: String): HttpRequestBuilder =
    http("[post] Do you know the Unique Taxpayer Reference for this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/trust-has-utr")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getTrustUTRPage: HttpRequestBuilder =
    http("[get ] Trust Unique Taxpayer Reference (UTR) page")
      .get(cisContractorFrontendUrl + "/add/trust/trust-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postTrustUTRPage(utr: String): HttpRequestBuilder =
    http("[post] Trust Unique Taxpayer Reference (UTR) page")
      .post(cisContractorFrontendUrl + "/add/trust/trust-utr")
      .formParam("value", utr)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddTrustWrnPage: HttpRequestBuilder =
    http("[get ] Is there a works reference number associated with this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/trust-has-works-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddTrustWrnPage(option: String): HttpRequestBuilder =
    http("[post] Is there a works reference number associated with this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/trust-has-works-reference")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getTrustWrnPage: HttpRequestBuilder =
    http("[get ] What is the works reference number associated with this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/trust-works-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postTrustWrnPage(option: String): HttpRequestBuilder =
    http("[post] What is the works reference number associated with this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/trust-works-reference")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getTrustCheckYourAnswersPage: HttpRequestBuilder =
    http("[get ] Check your answers before adding this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/check-your-answers")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postTrustCheckYourAnswersPage: HttpRequestBuilder =
    http("[post] Check your answers before adding this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/check-your-answers")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeTrustNamePage: HttpRequestBuilder =
    http("[get ] What is the partnership name page")
      .get(cisContractorFrontendUrl + "/add/trust/change-trust-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeTrustNamePage(trustName: String): HttpRequestBuilder =
    http("[post] What is the partnership name page")
      .post(cisContractorFrontendUrl + "/add/trust/change-trust-name")
      .formParam("value", trustName)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeAddTrustAddressPage: HttpRequestBuilder =
    http("[get ] Do you want to add an address for this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/change-check-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeAddTrustAddressPage(option: String): HttpRequestBuilder =
    http("[post] Do you want to add an address for this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/change-check-address")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeTrustAddressPage: HttpRequestBuilder =
    http("[get ] What is the address for this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/change-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeTrustAddressPage(
    addressLine1: String,
    addressLine2: String,
    town: String,
    county: String,
    postalCode: String,
    country: String
  ): HttpRequestBuilder =
    http("[post] What is the address for this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/change-address")
      .formParam("addressLine1", addressLine1)
      .formParam("addressLine2", addressLine2)
      .formParam("addressLine3", town)
      .formParam("addressLine4", county)
      .formParam("postalCode", postalCode)
      .formParam("country", country)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeTrustContactMethodPage: HttpRequestBuilder =
    http("[get ] What are the contact details for this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/change-choose-contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeTrustContactMethodPage(contactOption: String): HttpRequestBuilder =
    http("[post] What are the contact details for this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/change-choose-contact-details")
      .formParam("value", contactOption)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeTrustEmailAddressPage: HttpRequestBuilder =
    http("[get ] What is the email address for this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/change-email-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeTrustEmailAddressPage(email: String): HttpRequestBuilder =
    http("[post] What is the email address for this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/change-email-address")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeTrustPhoneNumberPage: HttpRequestBuilder =
    http("[get ] What is the phone number for this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/change-phone-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeTrustPhoneNumberPage(phoneNumber: String): HttpRequestBuilder =
    http("[post] What is the phone number for this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/change-phone-number")
      .formParam("value", phoneNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeTrustMobileNumberPage: HttpRequestBuilder =
    http("[get ] What is the mobile number for this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/change-mobile-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeTrustMobileNumberPage(mobileNumber: String): HttpRequestBuilder =
    http("[post] What is the mobile number for this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/change-mobile-number")
      .formParam("value", mobileNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeAddTrustUTRPage: HttpRequestBuilder =
    http("[get ] Do you know the Unique Taxpayer Reference for this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/change-partnership-has-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeAddTrustUTRPage(option: String): HttpRequestBuilder =
    http("[post] Do you know the Unique Taxpayer Reference for this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/change-partnership-has-utr")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeTrustUTRPage: HttpRequestBuilder =
    http("[get ] Trust Unique Taxpayer Reference (UTR) page")
      .get(cisContractorFrontendUrl + "/add/trust/change-partnership-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeTrustUTRPage(utr: String): HttpRequestBuilder =
    http("[post] Trust Unique Taxpayer Reference (UTR) page")
      .post(cisContractorFrontendUrl + "/add/trust/change-partnership-utr")
      .formParam("value", utr)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeAddTrustWrnPage: HttpRequestBuilder =
    http("[get ] Is there a works reference number associated with this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/change-trust-has-works-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeAddTrustWrnPage(option: String): HttpRequestBuilder =
    http("[post] Is there a works reference number associated with this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/change-trust-has-works-reference")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeTrustWrnPage: HttpRequestBuilder =
    http("[get ] What is the works reference number associated with this trust page")
      .get(cisContractorFrontendUrl + "/add/trust/change-trust-works-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeTrustWrnPage(worksRefNumber: String): HttpRequestBuilder =
    http("[post] What is the works reference number associated with this trust page")
      .post(cisContractorFrontendUrl + "/add/trust/change-trust-works-reference")
      .formParam("value", worksRefNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getTrustSubcontractorAddedPage: HttpRequestBuilder =
    http("[get ] Trust subcontractor added page")
      .get(cisContractorFrontendUrl + "/add/trust/subcontractor-added")
      .check(status.is(200))
}
