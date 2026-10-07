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

object DeleteSubcontractorRequests extends ServicesConfiguration with CisPerformanceTestBase {

  val getRetrieveSubcontractorList: HttpRequestBuilder =
    http("[get ] Retrieve subcontractor list")
      .get(cisManageFrontendUrl + "/subcontractors/retrieve")
      .check(status.is(303))

  val getYourSubcontractorListPage: HttpRequestBuilder =
    http("[get ] Your subcontractors")
      .get(cisManageFrontendUrl + "/subcontractors/800/your-subcontractors")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val getYourSubcontractorListPage2: HttpRequestBuilder =
    http("[get ] Your subcontractors (page 2 of 2)")
      .get(cisManageFrontendUrl + "/subcontractors/800/your-subcontractors")
      .queryParam("page", "2")
      .queryParam("sortBy", "name")
      .queryParam("sortOrder", "ascending")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def getDeleteStatus(id: String): HttpRequestBuilder =
    http("[get ] Delete status")
      .get(cisManageFrontendUrl + s"/subcontractors/$id/delete-status")
      .check(status.is(303))

  val getCannotDeletePage: HttpRequestBuilder =
    http("[get ] Cannot delete subcontractor")
      .get(cisManageFrontendUrl + "/subcontractors/cannot-delete-subcontractor-warning")
      .check(status.is(200))

  val getDeleteSubcontractorOption: HttpRequestBuilder =
    http("[get ] Are you sure you want to delete subcontractor?")
      .get(cisManageFrontendUrl + "/subcontractors/delete-subcontractor/2")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDeleteSubcontractorOption(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to delete subcontractor?")
      .post(cisManageFrontendUrl + "/subcontractors/delete-subcontractor/2")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getSubcontractorDeleted: HttpRequestBuilder =
    http("[get ] Subcontractor deleted")
      .get(cisManageFrontendUrl + "/subcontractors/subcontractor-deleted")
      .check(status.is(200))
}
