import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class tweetOperation extends Constants{
    /*private final Query query = new Query("css Thread");
    private final Twitter tweets = new Twitter();*/

    /*void selectingCount() {

        while (tweets.getTweets().size() < getNUMBER_OF_TWEETS()) {
            query.setCount(Math.min(getNUMBER_OF_TWEETS() - tweets.getTweets().size(), 100));
            addingTweets();
            query.setMaxId(LAST_ID - 1);
        }
    }*/

   /* private void addingTweets() {
        try {
            QueryResult result = tweets.returnTwitterAuth().search(query);
            result.getTweets().stream().map(Status::getText).distinct().close();
            tweets.getTweets().addAll(result.getTweets());

            System.out.println("Gathered " + tweets.getTweets().size() + " tweets");
            for (Status t : tweets.getTweets()) {
                if (t.getId() < LAST_ID) LAST_ID = t.getId();
            }

        } catch (TwitterException te) {
            System.out.println("Couldn't connect: " + te);
        }
    }*/
}
