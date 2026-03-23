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

object AddPartnershipSubcontractorRequests extends ServicesConfiguration with CisPerformanceTestBase {

  val getPartnershipNamePage: HttpRequestBuilder =
    http("[get ] What is the partnership name page")
      .get(cisContractorFrontendUrl + "/add/partnership/partnership-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postPartnershipNamePage(partnershipName: String): HttpRequestBuilder =
    http("[post] What is the partnership name page")
      .post(cisContractorFrontendUrl + "/add/partnership/partnership-name")
      .formParam("value", partnershipName)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddPartnershipAddressPage: HttpRequestBuilder =
    http("[get ] Do you want to add an address for this subcontractor page")
      .get(cisContractorFrontendUrl + "/add/partnership/check-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddPartnershipAddressPage(option: String): HttpRequestBuilder =
    http("[post] Do you want to add an address for this subcontractor page")
      .post(cisContractorFrontendUrl + "/add/partnership/check-address")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getPartnershipAddressPage: HttpRequestBuilder =
      http("[get ] What is the address for this partnership page")
      .get(cisContractorFrontendUrl + "/add/partnership/address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postPartnershipAddressPage(addressLine1: String,
                                             addressLine2: String,
                                             town: String,
                                             county: String,
                                             postalCode: String,
                                             country: String
                                            ): HttpRequestBuilder =
    http("[post] What is the address for this partnership page")
      .post(cisContractorFrontendUrl + "/add/partnership/address")
      .formParam("addressLine1", addressLine1)
      .formParam("addressLine2", addressLine2)
      .formParam("addressLine3", town)
      .formParam("addressLine4", county)
      .formParam("postalCode", postalCode)
      .formParam("country", country)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getPartnershipContactMethodPage: HttpRequestBuilder =
    http("[get ] What is the contact method for this partnership page")
      .get(cisContractorFrontendUrl + "/add/partnership/choose-contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postPartnershipContactMethodPage(contactOption: String): HttpRequestBuilder =
    http("[post] What is the contact method for this partnership page")
      .post(cisContractorFrontendUrl + "/add/partnership/choose-contact-details")
      .formParam("value", contactOption)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getPartnershipEmailAddressPage: HttpRequestBuilder =
    http("[get ] What is the partnership email address page")
      .get(cisContractorFrontendUrl + "/add/partnership/email-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postPartnershipEmailAddressPage(email: String): HttpRequestBuilder =
    http("[post] What is the partnership email address page")
      .post(cisContractorFrontendUrl + "/add/partnership/email-address")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getPartnershipPhoneNumberPage: HttpRequestBuilder =
    http("[get ] What is the partnership phone number page")
      .get(cisContractorFrontendUrl + "/add/partnership/phone-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postPartnershipPhoneNumberPage(phoneNumber: String): HttpRequestBuilder =
    http("[post] What is the partnership phone number page")
      .post(cisContractorFrontendUrl + "/add/partnership/phone-number")
      .formParam("value", phoneNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getPartnershipMobileNumberPage: HttpRequestBuilder =
    http("[get ] What is the partnership mobile number page")
      .get(cisContractorFrontendUrl + "/add/partnership/mobile-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postPartnershipMobileNumberPage(mobileNumber: String): HttpRequestBuilder =
    http("[post] What is the partnership mobile number page")
      .post(cisContractorFrontendUrl + "/add/partnership/mobile-number")
      .formParam("value", mobileNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddPartnershipUTRPage: HttpRequestBuilder =
    http("[get ] Does this partnership have a UTR page")
      .get(cisContractorFrontendUrl + "/add/partnership/partnership-has-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddPartnershipUTRPage(option: String): HttpRequestBuilder =
    http("[post] Does this partnership have a UTR page")
      .post(cisContractorFrontendUrl + "/add/partnership/partnership-has-utr")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getPartnershipUTRPage: HttpRequestBuilder =
    http("[get ] What is the UTR for this partnership page")
      .get(cisContractorFrontendUrl + "/add/partnership/partnership-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postPartnershipUTRPage(utr: String): HttpRequestBuilder =
    http("[post] What is the UTR for this partnership page")
      .post(cisContractorFrontendUrl + "/add/partnership/partnership-utr")
      .formParam("value", utr)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getNominatedPartnerName: HttpRequestBuilder =
    http("[get ] Who is the nominated partner for this partnership")
      .get(cisContractorFrontendUrl + "/add/partnership/nominated-partner")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postNominatedPartnerName(nominatedPartnerName:String): HttpRequestBuilder =
    http("[post] Who is the nominated partner for this partnership")
      .post(cisContractorFrontendUrl + "/add/partnership/nominated-partner")
      .formParam("value", nominatedPartnerName)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddNominatedPartnerUTRPage: HttpRequestBuilder =
    http("[get ] Do you know the UTR for the nominated partner page")
      .get(cisContractorFrontendUrl + "/add/partnership/nominated-partner-has-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddNominatedPartnerUTRPage(option: String): HttpRequestBuilder =
    http("[post] Do you know the UTR for the nominated partner page")
      .post(cisContractorFrontendUrl + "/add/partnership/nominated-partner-has-utr")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getNominatedPartnerUTRPage: HttpRequestBuilder =
    http("[get ] What is the Self Assessment UTR for this nominated partner page")
      .get(cisContractorFrontendUrl + "/add/partnership/nominated-partner-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postNominatedPartnerUTRPage(nominatedPartnerUtr: String): HttpRequestBuilder =
    http("[post] What is the Self Assessment UTR for this nominated partner page")
      .post(cisContractorFrontendUrl + "/add/partnership/nominated-partner-utr")
      .formParam("value", nominatedPartnerUtr)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddNominatedPartnerNinoPage: HttpRequestBuilder =
    http("[get ] Do you know the National Insurance number for the nominated partner page")
      .get(cisContractorFrontendUrl + "/add/partnership/nominated-partner-has-national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddNominatedPartnerNinoPage(option: String): HttpRequestBuilder =
    http("[post] Do you know the National Insurance number for the nominated partner page")
      .post(cisContractorFrontendUrl + "/add/partnership/nominated-partner-has-national-insurance-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getNominatedPartnerNinoPage: HttpRequestBuilder =
    http("[get ] What is the National Insurance number for this nominated partner page")
      .get(cisContractorFrontendUrl + "/add/partnership/nominated-partner-national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postNominatedPartnerNinoPage(nominatedPartnerNino: String): HttpRequestBuilder =
    http("[post] What is the National Insurance number for this nominated partner page")
      .post(cisContractorFrontendUrl + "/add/partnership/nominated-partner-national-insurance-number")
      .formParam("value", nominatedPartnerNino)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddNominatedPartnerCrnPage: HttpRequestBuilder =
    http("[get ] Does nominated partner have a company registration number page")
      .get(cisContractorFrontendUrl + "/add/partnership/nominated-partner-has-company-registration-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddNominatedPartnerCrnPage(option: String): HttpRequestBuilder =
    http("[post] Does nominated partner have a company registration number page")
      .post(cisContractorFrontendUrl + "/add/partnership/nominated-partner-has-company-registration-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getNominatedPartnerCrnPage: HttpRequestBuilder =
    http("[get ] What is the company registration number for this nominated partner page")
      .get(cisContractorFrontendUrl + "/add/partnership/nominated-partner-company-registration-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postNominatedPartnerCrnPage(nominatedPartnerCrn: String): HttpRequestBuilder =
    http("[post] What is the company registration number for this nominated partner page")
      .post(cisContractorFrontendUrl + "/add/partnership/nominated-partner-company-registration-number")
      .formParam("value", nominatedPartnerCrn)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddPartnershipWrnPage: HttpRequestBuilder =
    http("[get ] Does this partnership have a works reference number page")
      .get(cisContractorFrontendUrl + "/add/partnership/partnership-has-works-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postAddPartnershipWrnPage(option: String): HttpRequestBuilder =
    http("[post] Does this partnership have a works reference number page")
      .post(cisContractorFrontendUrl + "/add/partnership/partnership-has-works-reference")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getPartnershipWrnPage: HttpRequestBuilder =
    http("[get ] What is the works reference number for this partnership page")
      .get(cisContractorFrontendUrl + "/add/partnership/partnership-works-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postPartnershipWrnPage(worksRefNumber: String): HttpRequestBuilder =
    http("[post] What is the works reference number for this partnership page")
      .post(cisContractorFrontendUrl + "/add/partnership/partnership-works-reference")
      .formParam("value", worksRefNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getPartnershipCheckYourAnswersPage: HttpRequestBuilder =
    http("[get ] Check your answers before adding this partnership page")
      .get(cisContractorFrontendUrl + "/add/partnership/check-your-answers")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postPartnershipCheckYourAnswersPage: HttpRequestBuilder =
    http("[post] Check your answers before adding this partnership page")
      .post(cisContractorFrontendUrl + "/add/partnership/check-your-answers")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  //TODO Update change links urls

  val getUpdatePartnershipNamePage: HttpRequestBuilder =
    http("[get ] What is the partnership name page")
      .get(cisContractorFrontendUrl + "/add/partnership/partnership-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdatePartnershipNamePage(partnershipName: String): HttpRequestBuilder =
    http("[post] What is the partnership name page")
      .post(cisContractorFrontendUrl + "/add/partnership/partnership-name")
      .formParam("value", partnershipName)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdateAddPartnershipAddressPage: HttpRequestBuilder =
    http("[get ] Do you want to add an address for this subcontractor page")
      .get(cisContractorFrontendUrl + "/add/partnership/check-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdateAddPartnershipAddressPage(option: String): HttpRequestBuilder =
    http("[post] Do you want to add an address for this subcontractor page")
      .post(cisContractorFrontendUrl + "/add/partnership/check-address")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdatePartnershipAddressPage: HttpRequestBuilder =
    http("[get ] What is the address for this partnership page")
      .get(cisContractorFrontendUrl + "/add/partnership/address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdatePartnershipAddressPage(addressLine1: String,
                                 addressLine2: String,
                                 town: String,
                                 county: String,
                                 postalCode: String,
                                 country: String
                                ): HttpRequestBuilder =
    http("[post] What is the address for this partnership page")
      .post(cisContractorFrontendUrl + "/add/partnership/address")
      .formParam("addressLine1", addressLine1)
      .formParam("addressLine2", addressLine2)
      .formParam("addressLine3", town)
      .formParam("addressLine4", county)
      .formParam("postalCode", postalCode)
      .formParam("country", country)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdatePartnershipContactMethodPage: HttpRequestBuilder =
    http("[get ] What is the contact method for this partnership page")
      .get(cisContractorFrontendUrl + "/add/partnership/choose-contact-details")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdatePartnershipContactMethodPage(contactOption: String): HttpRequestBuilder =
    http("[post] What is the contact method for this partnership page")
      .post(cisContractorFrontendUrl + "/add/partnership/choose-contact-details")
      .formParam("value", contactOption)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdatePartnershipEmailAddressPage: HttpRequestBuilder =
    http("[get ] What is the partnership email address page")
      .get(cisContractorFrontendUrl + "/add/partnership/email-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdatePartnershipEmailAddressPage(email: String): HttpRequestBuilder =
    http("[post] What is the partnership email address page")
      .post(cisContractorFrontendUrl + "/add/partnership/email-address")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdatePartnershipPhoneNumberPage: HttpRequestBuilder =
    http("[get ] What is the partnership phone number page")
      .get(cisContractorFrontendUrl + "/add/partnership/phone-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdatePartnershipPhoneNumberPage(phoneNumber: String): HttpRequestBuilder =
    http("[post] What is the partnership phone number page")
      .post(cisContractorFrontendUrl + "/add/partnership/phone-number")
      .formParam("value", phoneNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdatePartnershipMobileNumberPage: HttpRequestBuilder =
    http("[get ] What is the partnership mobile number page")
      .get(cisContractorFrontendUrl + "/add/partnership/mobile-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdatePartnershipMobileNumberPage(mobileNumber: String): HttpRequestBuilder =
    http("[post] What is the partnership mobile number page")
      .post(cisContractorFrontendUrl + "/add/partnership/mobile-number")
      .formParam("value", mobileNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdateAddPartnershipUTRPage: HttpRequestBuilder =
    http("[get ] Does this partnership have a UTR page")
      .get(cisContractorFrontendUrl + "/add/partnership/partnership-has-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdateAddPartnershipUTRPage(option: String): HttpRequestBuilder =
    http("[post] Does this partnership have a UTR page")
      .post(cisContractorFrontendUrl + "/add/partnership/partnership-has-utr")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdatePartnershipUTRPage: HttpRequestBuilder =
    http("[get ] What is the UTR for this partnership page")
      .get(cisContractorFrontendUrl + "/add/partnership/partnership-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdatePartnershipUTRPage(utr: String): HttpRequestBuilder =
    http("[post] What is the UTR for this partnership page")
      .post(cisContractorFrontendUrl + "/add/partnership/partnership-utr")
      .formParam("value", utr)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdateNominatedPartnerName: HttpRequestBuilder =
    http("[get ] Who is the nominated partner for this partnership")
      .get(cisContractorFrontendUrl + "/add/partnership/nominated-partner")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdateNominatedPartnerName(nominatedPartnerName:String): HttpRequestBuilder =
    http("[post] Who is the nominated partner for this partnership")
      .post(cisContractorFrontendUrl + "/add/partnership/nominated-partner")
      .formParam("value", nominatedPartnerName)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdateAddNominatedPartnerUTRPage: HttpRequestBuilder =
    http("[get ] Do you know the UTR for the nominated partner page")
      .get(cisContractorFrontendUrl + "/add/partnership/nominated-partner-has-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdateAddNominatedPartnerUTRPage(option: String): HttpRequestBuilder =
    http("[post] Do you know the UTR for the nominated partner page")
      .post(cisContractorFrontendUrl + "/add/partnership/nominated-partner-has-utr")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdateNominatedPartnerUTRPage: HttpRequestBuilder =
    http("[get ] What is the Self Assessment UTR for this nominated partner page")
      .get(cisContractorFrontendUrl + "/add/partnership/nominated-partner-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdateNominatedPartnerUTRPage(nominatedPartnerUtr: String): HttpRequestBuilder =
    http("[post] What is the Self Assessment UTR for this nominated partner page")
      .post(cisContractorFrontendUrl + "/add/partnership/nominated-partner-utr")
      .formParam("value", nominatedPartnerUtr)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdateAddNominatedPartnerNinoPage: HttpRequestBuilder =
    http("[get ] Do you know the National Insurance number for the nominated partner page")
      .get(cisContractorFrontendUrl + "/add/partnership/nominated-partner-has-national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdateAddNominatedPartnerNinoPage(option: String): HttpRequestBuilder =
    http("[post] Do you know the National Insurance number for the nominated partner page")
      .post(cisContractorFrontendUrl + "/add/partnership/nominated-partner-has-national-insurance-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdateNominatedPartnerNinoPage: HttpRequestBuilder =
    http("[get ] What is the National Insurance number for this nominated partner page")
      .get(cisContractorFrontendUrl + "/add/partnership/nominated-partner-national-insurance-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdateNominatedPartnerNinoPage(nominatedPartnerNino: String): HttpRequestBuilder =
    http("[post] What is the National Insurance number for this nominated partner page")
      .post(cisContractorFrontendUrl + "/add/partnership/nominated-partner-national-insurance-number")
      .formParam("value", nominatedPartnerNino)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdateAddNominatedPartnerCrnPage: HttpRequestBuilder =
    http("[get ] Does nominated partner have a company registration number page")
      .get(cisContractorFrontendUrl + "/add/partnership/nominated-partner-has-company-registration-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdateAddNominatedPartnerCrnPage(option: String): HttpRequestBuilder =
    http("[post] Does nominated partner have a company registration number page")
      .post(cisContractorFrontendUrl + "/add/partnership/nominated-partner-has-company-registration-number")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdateNominatedPartnerCrnPage: HttpRequestBuilder =
    http("[get ] What is the company registration number for this nominated partner page")
      .get(cisContractorFrontendUrl + "/add/partnership/nominated-partner-company-registration-number")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdateNominatedPartnerCrnPage(nominatedPartnerCrn: String): HttpRequestBuilder =
    http("[post] What is the company registration number for this nominated partner page")
      .post(cisContractorFrontendUrl + "/add/partnership/nominated-partner-company-registration-number")
      .formParam("value", nominatedPartnerCrn)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdateAddPartnershipWrnPage: HttpRequestBuilder =
    http("[get ] Does this partnership have a works reference number page")
      .get(cisContractorFrontendUrl + "/add/partnership/partnership-has-works-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdateAddPartnershipWrnPage(option: String): HttpRequestBuilder =
    http("[post] Does this partnership have a works reference number page")
      .post(cisContractorFrontendUrl + "/add/partnership/partnership-has-works-reference")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdatePartnershipWrnPage: HttpRequestBuilder =
    http("[get ] What is the works reference number for this partnership page")
      .get(cisContractorFrontendUrl + "/add/partnership/partnership-works-reference")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postUpdatePartnershipWrnPage(worksRefNumber: String): HttpRequestBuilder =
    http("[post] What is the works reference number for this partnership page")
      .post(cisContractorFrontendUrl + "/add/partnership/partnership-works-reference")
      .formParam("value", worksRefNumber)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUpdatePartnershipCheckYourAnswersPage: HttpRequestBuilder =
    http("[get ] Check your answers before adding this partnership page")
      .get(cisContractorFrontendUrl + "/add/partnership/check-your-answers")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postUpdatePartnershipCheckYourAnswersPage: HttpRequestBuilder =
    http("[post] Check your answers before adding this partnership page")
      .post(cisContractorFrontendUrl + "/add/partnership/check-your-answers")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))
}
