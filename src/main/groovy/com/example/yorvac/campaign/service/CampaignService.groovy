package com.example.yorvac.campaign.service

import com.example.yorvac.campaign.model.Participant
import org.camunda.bpm.engine.RuntimeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import static com.example.yorvac.campaign.constant.ProcessDefinitions.CAMPAIGN_PROCESS

@Service
class CampaignService {

  @Autowired
  RuntimeService runtimeService

  void startCampaignProcess(Participant participant) {
    runtimeService.createProcessInstanceByKey(CAMPAIGN_PROCESS.process)
      .businessKey(participant.email)
      .setVariable('participantName', participant.name)
      .execute()
  }
}
