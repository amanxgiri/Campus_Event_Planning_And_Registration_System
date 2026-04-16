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
        if (participant != null) {
            this.participants.add(participant);
        }
    }

    public void updateParticipant(Participant participant) {
        if (participant == null) {
            return;
        }
        for (int i = 0; i < participants.size(); i++) {
            if (participants.get(i).getParticipantId() == participant.getParticipantId()) {
                participants.set(i, participant);
                return;
            }
        }
    }

    public void deleteParticipant(int participantId) {
        participants.removeIf(p -> p.getParticipantId() == participantId);
    }

    public List<Participant> getAllParticipants() {
        return new ArrayList<>(this.participants);
    }

    public Participant findParticipantById(int participantId) {
        for (Participant participant : participants) {
            if (participant.getParticipantId() == participantId) {
                return participant;
            }
        }
        return null;
    }
}
