package com.example.yorvac.invoice.delegate

import groovy.util.logging.Slf4j
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Slf4j
@Component
class PricingValidationTask implements JavaDelegate {
  @Override
  void execute(DelegateExecution execution) throws Exception {
    log.info("Validating pricing")
    Thread.sleep(2500)
  }
}
