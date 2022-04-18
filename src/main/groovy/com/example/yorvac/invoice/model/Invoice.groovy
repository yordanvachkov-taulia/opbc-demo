package com.example.yorvac.invoice.model

class Invoice implements Serializable{
  String invoiceNumber
  String buyerName
  String supplierName
  String itemIdentifier
  String itemName
  int quantity
  BigDecimal pricePerUnit
  BigDecimal totalPrice
  String currency
}
