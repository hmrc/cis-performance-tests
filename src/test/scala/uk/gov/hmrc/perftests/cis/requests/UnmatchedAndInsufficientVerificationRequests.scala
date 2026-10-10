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

object UnmatchedAndInsufficientVerificationRequests extends ServicesConfiguration with CisPerformanceTestBase {
  val getUnmatchedSubcontractorExist: HttpRequestBuilder =
    http("[get ] You have unmatched subcontractors")
      .get(cisContractorFrontendUrl + "/verify/unmatched-subcontractors-exist")
      .check(status.is(200))

  val getCheckResultsRedirect: HttpRequestBuilder =
    http("[get ] Check results redirect")
      .get(cisContractorFrontendUrl + "/verify/check-results")
      .check(status.is(303))

  val getVerificationResults: HttpRequestBuilder =
    http("[get ] Verification results")
      .get(cisContractorFrontendUrl + "/verify/verification-results")
      .check(status.is(200))

  val getUnmatchedSubcontractorsRedirect: HttpRequestBuilder =
    http("[get ] Get unmatched subcontractors and redirect")
      .get(cisContractorFrontendUrl + "/verify/unmatched-subcontractors")
      .check(status.is(303))

  val getReviewUnmatchedSubcontractorsPage: HttpRequestBuilder =
    http("[get ] Review unmatched subcontractors")
      .get(cisContractorFrontendUrl + "/verify/review-unmatched-subcontractors")
      .check(status.is(200))

  def getUnmatchedSubcontractorInfoRedirect(subbieRef: String): HttpRequestBuilder =
    http("[get ] Retrieve subcontractor info and redirect")
      .get(cisContractorFrontendUrl + s"/info/start/$subbieRef/unmatched")
      .check(status.is(303))

  val getUnmatchedSubcontractorInfoReadOnly: HttpRequestBuilder =
    http("[get ] Subcontractor information (Read only)")
      .get(cisContractorFrontendUrl + "/info/subcontractor-information-read-only/unmatched")
      .check(status.is(200))

  def getEditUnmatchedSubcontractorInfoRedirect(subbieRef: String): HttpRequestBuilder =
    http("[get ] Retrieve subcontractor info for edit and redirect")
      .get(cisContractorFrontendUrl + s"/amend/start/$subbieRef/unmatched")
      .check(status.is(303))

  val getProceedUnmatchedOptionPage: HttpRequestBuilder =
    http("[get ] Are you sure you want to proceed with verifying subcontractor")
      .get(cisContractorFrontendUrl + "/proceed-verifying-unmatched-subcontractor/10")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postProceedUnmatchedOptionPage(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to proceed with verifying subcontractor")
      .post(cisContractorFrontendUrl + "/proceed-verifying-unmatched-subcontractor/10")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getRemoveUnmatchedOptionPage: HttpRequestBuilder =
    http("[get ] Are you sure you want to remove subcontractor from this verification request?")
      .get(cisContractorFrontendUrl + "/remove-unmatched-subcontractor-from-request/10")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postRemoveUnmatchedOptionPage(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to proceed with verifying subcontractor")
      .post(cisContractorFrontendUrl + "/remove-unmatched-subcontractor-from-request/10")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val postVerifyWhichInsufficientSubcontractor: HttpRequestBuilder =
    http("[post] Which subcontractors to Verify page (Page 1 of 2)")
      .post(cisContractorFrontendUrl + "/verify/select-subcontractors-to-verify")
      .formParam("csrfToken", f"#{csrfToken}")
      .formParam("value[2]", "#{checkboxValues(2)}")
      .check(status.is(303))

  val getVerifyWhichSubcontractorPage2: HttpRequestBuilder =
    http("[get ] Which subcontractors to Verify page (Page 2 of 2)")
      .get(cisContractorFrontendUrl + "/verify/select-subcontractors-to-verify")
      .queryParam("page", "2")
      .check(status.is(200))
      .check(css("input.govuk-checkboxes__input", "value").findAll.saveAs("checkboxValues"))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postVerifyWhichInsufficientSubcontractorPage2: HttpRequestBuilder =
    http("[post] Which subcontractors to Verify page (Page 2 of 2)")
      .post(cisContractorFrontendUrl + "/verify/select-subcontractors-to-verify")
      .queryParam("page", "2")
      .formParam("csrfToken", f"#{csrfToken}")
      .formParam("value[7]", "#{checkboxValues(7)}")
      .formParam("value[5]", "#{checkboxValues(5)}")
      .check(status.is(303))

  val getSubcontractorHaveMissingInformation: HttpRequestBuilder =
    http("[get ] Cannot verify all subcontractors")
      .get(cisContractorFrontendUrl + "/verify/subcontractors-have-missing-information")
      .check(status.is(200))

  def getInsufficientSubcontractorInfoRedirect(subbieRef: String): HttpRequestBuilder =
    http("[get ] Retrieve subcontractor info and redirect")
      .get(cisContractorFrontendUrl + s"/info/start/$subbieRef/insufficient")
      .check(status.is(303))

  val getInsufficientSubcontractorInfoReadOnly: HttpRequestBuilder =
    http("[get ] Insufficient subcontractor information (Read only)")
      .get(cisContractorFrontendUrl + "/info/subcontractor-information-read-only/insufficient")
      .check(status.is(200))

  def getEditInsufficientSubcontractorInfoRedirect(subbieRef: String): HttpRequestBuilder =
    http("[get ] Retrieve subcontractor info for edit and redirect")
      .get(cisContractorFrontendUrl + s"/amend/start/$subbieRef/insufficient")
      .check(status.is(303))

  val getInsufficientOptionPage: HttpRequestBuilder =
    http("[get ] Are you sure you want to proceed with including subcontractor in this verification request?")
      .get(cisContractorFrontendUrl + "/proceed-verifying-subcontractor/1")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postProceedInsufficientOptionPage(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to proceed with including subcontractor in this verification request?")
      .post(cisContractorFrontendUrl + "/proceed-verifying-subcontractor/1")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getRemoveInsufficientOptionPage: HttpRequestBuilder =
    http("[get ] Are you sure you want to remove subcontractor from this verification request?")
      .get(cisContractorFrontendUrl + "/remove-subcontractor-from-request")
      .queryParam("verificationResourceRef", "10")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postRemoveInsufficientOptionPage(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to proceed with verifying subcontractor")
      .post(cisContractorFrontendUrl + "/remove-subcontractor-from-request")
      .queryParam("verificationResourceRef", "10")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

}
