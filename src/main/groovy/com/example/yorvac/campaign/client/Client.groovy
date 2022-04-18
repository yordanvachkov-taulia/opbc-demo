package com.example.yorvac.campaign.client

import com.example.yorvac.campaign.model.AssignmentsScore
import com.example.yorvac.campaign.model.Participant
import com.example.yorvac.campaign.service.CampaignService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('/api/v1/campaigns')
class Client {

  @Autowired
  CampaignService campaignService

  @PostMapping
  String startCampaignForParticipant(@RequestBody Participant participant) {
    campaignService.startCampaignProcess(participant)
    'Success!'
  }

  @PostMapping('/assignments/score')
  String submitAssignmentsScore(@RequestBody AssignmentsScore assignmentsScore) {
    campaignService.submitAssignmentScore(assignmentsScore)
  }

}
