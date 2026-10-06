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

object ManageClientDetailsRequests extends ServicesConfiguration with CisPerformanceTestBase {
   val getClientDetails: HttpRequestBuilder =
     http("[get ] Client details")
       .get(cisManageFrontendUrl + "/client-details/manage-client-details")
       .check(status.is(200))

  val getWhatIsYourClientReference: HttpRequestBuilder =
    http("[get ] What is your client reference?")
      .get(cisManageFrontendUrl + "/client-details/change-client-reference/800")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatIsYourClientReference(reference: String): HttpRequestBuilder =
    http("[post] What is your client reference?")
      .post(cisManageFrontendUrl + "/client-details/change-client-reference/800")
      .formParam("value", reference)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getClientReferenceUpdated: HttpRequestBuilder =
    http("[get ] Client reference updated")
      .get(cisManageFrontendUrl + "/client-details/client-reference-updated")
      .check(status.is(200))

  val getRemoveClientOptionPage: HttpRequestBuilder =
    http("[get ] Are you sure you want to remove client?")
      .get(cisManageFrontendUrl + "/client-details/remove-client/800")
      .check(status.is(200))
      .formParam("csrfToken", f"#{csrfToken}")

  def postRemoveClientOptionPage(option: String): HttpRequestBuilder =
    http("[post] Are you sure you want to remove client?")
      .post(cisManageFrontendUrl + "/client-details/remove-client/800")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getClientRemoved: HttpRequestBuilder =
    http("[get ] Client removed")
      .get(cisManageFrontendUrl + "/client-details/client-removed")
      .check(status.is(200))
}
