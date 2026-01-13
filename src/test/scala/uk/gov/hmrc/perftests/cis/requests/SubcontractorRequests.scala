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

object SubcontractorRequests extends ServicesConfiguration with CisPerformanceTestBase {

  val getWhatTypeOfSubcontractorAreYouAdding: HttpRequestBuilder =
    http("[get] What Type Of Subcontractor Are You Adding page")
      .get(cisContractorFrontendUrl + "/type-of-subcontractor")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatTypeOfSubcontractorAreYouAdding(option: String): HttpRequestBuilder =
    http("[post] What Type of Subcontractor Are You Adding page")
      .post(cisContractorFrontendUrl + "/type-of-subcontractor")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDoesTheSubcontractorUseATradingName: HttpRequestBuilder =
    http("[get] Does The Subcontractor Use A Trading Name page")
      .get(cisContractorFrontendUrl + "/check-trading-name")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoesTheSubcontractorUseATradingName(option: String): HttpRequestBuilder =
    http("[post] Does The Subcontractor Use A Trading Name page")
      .post(cisContractorFrontendUrl + "/check-trading-name")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getWhatIsTheSubcontractorsTradingName: HttpRequestBuilder =
    http("[get] What Is The Subcontractor's Trading Name page")
      .get(cisContractorFrontendUrl + "/monthly-return/confirm-email-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatIsTheSubcontractorsTradingName(name: String): HttpRequestBuilder =
    http("[post] Confirmation Email Address page")
      .post(cisFrontendUrl + "/trading-name")
      .formParam("value", name)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getDoYouWantToAddTheSubcontractorsAddress: HttpRequestBuilder =
    http("[get] Do You Want To Add The Subcontractor's Address page")
      .get(cisContractorFrontendUrl + "/check-address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postDoYouWantToAddTheSubcontractorsAddress(option: String): HttpRequestBuilder =
    http("[post] Do You Want To Add The Subcontractor's Address page")
      .post(cisContractorFrontendUrl + "/check-address")
      .formParam("value", option)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))

  val getWhatIsTheSubcontractorsAddress: HttpRequestBuilder =
    http("[get] What Is The Subcontractor's Address page")
      .get(cisContractorFrontendUrl + "/address")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  def postWhatIsTheSubcontractorsAddress(
    line1: String,
    line2: String,
    town: String,
    country: String,
    postcode: String
  ): HttpRequestBuilder =
    http("[post] What Is The Subcontractor's Address page")
      .post(cisFrontendUrl + "/monthly-return/date-confirm-nil-payments")
      .formParam("value.line1", line1)
      .formParam("value.line2", line2)
      .formParam("value.town", town)
      .formParam("value.country", country)
      .formParam("value.postcode", postcode)
      .formParam("csrfToken", f"#{csrfToken}")
      .check(status.is(303))
}
