package com.example.yorvac.invoice.delegate

import com.example.yorvac.invoice.model.Invoice
import groovy.util.logging.Slf4j
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Slf4j
@Component
class NameValidationTask implements JavaDelegate {
  @Override
  void execute(DelegateExecution execution) throws Exception {
    Invoice invoice = execution.getVariable('invoice') as Invoice
    log.info("Validating buyer and supplier names for invoice[${invoice.invoiceNumber}]")
    Thread.sleep(5000)
  }
}
