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
import uk.gov.hmrc.perftests.cis.requests.SubcontractorRequests._

class CisSimulation extends Simulation with PerformanceTestRunner {

  before {
    DatabaseCleanup.dropMongoCollection()
    DatabaseCleanup.cleanupDatabaseIfNotStub()
  }

  setup("nil-monthly-return", "NMRP").withRequests(
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
    getMNRFCheckYourAnswersPage,
    getChangeConfirmNilReturnPage,
    postChangeConfirmNilReturnPage("09", "2024"),
    getMNRFCheckYourAnswersPage,
    getChangeDoYouWantToSubmitAnInactivityRequestPage,
    postChangeDoYouWantToSubmitAnInactivityRequestPage("option1"),
    getInactivityWarningPage,
    postInactivityWarningPage,
    getMNRFCheckYourAnswersPage,
    getChangeConfirmEmailAddressPage,
    postChangeConfirmEmailAddressPage("Submissionsuccessful@test.com"),
    getMNRFCheckYourAnswersPage,
    postMNRFCheckYourAnswersPage,
    postSubmissionSendPage,
    getPollingPage,
    getPollingPage,
    postPollingPage,
    getSuccessfulSubmissionPage
  )

  setup("agent-landing-pages", "ALP ").withRequests(
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

  setup("org-landing-pages", "OLP ").withRequests(
    getAuthPage,
    postManageAuthPage("Organisation"),
    getSession,
    getManageFrontend,
    getSignIntoCISPage,
    getSignIntoCISRouting,
    getCisReturnDashboardPage
  )

  setup("add-subcontractor-pages", "ASP ").withRequests(
    getAuthPage,
    postSubcontractorAuthPage,
    getSession,
    getAddSubcontractor,
    getWhatTypeOfSubcontractorAreYouAdding,
    postWhatTypeOfSubcontractorAreYouAdding("soletrader"),
    getDoesTheSubcontractorUseATradingName,
    postDoesTheSubcontractorUseATradingName("true"),
    getWhatIsTheSubcontractorsTradingName,
    postWhatIsTheSubcontractorsTradingName("Test Subcontractor Ltd"),
    getDoYouWantToAddTheSubcontractorsAddress,
    postDoYouWantToAddTheSubcontractorsAddress("true"),
    getWhatIsTheSubcontractorsAddress,
    postWhatIsTheSubcontractorsAddress("123 Test Ave", "", "Testing City", "United Kingdom", "AB1 2CD"),
    getDoYouHaveANationalInsuranceNumber,
    postDoYouHaveANationalInsuranceNumber("true"),
    getWhatIsTheSubcontractorsNationalInsuranceNumber,
    postWhatIsTheSubcontractorsNationalInsuranceNumber("AA123456C"),
    getDoYouHaveAUniqueTaxpayerReference,
    postDoYouHaveAUniqueTaxpayerReference("true"),
    getWhatIsTheSubcontractorsUniqueTaxpayerReference,
    postWhatIsTheSubcontractorsUniqueTaxpayerReference("1111111111"),
    getDoYouHaveAWorksReferenceNumber,
    postDoYouHaveAWorksReferenceNumber("true"),
    getWhatIsTheWorksReferenceNumber,
    postWhatIsTheWorksReferenceNumber("12345678"),
    getDoYouWantToAddTheSubcontractorsContactDetails,
    postDoYouWantToAddTheSubcontractorsContactDetails("true"),
    getSubcontractorsContactDetails,
    postSubcontractorsContactDetails("Test123@test.com", "01234567890"),
    getSubcontractorCheckYourAnswersPage,
    getChangeWhatTypeOfSubcontractorAreYouAdding,
    postChangeWhatTypeOfSubcontractorAreYouAdding("soletrader"),
    getSubcontractorCheckYourAnswersPage,
    getChangeWhatIsTheSubcontractorsTradingName,
    postChangeWhatIsTheSubcontractorsTradingName("New Test Subcontractor Ltd"),
    getSubcontractorCheckYourAnswersPage,
    getChangeDoesTheSubcontractorUseATradingName,
    postChangeDoesTheSubcontractorUseATradingName("false"),
    getChangeWhatIsTheSubcontractorsName,
    postChangeWhatIsTheSubcontractorsName("John", "Test", "Doe"),
    getSubcontractorCheckYourAnswersPage,
    getChangeWhatIsTheSubcontractorsName,
    postChangeWhatIsTheSubcontractorsName("Jane", "Tester", "Doe"),
    getSubcontractorCheckYourAnswersPage,
    getChangeWhatIsTheSubcontractorsAddress,
    postChangeWhatIsTheSubcontractorsAddress("123 Test Ave", "Flat 456", "Testing City", "United Kingdom", "AB21 3DE"),
    getSubcontractorCheckYourAnswersPage,
    getChangeDoYouWantToAddTheSubcontractorsAddress,
    postChangeDoYouWantToAddTheSubcontractorsAddress("false"),
    getSubcontractorCheckYourAnswersPage,
    getChangeWhatIsTheSubcontractorsNationalInsuranceNumber,
    postChangeWhatIsTheSubcontractorsNationalInsuranceNumber("JJ123456A"),
    getSubcontractorCheckYourAnswersPage,
    getChangeDoYouHaveANationalInsuranceNumber,
    postChangeDoYouHaveANationalInsuranceNumber("false"),
    getSubcontractorCheckYourAnswersPage,
    getChangeWhatIsTheSubcontractorsUniqueTaxpayerReference,
    postChangeWhatIsTheSubcontractorsUniqueTaxpayerReference("2222222222"),
    getSubcontractorCheckYourAnswersPage,
    getChangeDoYouHaveAUniqueTaxpayerReference,
    postChangeDoYouHaveAUniqueTaxpayerReference("false"),
    getSubcontractorCheckYourAnswersPage,
    getChangeWhatIsTheWorksReferenceNumber,
    postChangeWhatIsTheWorksReferenceNumber("78789934"),
    getSubcontractorCheckYourAnswersPage,
    getChangeDoYouHaveAWorksReferenceNumber,
    postChangeDoYouHaveAWorksReferenceNumber("false"),
    getSubcontractorCheckYourAnswersPage,
    getChangeSubcontractorsContactDetails,
    postChangeSubcontractorsContactDetails("doe.test@testurl.com", "449876543210"),
    getSubcontractorCheckYourAnswersPage,
    getChangeDoYouWantToAddTheSubcontractorsContactDetails,
    postChangeDoYouWantToAddTheSubcontractorsContactDetails("false"),
    getSubcontractorCheckYourAnswersPage
  )
  runSimulation()
}
