package com.example.yorvac.invoice.delegate

import groovy.util.logging.Slf4j
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Slf4j
@Component
class LoadInvoicesTask implements JavaDelegate {

  @Override
  void execute(DelegateExecution execution) throws Exception {
    log.info("Loading invoices..")
    execution.setVariable('invoices', ['sample-invoice.csv', 'sample-invoice2.csv'])
  }
}
