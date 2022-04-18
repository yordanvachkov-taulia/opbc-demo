package com.example.yorvac

import com.example.yorvac.campaign.constant.ProcessDefinitions
import org.camunda.bpm.engine.runtime.ProcessInstance
import org.camunda.bpm.engine.test.Deployment
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.complete
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.execute
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.job
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.task
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.withVariables
import static org.camunda.bpm.extension.mockito.DelegateExpressions.registerJavaDelegateMock

@Deployment(resources = ["bpmn/campaignProcess.bpmn"])
class CampaignProcessTest {

  @RegisterExtension
  ProcessEngineExtension extension = ProcessEngineExtension.builder().build()

  @Test
  void testInvitationPath() {
    //setup delegate mocks
    registerJavaDelegateMock('calculateScoreTask').onExecutionSetVariables(['score': 90])
    registerJavaDelegateMock('sendInvitationEmailTask')

    ProcessInstance pi = startProcessAndAssertItPassedCalculateScoreTask()
    assertThat(pi).hasPassed('sendInvitationEmail')

    assertProcessIsEnded(pi)
  }

  @Test
  void testRejectionPath() {
    registerJavaDelegateMock('calculateScoreTask').onExecutionSetVariables(['score': 70])
    registerJavaDelegateMock('sendRejectionEmailTask')

    ProcessInstance pi = startProcessAndAssertItPassedCalculateScoreTask()
    assertThat(pi).hasPassed('sendRejectionEmail')

    assertProcessIsEnded(pi)
  }

  ProcessInstance startProcessAndAssertItPassedCalculateScoreTask() {
    ProcessInstance pi = extension.getProcessEngine()
      .getRuntimeService()
      .startProcessInstanceByKey(ProcessDefinitions.CAMPAIGN_PROCESS.process, 'test@example.com')

    assertThat(pi).isActive()
    assertThat(pi).isWaitingAt('enterAssignmentScore')

    complete(task(), withVariables('assignmentScore', 70))
    assertThat(pi).hasPassed('enterAssignmentScore','calculateScore')

    pi
  }

  void assertProcessIsEnded(ProcessInstance pi) {
    assertThat(pi).isWaitingAt('reviewCampaign')
    complete(task())

    assertThat(pi).isEnded()
  }

}
