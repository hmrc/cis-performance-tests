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

object VerifySubcontractorRequests extends ServicesConfiguration with CisPerformanceTestBase {

  val getVerifySubcontractor: HttpRequestBuilder =
    http("[get ] Verify Subcontractors")
      .get(cisContractorFrontendUrl + "/add/verify/newest")
      .check(status.is(303))

  val getVerifyWhichSubcontractorPage: HttpRequestBuilder =
    http("[get ] Which subcontractors to Verify page")
      .get(cisContractorFrontendUrl + "/add/verify/select-subcontractors-to-verify")
      .check(status.is(200))
      .check(css("input.govuk-checkboxes__input", "value").findAll.saveAs("checkboxValues"))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postVerifyWhichSubcontractorPage(companyName: String): HttpRequestBuilder =
    http("[post] Which subcontractors to Verify page")
      .post(cisContractorFrontendUrl + "/add/verify/select-subcontractors-to-verify")
      .formParam("csrfToken", f"#{csrfToken}")
      .formParam("value[0]", "#{checkboxValues(0)}")
      .formParam("value[1]", "#{checkboxValues(1)}")
      .formParam("value[2]", "#{checkboxValues(2)}")
      .formParam("value[3]", "#{checkboxValues(3)}")
      .formParam("value[4]", "#{checkboxValues(4)}")
      .formParam("value[5]", "#{checkboxValues(5)}")
      .check(status.is(303))

  val getReverifyExistingSubcontractorPage: HttpRequestBuilder =
    http("[get ] Reverify existing subcontractors")
      .get(cisContractorFrontendUrl + "/add/verify/reverify-existing-subcontractors")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postReverifyExistingSubcontractorPage(companyName: String): HttpRequestBuilder =
    http("[post] Reverify existing subcontractors")
      .post(cisContractorFrontendUrl + "/add/verify/reverify-existing-subcontractors")
      .formParam("csrfToken", f"#{csrfToken}")
      .formParam("value", "true")
      .check(status.is(303))

  val getReverifySelectSubcontractorPage: HttpRequestBuilder =
    http("[get ] Which subcontractors to ReVerify page")
      .get(cisContractorFrontendUrl + "/add/verify/select-subcontractors-to-reverify")
      .check(status.is(200))
      .check(css("input.govuk-checkboxes__input", "value").findAll.saveAs("checkboxValues"))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postReverifySelectSubcontractorPage(companyName: String): HttpRequestBuilder =
    http("[post] Which subcontractors to ReVerify page")
      .post(cisContractorFrontendUrl + "/add/verify/select-subcontractors-to-reverify")
      .formParam("csrfToken", f"#{csrfToken}")
      .formParam("value[0]", "#{checkboxValues(0)}")
      .formParam("value[1]", "#{checkboxValues(1)}")
      .check(status.is(303))

  val getCurrentSubcontractorsToVerify: HttpRequestBuilder =
    http("[get ] Current subcontractors batch to verify request")
      .get(cisContractorFrontendUrl + "/verify/current")
      .check(status.is(303))

  val getModifySubcontractorsToVerify: HttpRequestBuilder =
    http("[get ] Modify subcontractors batch to verify request")
      .get(cisContractorFrontendUrl + "/verify/verification-batch/modify")
      .check(status.is(303))

  val getCheckVerificationBatchReadiness: HttpRequestBuilder =
    http("[get ] Check Verification Batch Readiness")
      .get(cisContractorFrontendUrl + "/verify/check-verification-batch-readiness")
      .check(status.is(303))

  val getVerifyEmailConfirmationPage: HttpRequestBuilder =
    http("[get ] Want email confirmation of this verification request")
      .get(cisContractorFrontendUrl + "/verify/confirmation-email-stored")
      .check(status.is(200))

  def postVerifyEmailConfirmationPage(companyName: String): HttpRequestBuilder =
    http("[post] Which subcontractors to ReVerify page")
      .post(cisContractorFrontendUrl + "/verify/confirmation-email-stored")
      .formParam("csrfToken", f"#{csrfToken}")
      .formParam("value", "differentEmail")
      .check(status.is(303))

  val getVerifyEnterEmailConfirmationPage: HttpRequestBuilder =
    http("[get ] What email address for confirmation for verification request")
      .get(cisFrontendUrl + "/verify/enter-confirmation-email")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postVerifyEnterEmailConfirmationPage(email: String): HttpRequestBuilder =
    http("[post] What email address for confirmation for verification request")
      .post(cisFrontendUrl + "/verify/enter-confirmation-email")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getVerificationCheckYourAnswersPage: HttpRequestBuilder =
    http("[get ] Get Verification Check your answers page")
      .get(cisFrontendUrl + "/verify/check-your-answers")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postVerificationCheckYourAnswersPage: HttpRequestBuilder =
    http("[post] Post Verification Check your answers")
      .post(cisFrontendUrl + "/verify/check-your-answers")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))
}
