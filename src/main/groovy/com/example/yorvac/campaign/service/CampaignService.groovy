package com.example.yorvac.campaign.service

import com.example.yorvac.campaign.model.AssignmentsScore
import com.example.yorvac.campaign.model.Participant
import groovy.util.logging.Slf4j
import org.camunda.bpm.engine.MismatchingMessageCorrelationException
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.runtime.Execution
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import static com.example.yorvac.campaign.constant.ProcessActivities.RECEIVE_ASSIGNMENT_SCORE
import static com.example.yorvac.campaign.constant.ProcessDefinitions.CAMPAIGN_PROCESS
import static com.example.yorvac.campaign.constant.ProcessMessages.RECEIVE_ASSIGNMENT_SCORE_MESSAGE

@Slf4j
@Service
class CampaignService {

  @Autowired
  RuntimeService runtimeService

  void startCampaignProcess(Participant participant) {
    log.info("Starting campaign process for participant[${participant.email}]")
    runtimeService.createProcessInstanceByKey(CAMPAIGN_PROCESS.process)
      .businessKey(participant.email)
      .setVariable('participantName', participant.name)
      .execute()
  }

  String submitAssignmentScore(AssignmentsScore assignmentsScore) {
    Execution execution = runtimeService.createExecutionQuery()
      .processDefinitionKey(CAMPAIGN_PROCESS.process)
      .processInstanceBusinessKey(assignmentsScore.participantEmail)
      .activityId(RECEIVE_ASSIGNMENT_SCORE.activity)
      .singleResult()

    if (execution) {
      runtimeService.signal(execution.id, ['assignmentScore': assignmentsScore.score])
      'Success'
    } else {
      'Execution is not waiting at receiveAssignmentScore'
    }
  }

  String submitAssignmentScoreV2(AssignmentsScore assignmentsScore) {
    try {
      runtimeService.correlateMessage(RECEIVE_ASSIGNMENT_SCORE_MESSAGE.message,
        assignmentsScore.participantEmail,
        ['assignmentScore': assignmentsScore.score])
    } catch (MismatchingMessageCorrelationException ignore) {
      return 'Execution is not waiting at receiveAssignmentScore'
    }

    'Success'
  }
}
