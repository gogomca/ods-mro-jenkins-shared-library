package org.ods.usecase

import org.ods.service.*
import org.ods.util.MROPipelineUtil

import spock.lang.*

import static util.FixtureHelper.*

import util.*

class JiraUseCaseSupportSpec extends SpecHelper {

    JiraUseCase createUseCase(PipelineSteps steps, MROPipelineUtil util, JiraService jira) {
        return new JiraUseCase(steps, util, jira)
    }

    JiraUseCaseSupport createUseCaseSupport(PipelineSteps steps, JiraUseCase usecase) {
        return new JiraUseCaseSupport(steps, usecase)
    }

    def "apply test results to test issues"() {
        given:
        def steps = Spy(PipelineSteps)
        def usecase = Mock(JiraUseCase)

        def support = new JiraUseCaseSupport(steps, usecase)
        usecase.setSupport(support)

        def testIssues = createJiraTestIssues()
        def testResults = createTestResults()

        when:
        support.applyTestResultsToTestIssues(testIssues, testResults)

        then:
        1 * usecase.applyTestResultsAsTestIssueLabels(testIssues, testResults)
    }

    def "get automated test issues"() {
        given:
        def steps = Spy(PipelineSteps)
        def util = Mock(MROPipelineUtil)
        def jira = Mock(JiraService)
        def usecase = createUseCase(steps, util, jira)

        def support = createUseCaseSupport(steps, usecase)
        usecase.setSupport(support)

        def project = createProject()

        def jqlQuery = [
            jql: "project = ${project.id} AND issuetype in ('Test') AND labels = 'AutomatedTest'",
            expand: [ "renderedFields" ],
            fields: [ "components", "description", "issuelinks", "issuetype", "summary" ]
        ]

        when:
        support.getAutomatedTestIssues(project.id)

        then:
        1 * jira.getIssuesForJQLQuery(jqlQuery) >> []
    }

    def "get automated test issues with componentId"() {
        given:
        def steps = Spy(PipelineSteps)
        def util = Mock(MROPipelineUtil)
        def jira = Mock(JiraService)
        def usecase = createUseCase(steps, util, jira)

        def support = createUseCaseSupport(steps, usecase)
        usecase.setSupport(support)

        def project = createProject()
        def componentId = "myComponent"

        def jqlQuery = [
            jql: "project = ${project.id} AND component = '${componentId}' AND issuetype in ('Test') AND labels = 'AutomatedTest'",
            expand: [ "renderedFields" ],
            fields: [ "components", "description", "issuelinks", "issuetype", "summary" ]
        ]

        when:
        support.getAutomatedTestIssues(project.id, componentId)

        then:
        1 * jira.getIssuesForJQLQuery(jqlQuery) >> []
    }

    def "get automated test issues with labelsSelector"() {
        given:
        def steps = Spy(PipelineSteps)
        def util = Mock(MROPipelineUtil)
        def jira = Mock(JiraService)
        def usecase = createUseCase(steps, util, jira)

        def support = createUseCaseSupport(steps, usecase)
        usecase.setSupport(support)

        def project = createProject()
        def componentId = "myComponent"
        def labelsSelector = ["UnitTest"]

        def jqlQuery = [
            jql: "project = ${project.id} AND component = '${componentId}' AND issuetype in ('Test') AND labels = 'UnitTest' AND labels = 'AutomatedTest'",
            expand: [ "renderedFields" ],
            fields: [ "components", "description", "issuelinks", "issuetype", "summary" ]
        ]

        when:
        support.getAutomatedTestIssues(project.id, componentId, labelsSelector)

        then:
        1 * jira.getIssuesForJQLQuery(jqlQuery) >> []
    }

    def "get automated acceptance test issues"() {
        given:
        def steps = Spy(PipelineSteps)
        def util = Mock(MROPipelineUtil)
        def jira = Mock(JiraService)
        def usecase = createUseCase(steps, util, jira)

        def support = createUseCaseSupport(steps, usecase)
        usecase.setSupport(support)

        def project = createProject()

        def jqlQuery = [
            jql: "project = ${project.id} AND issuetype in ('Test') AND labels = 'AcceptanceTest' AND labels = 'AutomatedTest'",
            expand: [ "renderedFields" ],
            fields: [ "components", "description", "issuelinks", "issuetype", "summary" ]
        ]

        when:
        support.getAutomatedAcceptanceTestIssues(project.id)

        then:
        1 * jira.getIssuesForJQLQuery(jqlQuery) >> []
    }

    def "get automated acceptance test issues with componentId"() {
        given:
        def steps = Spy(PipelineSteps)
        def util = Mock(MROPipelineUtil)
        def jira = Mock(JiraService)
        def usecase = createUseCase(steps, util, jira)

        def support = createUseCaseSupport(steps, usecase)
        usecase.setSupport(support)

        def project = createProject()
        def componentId = "myComponent"

        def jqlQuery = [
            jql: "project = ${project.id} AND component = '${componentId}' AND issuetype in ('Test') AND labels = 'AcceptanceTest' AND labels = 'AutomatedTest'",
            expand: [ "renderedFields" ],
            fields: [ "components", "description", "issuelinks", "issuetype", "summary" ]
        ]

        when:
        support.getAutomatedAcceptanceTestIssues(project.id, componentId)

        then:
        1 * jira.getIssuesForJQLQuery(jqlQuery) >> []
    }

