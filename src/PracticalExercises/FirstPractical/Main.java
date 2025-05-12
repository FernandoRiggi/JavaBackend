package PracticalExercises.FirstPractical;

public class Main {
    public static void main(String[] args) {
        // Criando usuários
        UserAccount user1 = new UserAccount("user1@example.com", "User1");
        UserAccount user2 = new UserAccount("user2@example.com", "User2");

        // Testando publicação de posts
        user1.publish("Hello, world!");
        user1.publish("This is my second post.");
        for (int i = 0; i < 8; i++) {
            user1.publish("This is my " + (i+2) + " post.");
        }

        System.out.println("=== User1's Posts ===");
        System.out.println(user1.showMyPosts());

        // Testando seguidores
        user2.acceptFollower(user1);
        System.out.println("=== User2's Followers ===");
        System.out.println(user2.showMyFriends());

        // Testando timeline
        user1.acceptFollower(user2);
        user2.publish("Hello from User2!");

        System.out.println("=== User1's Timeline ===");
        System.out.println(user1.showTimeline());

        System.out.println("=== User2's Timeline ===");
        System.out.println(user2.showTimeline());
        // Testando interações com post
        user1.clapPost(0);
        user1.booPost(1);
        System.out.println("=== User1's Posts After Interactions ===");
        System.out.println(user1.showMyPosts());

        // Testando exclusão de post
        boolean deleted = user1.delete(1);
        System.out.println("Post deleted: " + deleted);
        System.out.println(user1.showMyPosts());
    }
}