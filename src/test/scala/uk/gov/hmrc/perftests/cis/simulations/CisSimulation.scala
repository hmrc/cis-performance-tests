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

package uk.gov.hmrc.perftests.cis.simulations

import io.gatling.core.scenario.Simulation
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.cis.mongo.DatabaseCleanup
import uk.gov.hmrc.perftests.cis.requests.AuthRequests._
import uk.gov.hmrc.perftests.cis.requests.CisRequests._

class CisSimulation extends Simulation with PerformanceTestRunner {

  before {
    DatabaseCleanup.dropMongoCollection()
    DatabaseCleanup.deleteOracleTableData()
  }

  setup("nil-monthly-return", "Submit a monthly nil return").withRequests(
    getAuthPage, postAuthPage("Organisation"),
    getSession,
    getConstructionIndustryScheme,
    getConfirmNilReturnPage, postConfirmNilReturnPage("10", "2007"),
    getDoYouWantToSubmitAnInactivityRequestPage, postDoYouWantToSubmitAnInactivityRequestPage("option1"),
    getConfirmEmailAddressPage, postConfirmEmailAddressPage("test@test.com"),
    getDeclarationPage, postDeclarationPage("confirmed"),
    getInactivityWarningPage, postInactivityWarningPage,
    getCheckYourAnswersPage, postCheckYourAnswersPage

  )

  runSimulation()
}
