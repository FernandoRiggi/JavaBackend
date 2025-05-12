package Lesson01.Ex01;

public class Team {

    private String name;
    private String baseLocation;
    private String coachName;
    private final Player[] players = new Player[18];
    private int counter = 0;
    private Player captain;

    public Team(String name, String baseLocation, String coachName) {
        this.name = name;
        this.baseLocation = baseLocation;
        this.coachName = coachName;
    }

    public void addPlayer(Player player) {
        if (counter < 18) {
            players[counter] = player;
            counter++;
        } else {
            System.out.println("The team is completely full");
        }
    }

    public void removePlayer(Player player) {
        for (int i = 0; i < counter; i++) {
            if (players[i] == player) {
                for (int j = i; j < counter - 1; j++) {
                    players[j] = players[j + 1];
                }
                players[counter - 1] = null;
                counter--;
                return;
            }
        }
    }

    public void substitute(Player starter, Player substitute) {
        if(!starter.isFielded()) {System.out.println("The starter is not fielded"); return;}
        if(substitute.isFielded()) {System.out.println("The substitute is fielded"); return;}

        starter.setFielded(false);
        substitute.setFielded(true);
    }

    public void setCaptain(Player captain) {
        this.captain = captain;
        System.out.println("Captain has been set to " + captain.getName());
    }

    public Player[] getFieldedPlayers() {
        return getPlayersByState(true);
    }

    public Player[] getOutfieldedPlayers() {
        return getPlayersByState(false);
    }

    private Player[] getPlayersByState(boolean isFielded) {
        int count = 0;
        for (int i = 0; i < counter; i++) {
            if (players[i] != null && (players[i].isFielded()==isFielded)) {
                count++;
            }
        }

        Player[] result = new Player[count];
        int index = 0;
        for (int i = 0; i < counter; i++) {
            if (players[i] != null && !players[i].isFielded()) {
                result[index++] = players[i];
            }
        }

        return result;
    }
}
