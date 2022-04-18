package com.example.yorvac.campaign.constant

enum ProcessMessages {
  RECEIVE_ASSIGNMENT_SCORE_MESSAGE('receiveAssignmentScoreMessage');

  final String message

  ProcessMessages(String message) {
    this.message = message
  }
}
