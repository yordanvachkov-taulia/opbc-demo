package com.example.yorvac.invoice

import com.example.yorvac.invoice.model.Invoice

class Converter {
  static Invoice toInvoice(String line) {
    String[] data = line.split(',')
    new Invoice().tap {
      invoiceNumber = data[0]
      buyerName = data[1]
      supplierName = data[2]
      itemIdentifier = data[3]
      itemName = data[4]
      pricePerUnit = new BigDecimal(data[6])
      quantity = Integer.valueOf(data[6])
      totalPrice = new BigDecimal(data[7])
      currency = data[8]
    }
  }
}
