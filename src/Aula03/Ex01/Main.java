package Aula03.Ex01;

public class Main {
    public static void main(String[] args) {
        Team team = new Team("Dream Team", "Barcelona", "Guardiola");

        Player[] players = new Player[18];
        for (int i = 0; i < 18; i++) {
            boolean isFielded = i < 11;
            players[i] = new Player("Player" + (i + 1), "Position" + (i + 1), i + 1, isFielded);
            team.addPlayer(players[i]);
        }

        team.setCaptain(players[0]);

        System.out.println("\n Captain:");
        System.out.println(players[0].getStateAsString());

        System.out.println("\n Fielded Players:");
        for (Player player : team.getFieldedPlayers()) {
            System.out.println(player.getStateAsString());
        }

        System.out.println("\n Outfielded Players:");
        for (Player player : team.getOutfieldedPlayers()) {
            System.out.println(player.getStateAsString());
        }

        System.out.println("\n Substituindo " + players[0].getName() + " por " + players[11].getName() + "...");
        team.substitute(players[0], players[11]);

        System.out.println("\nFielded Players After Substitution:");
        for (Player player : team.getFieldedPlayers()) {
            System.out.println(player.getStateAsString());
        }

        System.out.println("\n Outfielded Players After Substitution:");
        for (Player player : team.getOutfieldedPlayers()) {
            System.out.println(player.getStateAsString());
        }
    }
}
