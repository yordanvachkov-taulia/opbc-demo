package com.example.yorvac.invoice.delegate

import groovy.util.logging.Slf4j
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Slf4j
@Component
class ScanInvoiceTask implements JavaDelegate {

  @Override
  void execute(DelegateExecution execution) throws Exception {
    log.info('Pretend to be doing some kind of scanning..')
    Thread.sleep(10000)
  }
}
