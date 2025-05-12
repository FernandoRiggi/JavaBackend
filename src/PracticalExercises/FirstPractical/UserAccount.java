package PracticalExercises.FirstPractical;

import java.time.LocalDate;

public class UserAccount {
    private String email;
    private String userName;
    private final Post[] timeline = new Post[10];
    private int countPosts =0;
    private final Post[] posts = new Post[100000];
    private int countFollowers =0;
    private final UserAccount[] followers = new UserAccount[100000];

    public UserAccount(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }

    public void publish(String quote){
        Post post = new Post(this, quote);
        posts[countPosts] = post;
        countPosts++;
        for(int i =0; i<countFollowers; i++){
            followers[i].updateTimeline(post);
        }
    }

    public boolean delete(int postIdx){
        for(int i =0; i<posts.length; i++){
            if(posts[i]==posts[postIdx]){
                for(int j=0; j<posts.length-1; j++){
                    posts[j]=posts[j+1];
                }
                posts[postIdx]=null;
                return true;
            }
        }
        return false;
    }

    public String showTimeline(){
        StringBuilder sb = new StringBuilder();
        for (Post post : timeline) {
            if (post != null) {
                sb.append(post.show());
            }
        }
        return sb.toString();
    }

    public String showMyPosts(){
        StringBuilder sb = new StringBuilder();
        for (Post post : posts) {
            if (post != null) {
                sb.append(post.show());
            }
        }
        return sb.toString();
    }

    public String showMyFriends(){
        StringBuilder sb = new StringBuilder();
        for (UserAccount account : followers) {
            if (account != null) {
                sb.append(account.userName);
            }
        }
        return sb.toString();
    }

    public void clapPost(int postIdx){
        posts[postIdx].clap();
    }

    public void booPost(int postIdx){
        posts[postIdx].boo();
    }

    public void updateTimeline(Post newPost){
        LocalDate oldest = posts[0].getDate();

        for (int i = 0; i < timeline.length; i++) {
            if (timeline[i] == null) {
                timeline[i] = newPost;
                return;
            }
        }

        if(timeline[9] != null){
            for(Post post : timeline){
                if(post.getDate().compareTo(oldest) < 0){
                    oldest = post.getDate();
                }
            }
            for(int i =0; i < timeline.length; i++){
                if(timeline[i].getDate()==oldest){
                    timeline[i] = newPost;
                }
            }
        }
    }

    public void acceptFollower(UserAccount newFollower){
        followers[countFollowers] = newFollower;
        countFollowers++;
    }

    public void blockFollower(UserAccount follower){
        for(int i =0; i < countFollowers; i++){
            if(followers[i]==follower){
                for(int j = 1; j < countFollowers; j++){
                    followers[j] = followers[j+1];
                }
                followers[--countFollowers] = null;
                return;
            }
        }
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
