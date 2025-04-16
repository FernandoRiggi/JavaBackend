package Aula07.ex02;

public class Main {
    public static void main(String[] args) {
        // Criando os jogadores
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        // Criando o jogo com os jogadores
        Game game = new Game(player1, player2);

        // Enquanto o jogo não estiver terminado, jogue
        while (!game.isDone()) {
            // Joga uma rodada
            game.play();
        }

        // Exibe o vencedor final
        Player winner = game.getWinner();
        if (winner != null) {
            System.out.println("The winner of the game is: " + winner.getName());
        } else {
            System.out.println("The game ended in a draw.");
        }
    }
}
