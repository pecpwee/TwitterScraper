import org.jetbrains.annotations.NotNull;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;

import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TwitterProject  {


    public static void main(String[] args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey("OkXcNWZCRrssy3BH1sYI9HL9X").setOAuthConsumerSecret("m23KC1fc2eHqRQ1OI4bGDKC78Pvxpan4DeNFxEz0VTOy1ZneDi").setOAuthAccessToken("1350104481380315137-sjsQg5HG2LXAGTXYubWn6gaVtNfcY5").setOAuthAccessTokenSecret("Q2xGuVQGdGDjeqqncFUJwPO7MbqEvUly6zo5527MH1PM5");
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        Query query = new Query("(css thread) OR (js thread)");
        int numberOfTweets = 512;
        long lastID = Long.MAX_VALUE;
        List<Status> tweets = new ArrayList<>();

        while (tweets.size() < numberOfTweets) {
            query.setCount(Math.min(numberOfTweets - tweets.size(), 100));
            try {
                QueryResult result = twitter.search(query);

                result.getTweets().stream().map(Status::getText).distinct().close();
                tweets.addAll(result.getTweets());

                System.out.println("Gathered " + tweets.size() + " tweets");

                for (Status t : tweets) if (t.getId() < lastID) lastID = t.getId();

            } catch (TwitterException te) {
                System.out.println("Couldn't connect: " + te);
            }
            query.setMaxId(lastID - 1);
        }
        File file = getUniqueFile();
        try {
            FileWriter fileWriter = new FileWriter(file);

            String newLine = System.getProperty("line.separator");

            for (int i = 0; i < tweets.size(); i++) {

                Status t = tweets.get(i);

                String user = t.getUser().getScreenName();
                String url = "https://twitter.com/" + t.getUser().getScreenName() + "/status/" + t.getId();


                writeToFile(fileWriter, i, user, url, newLine);

            }
            fileWriter.close();
        } catch (IOException io) {
            io.printStackTrace();
            io.getCause();
        }
    }

    @NotNull
    private static File getUniqueFile() {
        int count = 0;
        String file = "output" + count + ".txt";
        File fl = new File(file);
        while (fl.exists()) {
            file = "output" + (count += 1) + ".txt";

            fl = new File(file);
        }
        return fl;
    }

    public static void writeToFile(@org.jetbrains.annotations.NotNull FileWriter fileWriter, int i, String user, String url, String newLine) throws IOException {
        fileWriter.write(i + " USER: " + user + " URL " + url + newLine);
    }
}