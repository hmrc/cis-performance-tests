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

object ManageContractorDetailsRequests extends ServicesConfiguration with CisPerformanceTestBase {

  val getContractorDetailsRedirect: HttpRequestBuilder =
    http("[get ] Contractor details redirect")
      .get(cisManageFrontendUrl + "/agent/manage-construction-industry-scheme-account/800/target/contractorDetails")
      .check(status.is(303))

  val getContractorDetailsManageRedirect: HttpRequestBuilder =
    http("[get ] Contractor details manage redirect")
      .get(cisManageContractorUrl + "/contractor-details/manage")
      .check(status.is(303))

  val getContractorDetailsInfoPage: HttpRequestBuilder =
    http("[get ] Contractor details page")
      .get(cisManageContractorUrl + "/contractor-details/contractor-details-added")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postContractorDetailsInfoPage: HttpRequestBuilder =
    http("[post] Contractor details page")
      .post(cisManageContractorUrl + "/contractor-details/contractor-details-added")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeContractorUTR: HttpRequestBuilder =
    http("[get ] Your contractor's Unique Taxpayer Reference")
      .get(cisManageContractorUrl + "/contractor-details/change-contractor-utr")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeContractorUTR(utr: String): HttpRequestBuilder =
    http("[post] Your contractor's Unique Taxpayer Reference")
      .post(cisManageContractorUrl + "/contractor-details/change-contractor-utr")
      .formParam("value", utr)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeContractorSchemeName: HttpRequestBuilder =
    http("[get ] What is your contractor's scheme name?")
      .get(cisManageContractorUrl + "/contractor-details/change-contractor-scheme-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeContractorSchemeName(name: String): HttpRequestBuilder =
    http("[post] What is your contractor's scheme name?")
      .post(cisManageContractorUrl + "/contractor-details/change-contractor-scheme-name")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getRemoveContractorSchemeNameOption: HttpRequestBuilder =
    http("[get ] Are you sure you want to remove the contractor's scheme name?")
      .get(cisManageContractorUrl + "/contractor-details/remove-contractor-detail/scheme-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postRemoveContractorSchemeNameOption(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to remove the contractor's scheme name?")
      .post(cisManageContractorUrl + "/contractor-details/remove-contractor-detail/scheme-name")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getChangeContractorEmailAddress: HttpRequestBuilder =
    http("[get ] What is your contractor's email address?")
      .get(cisManageContractorUrl + "/contractor-details/change-contractor-email-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postChangeContractorEmailAddress(email: String): HttpRequestBuilder =
    http("[post] What is your contractor's email address?")
      .post(cisManageContractorUrl + "/contractor-details/change-contractor-email-address")
      .formParam("value", email)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getRemoveContractorEmailOption: HttpRequestBuilder =
    http("[get ] Are you sure you want to remove the contractor’s email address?")
      .get(cisManageContractorUrl + "/contractor-details/remove-contractor-detail/email-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postRemoveContractorEmailOption(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to remove the contractor’s email address?")
      .post(cisManageContractorUrl + "/contractor-details/remove-contractor-detail/email-address")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getContractorDetailsUpdated: HttpRequestBuilder =
    http("[get ] Contractor details updated")
      .get(cisManageContractorUrl + "/contractor-details/contractor-details-updated")
      .check(status.is(200))
}
