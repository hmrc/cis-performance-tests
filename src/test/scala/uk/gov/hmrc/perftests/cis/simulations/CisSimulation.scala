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
import uk.gov.hmrc.perftests.cis.requests.NilMonthlyReturnRequests._
import uk.gov.hmrc.perftests.cis.requests.LandingPagesRequests._
import uk.gov.hmrc.perftests.cis.requests.SubcontractorRequests.{getDoesTheSubcontractorUseATradingName, _}

class CisSimulation extends Simulation with PerformanceTestRunner {

  before {
    DatabaseCleanup.dropMongoCollection()
    DatabaseCleanup.cleanupDatabaseIfNotStub()
  }

  setup("nil-monthly-return", "Monthly nil return").withRequests(
    getAuthPage,
    postAuthPage,
    getSession,
    getConstructionIndustryScheme,
    getConfirmNilReturnPage,
    postConfirmNilReturnPage("04", "2025"),
    getDoYouWantToSubmitAnInactivityRequestPage,
    postDoYouWantToSubmitAnInactivityRequestPage("option2"),
    getConfirmEmailAddressPage,
    postConfirmEmailAddressPage("tester.test@test.com"),
    getDeclarationPage,
    postDeclarationPage("confirmed"),
    getCheckYourAnswersPage,
    getChangeConfirmNilReturnPage,
    postChangeConfirmNilReturnPage("09", "2024"),
    getCheckYourAnswersPage,
    getChangeDoYouWantToSubmitAnInactivityRequestPage,
    postChangeDoYouWantToSubmitAnInactivityRequestPage("option1"),
    getInactivityWarningPage,
    postInactivityWarningPage,
    getCheckYourAnswersPage,
    getChangeConfirmEmailAddressPage,
    postChangeConfirmEmailAddressPage("Submissionsuccessful@test.com"),
    getCheckYourAnswersPage,
    postCheckYourAnswersPage,
    postSubmissionSendPage,
    getPollingPage,
    getPollingPage,
    postPollingPage,
    getSuccessfulSubmissionPage
  )

  setup("agent-landing-pages", "Agent landing pages").withRequests(
    getAuthPage,
    postManageAuthPage("Agent"),
    getSession,
    getManageFrontend,
    getSignIntoCISPage,
    getSignIntoCISRouting,
    getRetrieveClientList,
    getStart,
    getFileMonthlyCISReturnPage,
    getFilterFileMonthlyCISReturnPage,
    getAgentCisReturnDashboardPage
  )

  setup("org-landing-pages", "Organisation landing pages").withRequests(
    getAuthPage,
    postManageAuthPage("Organisation"),
    getSession,
    getManageFrontend,
    getSignIntoCISPage,
    getSignIntoCISRouting,
    getCisReturnDashboardPage
  )

  setup("add-subcontractor-pages", "Add subcontractor pages").withRequests(
    getAuthPage,
    postManageAuthPage("Organisation"),
    getSession,
    getWhatTypeOfSubcontractorAreYouAdding,
    postWhatTypeOfSubcontractorAreYouAdding("value_0"), // Individual or sole trader
    getDoesTheSubcontractorUseATradingName,
    postDoesTheSubcontractorUseATradingName("value"), // Yes
    getWhatIsTheSubcontractorsTradingName,
    postWhatIsTheSubcontractorsTradingName("Test Subcontractor Ltd"),
    getDoYouWantToAddTheSubcontractorsAddress,
    postDoYouWantToAddTheSubcontractorsAddress("value"), // Yes
    getWhatIsTheSubcontractorsAddress,
    postWhatIsTheSubcontractorsAddress("123 Test Ave", "", "Testing City", "United Kingdom", "AB1 2CD"),
    getDoYouHaveANationalInsuranceNumber,
    postDoYouHaveANationalInsuranceNumber("value"), // Yes
    getWhatIsTheSubcontractorsNationalInsuranceNumber,
    postWhatIsTheSubcontractorsNationalInsuranceNumber("AA123456C"),
    getDoYouHaveAUniqueTaxpayerReference,
    postDoYouHaveAUniqueTaxpayerReference("value"), //Yes
    getWhatIsTheSubcontractorsUniqueTaxpayerReference,
    postWhatIsTheSubcontractorsUniqueTaxpayerReference("1234567890"),
    getDoYouHaveAWorksReferenceNumber,
    postDoYouHaveAWorksReferenceNumber("value"), // Yes
    getWhatIsTheWorksReferenceNumber,
    postWhatIsTheWorksReferenceNumber("12345678"),
    getDoYouWantToAddTheSubcontractorsContactDetails,
    postDoYouWantToAddTheSubcontractorsContactDetails("value"), // Yes
    getSubcontractorsContactDetails
  )
  runSimulation()
}
