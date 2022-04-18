package com.example.yorvac.invoice.delegate

import com.example.yorvac.invoice.Converter
import com.example.yorvac.invoice.model.Invoice
import groovy.util.logging.Slf4j
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.camunda.bpm.engine.variable.Variables
import org.camunda.bpm.engine.variable.value.ObjectValue
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

@Slf4j
@Component
class LoadInvoicesTask implements JavaDelegate {

  @Override
  void execute(DelegateExecution execution) throws Exception {
    log.info("Loading invoices..")

    Resource resource = new ClassPathResource("sample-invoices.csv")
    File file = resource.getFile()
    List<String> lines = file.readLines()
    lines.remove(0)

    List<Invoice> invoices = lines.collect {
      Converter.toInvoice(it)
    }

    ObjectValue serializedInvoices =
      Variables.objectValue(invoices).serializationDataFormat("application/json").create()
    execution.setVariable('invoices', serializedInvoices)
  }
}
