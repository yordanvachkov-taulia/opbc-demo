package com.example.yorvac.campaign.delegate

import groovy.util.logging.Slf4j
import org.camunda.bpm.engine.delegate.BpmnError
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Slf4j
@Component
class CalculateScoreTask implements JavaDelegate {

  Random random = new Random()

  /**
   * This service task is responsible for fetching the score of the test from, let's say, another internal service
   * responsible only for the entry tests, then calculate the average between the test score and the one from the
   * assignments.
   */
  @Override
  void execute(DelegateExecution execution) throws Exception {
    String participant = execution.businessKey

    //testScore that is coming from database or another internal service
    int testScore = random.nextInt(100)
    log.info("Fetched score[${testScore}] from test for participant[${participant}]")

    //assignment score is being fetched from the process variables as we have added it by completing the user form
    int assignmentScore = execution.getVariable('assignmentScore') as int
    double score = testScore * 0.3 + assignmentScore * 0.7
    log.info("Average calculated score for participant[${participant}] is ${score}")

    //setting the final score as a process variable for later decision making
    execution.setVariable('score', score)

    if (score < 80) {
      throw new BpmnError('lowScoreCode',
        "Participant[${participant}] score[${score}] is less than required one[80]")
    }
  }
}
