package com.example.yorvac.campaign.delegate

import groovy.util.logging.Slf4j
import org.camunda.bpm.engine.ManagementService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.camunda.bpm.engine.runtime.Job
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Slf4j
@Component
class SendRejectionEmailTask implements JavaDelegate {

  @Autowired
  ManagementService managementService

  @Override
  void execute(DelegateExecution execution) throws Exception {
    log.info("Sending rejection email to participant[${execution.getBusinessKey()}]")

    Job job = managementService.createJobQuery()
      .processInstanceId(execution.processInstanceId)
      .singleResult()
    log.info("Job retries[${job.retries}]")

    //simulate failing behaviour - fails 2 times, succeeds on the last attempt
    if (job.retries != 1) {
      throw new RuntimeException('Something went wrong')
    }
  }
}
