package ru.job4j.generic.game;

public class GameDemo {
    public static void main(String[] args) {
        Pupil pupil1 = new Pupil("Ivan", 13);
        Pupil pupil2 = new Pupil("Mariya", 15);
        Pupil pupil3 = new Pupil("Adel", 12);
        Pupil pupil4 = new Pupil("Aliya", 9);

        Student student1 = new Student("Nick", 20);
        Student student2 = new Student("Xena", 18);
        Employee employee1 = new Employee("John", 32);
        Employee employee2 = new Employee("Mike", 47);

        Team<Pupil> pupilsTeam1 = new Team<>("Dragons");
        pupilsTeam1.addNewParticipant(pupil1);
        pupilsTeam1.addNewParticipant(pupil2);
        Team<Pupil> pupilsTeam2 = new Team<>("Lakers");
        pupilsTeam2.addNewParticipant(pupil3);
        pupilsTeam2.addNewParticipant(pupil4);

        Team<Student> studentTeam1 = new Team<>("Wolves");
        studentTeam1.addNewParticipant(student1);
        studentTeam1.addNewParticipant(student2);

        Team<Employee> employeeTeam1 = new Team<>("Bulls");
        employeeTeam1.addNewParticipant(employee1);
        employeeTeam1.addNewParticipant(employee2);

        Team<Participant> participantTeam = new Team<>("Participants");

        participantTeam.addNewParticipant(pupil1);
        participantTeam.addNewParticipant(student1);
        participantTeam.addNewParticipant(employee1);

        pupilsTeam1.playsWith(pupilsTeam2);
    }
}