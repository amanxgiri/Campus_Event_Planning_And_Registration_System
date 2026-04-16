package app.service;

import app.model.Participant;
import java.util.ArrayList;
import java.util.List;

public class ParticipantService {
    private List<Participant> participants;

    public ParticipantService() {
        this.participants = new ArrayList<>();
    }

    public void addParticipant(Participant participant) {
        // TODO: implement inserting participant
        this.participants.add(participant);
    }

    public void updateParticipant(Participant participant) {
        // TODO: implement updating participant
    }

    public void deleteParticipant(int participantId) {
        // TODO: implement deleting participant
    }

    public List<Participant> getAllParticipants() {
        // TODO: implement fetching all
        return this.participants;
    }

    public Participant findParticipantById(int participantId) {
        // TODO: implement fetching by ID
        return null;
    }
}
