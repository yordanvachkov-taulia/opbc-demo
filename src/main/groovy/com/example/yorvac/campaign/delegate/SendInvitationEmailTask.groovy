package com.example.yorvac.campaign.delegate

import groovy.util.logging.Slf4j
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Slf4j
@Component
class SendInvitationEmailTask implements JavaDelegate {
  @Override
  void execute(DelegateExecution execution) throws Exception {
    log.info("Sending invitation email to participant[${execution.getBusinessKey()}]")
  }
}
