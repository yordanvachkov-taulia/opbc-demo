package com.example.yorvac.invoice.delegate

import groovy.util.logging.Slf4j
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Slf4j
@Component
class OcrTask implements JavaDelegate {
  @Override
  void execute(DelegateExecution execution) throws Exception {
    log.info("Scan invoice[${execution.getVariable('invoice')}]")
    Thread.sleep(10000)
  }
}
