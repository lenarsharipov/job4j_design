package ru.job4j.generic.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Team<T extends Participant> {
    private String name;
    private List<T> participants = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public void addNewParticipant(T participant) {
        participants.add(participant);
        System.out.println(participant.getName() + " has been added to " + name + " team.");
    }

    public void playsWith(Team<T> team) {
        Random random = new Random();
        int i = random.nextInt(2);
        String winnerName = i == 0 ? this.name : team.name;
        System.out.println(winnerName + " team won!");
    }
}