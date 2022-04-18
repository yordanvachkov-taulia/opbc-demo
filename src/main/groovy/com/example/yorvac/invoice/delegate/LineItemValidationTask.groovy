package com.example.yorvac.invoice.delegate

import com.example.yorvac.invoice.model.Invoice
import groovy.util.logging.Slf4j
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Slf4j
@Component
class LineItemValidationTask implements JavaDelegate {
  @Override
  void execute(DelegateExecution execution) throws Exception {
    Invoice invoice = execution.getVariable('invoice') as Invoice
    log.info("Validating line-items for invoice[${invoice.invoiceNumber}]")
    Thread.sleep(8000)
  }
}