    def "get automated installation test issues"() {
        given:
        def steps = Spy(PipelineSteps)
        def util = Mock(MROPipelineUtil)
        def jira = Mock(JiraService)
        def usecase = createUseCase(steps, util, jira)

        def support = createUseCaseSupport(steps, usecase)
        usecase.setSupport(support)

        def project = createProject()

        def jqlQuery = [
            jql: "project = ${project.id} AND issuetype in ('Test') AND labels = 'InstallationTest' AND labels = 'AutomatedTest'",
            expand: [ "renderedFields" ],
            fields: [ "components", "description", "issuelinks", "issuetype", "summary" ]
        ]

        when:
        support.getAutomatedInstallationTestIssues(project.id)

        then:
        1 * jira.getIssuesForJQLQuery(jqlQuery) >> []
    }

    def "get automated installation test issues with componentId"() {
        given:
        def steps = Spy(PipelineSteps)
        def util = Mock(MROPipelineUtil)
        def jira = Mock(JiraService)
        def usecase = createUseCase(steps, util, jira)

        def support = createUseCaseSupport(steps, usecase)
        usecase.setSupport(support)

        def project = createProject()
        def componentId = "myComponent"

        def jqlQuery = [
            jql: "project = ${project.id} AND component = '${componentId}' AND issuetype in ('Test') AND labels = 'InstallationTest' AND labels = 'AutomatedTest'",
            expand: [ "renderedFields" ],
            fields: [ "components", "description", "issuelinks", "issuetype", "summary" ]
        ]

        when:
        support.getAutomatedInstallationTestIssues(project.id, componentId)

        then:
        1 * jira.getIssuesForJQLQuery(jqlQuery) >> []
    }

    def "get automated integration test issues"() {
        given:
        def steps = Spy(PipelineSteps)
        def util = Mock(MROPipelineUtil)
        def jira = Mock(JiraService)
        def usecase = createUseCase(steps, util, jira)

        def support = createUseCaseSupport(steps, usecase)
        usecase.setSupport(support)

        def project = createProject()

        def jqlQuery = [
            jql: "project = ${project.id} AND issuetype in ('Test') AND labels = 'IntegrationTest' AND labels = 'AutomatedTest'",
            expand: [ "renderedFields" ],
            fields: [ "components", "description", "issuelinks", "issuetype", "summary" ]
        ]

        when:
        support.getAutomatedIntegrationTestIssues(project.id)

        then:
        1 * jira.getIssuesForJQLQuery(jqlQuery) >> []
    }

    def "get automated integration test issues with componentId"() {
        given:
        def steps = Spy(PipelineSteps)
        def util = Mock(MROPipelineUtil)
        def jira = Mock(JiraService)
        def usecase = createUseCase(steps, util, jira)

        def support = createUseCaseSupport(steps, usecase)
        usecase.setSupport(support)

        def project = createProject()
        def componentId = "myComponent"

        def jqlQuery = [
            jql: "project = ${project.id} AND component = '${componentId}' AND issuetype in ('Test') AND labels = 'IntegrationTest' AND labels = 'AutomatedTest'",
            expand: [ "renderedFields" ],
            fields: [ "components", "description", "issuelinks", "issuetype", "summary" ]
        ]

        when:
        support.getAutomatedIntegrationTestIssues(project.id, componentId)

        then:
        1 * jira.getIssuesForJQLQuery(jqlQuery) >> []
    }

    def "get automated unit test issues"() {
        given:
        def steps = Spy(PipelineSteps)
        def util = Mock(MROPipelineUtil)
        def jira = Mock(JiraService)
        def usecase = createUseCase(steps, util, jira)

        def support = createUseCaseSupport(steps, usecase)
        usecase.setSupport(support)

        def project = createProject()

        def jqlQuery = [
            jql: "project = ${project.id} AND issuetype in ('Test') AND labels = 'UnitTest' AND labels = 'AutomatedTest'",
            expand: [ "renderedFields" ],
            fields: [ "components", "description", "issuelinks", "issuetype", "summary" ]
        ]

        when:
        support.getAutomatedUnitTestIssues(project.id)

        then:
        1 * jira.getIssuesForJQLQuery(jqlQuery) >> []
    }

    def "get automated unit test test issues with componentId"() {
        given:
        def steps = Spy(PipelineSteps)
        def util = Mock(MROPipelineUtil)
        def jira = Mock(JiraService)
        def usecase = createUseCase(steps, util, jira)

        def support = createUseCaseSupport(steps, usecase)
        usecase.setSupport(support)

        def project = createProject()
        def componentId = "myComponent"

        def jqlQuery = [
            jql: "project = ${project.id} AND component = '${componentId}' AND issuetype in ('Test') AND labels = 'UnitTest' AND labels = 'AutomatedTest'",
            expand: [ "renderedFields" ],
            fields: [ "components", "description", "issuelinks", "issuetype", "summary" ]
        ]

        when:
        support.getAutomatedUnitTestIssues(project.id, componentId)

        then:
        1 * jira.getIssuesForJQLQuery(jqlQuery) >> []
    }
}
