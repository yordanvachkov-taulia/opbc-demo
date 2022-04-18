package com.example.yorvac.campaign.constant

enum ProcessDefinitions {
  CAMPAIGN_PROCESS('campaignProcess'),
  CAMPAIGN_PROCESS_RECEIVE_TASK('campaignProcess-receiveTask');

  final String process

  ProcessDefinitions(String process) {
    this.process = process
  }
}