/*
 * Copyright 2023 HM Revenue & Customs
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

object PrepopulationRequests extends ServicesConfiguration with CisPerformanceTestBase {

  def getCheckSubcontractorRecordsPage(taxOfficeReference: String): HttpRequestBuilder =
    http(s"[get ] Check subcontractor records page")
      .get(cisManageFrontendUrl + s"/check-subcontractor-records/754/$taxOfficeReference/1/subcontractors")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postCheckSubcontractorRecordsPage(taxOfficeReference: String): HttpRequestBuilder =
    http(s"[post] Check subcontractor records page")
      .post(cisManageFrontendUrl + s"/check-subcontractor-records/754/$taxOfficeReference/1/subcontractors")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  def getYourSubcontractors(taxOfficeReference: String): HttpRequestBuilder =
    http("[get ] Your subcontractors")
      .get(cisManageFrontendUrl + s"/your-subcontractors/754/$taxOfficeReference/1/subcontractors")
      .check(status.is(200))

  def getStartPrepopulation(taxOfficeReference: String): HttpRequestBuilder =
    http("[get ] Start prepopulation")
      .get(cisManageFrontendUrl + s"/your-subcontractors/start/754/$taxOfficeReference/1/subcontractors/start")
      .check(status.is(303))

  val getSuccessfulAutomaticSubcontractorUpdatePage: HttpRequestBuilder =
    http("[get ] Successful automatic subcontractor update page")
      .get(cisManageFrontendUrl + "/automatic-subcontractor-update-successful/1/subcontractors")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postSuccessfulAutomaticSubcontractorUpdatePage: HttpRequestBuilder =
    http("[post] Successful automatic subcontractor update page")
      .post(cisManageFrontendUrl + "/automatic-subcontractor-update-successful/1/subcontractors")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getUnsuccessfulAutomaticSubcontractorUpdatePage: HttpRequestBuilder =
    http("[get ] Unsuccessful automatic subcontractor update page")
      .get(cisManageFrontendUrl + "/automatic-subcontractor-update-unsuccessful/1")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postUnsuccessfulAutomaticSubcontractorUpdatePage: HttpRequestBuilder =
    http("[post] Unsuccessful automatic subcontractor update page")
      .post(cisManageFrontendUrl + "/automatic-subcontractor-update-unsuccessful/1")
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getAddContractorDetailsPage: HttpRequestBuilder =
    http("[get ] Add contractor details page")
      .get(cisManageFrontendUrl + "/add-contractor-details")
      .check(status.is(200))
}
