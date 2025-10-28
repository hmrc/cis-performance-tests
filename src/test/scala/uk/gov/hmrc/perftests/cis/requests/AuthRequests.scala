/*
 * Copyright 2025 HM Revenue & Customs
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

object AuthRequests extends ServicesConfiguration with CisPerformanceTestBase {

  val getAuthPage: HttpRequestBuilder = http("[get] gg-sign-in")
    .get(baseUrlAuthLoginStub + "/auth-login-stub/gg-sign-in")
    .disableFollowRedirect
    .check(status.is(200))
    .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val getSession: HttpRequestBuilder = http("[get] auth session")
    .get(baseUrlAuthLoginStub + "/auth-login-stub/session")
    .check(status.is(200))
    .check(css("[data-session-id=\"authToken\"] > code").saveAs("accessToken"))
    .check(css("[data-session-id=\"sessionId\"] > code").saveAs("sessionId"))

  def postAuthPage(affinityGroup: String): HttpRequestBuilder = http("[post] gg-sign-in")
    .post(baseUrlAuthLoginStub + "/auth-login-stub/gg-sign-in")
    .disableFollowRedirect
    .formParam("authorityId", "")
    .formParam("redirectionUrl", cisFrontendUrl)
    .formParam("credentialStrength", "strong")
    .formParam("confidenceLevel", "50")
    .formParam("affinityGroup", affinityGroup)
    .formParam("enrolment[0].name", "HMRC-CIS-ORG")
    .formParam("enrolment[0].taxIdentifier[0].name", "TaxOfficeNumber")
    .formParam("enrolment[0].taxIdentifier[0].value", "754")
    .formParam("enrolment[0].taxIdentifier[1].name", "TaxOfficeReference")
    .formParam("enrolment[0].taxIdentifier[1].value", "EZ00100")
    .formParam("enrolment[0].state", "Activated")
    .formParam("csrfToken", "#{csrfToken}")
    .check(status.is(303))
//    .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
    .check(header("Location").is(cisFrontendUrl))

}
