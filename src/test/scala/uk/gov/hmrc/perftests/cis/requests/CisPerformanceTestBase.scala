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

import uk.gov.hmrc.performance.conf.ServicesConfiguration

trait CisPerformanceTestBase extends ServicesConfiguration {

  val baseUrlAuthLoginStub: String = baseUrlFor("auth-login-stub")

  val cisFrontendHost: String = baseUrlFor("cis-frontend")
  val cisFrontendUrl: String  = cisFrontendHost + s"/construction-industry-scheme"

  val cisManageHost: String        = baseUrlFor("cis-manage-frontend")
  val cisManageFrontendUrl: String = cisManageHost + s"/construction-industry-scheme/management"

  val cisContractorHost: String        = baseUrlFor("cis-contractor-frontend")
  val cisContractorFrontendUrl: String = cisContractorHost + s"/construction-industry-scheme/subcontractor/add"

}
