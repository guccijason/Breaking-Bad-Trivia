package cs1302.api;

import javafx.scene.layout.Priority;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * REPLACE WITH NON-SHOUTING DESCRIPTION OF YOUR APP.
 */
public class ApiApp extends Application {

    /** HTTP client. */
    public static HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
        .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
        .build();                                     // builds and returns an HttpClient

    public static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    private static final String BREAKING_API = "https://api.breakingbadquotes.xyz/v1/quotes";

    Stage stage;
    Scene scene;
    VBox root;
    HBox topLay;
    HBox secLay;
    HBox row1, row2, row3;
    Label title;
    Label question;
    Label quote;
    Button choice1, choice2, choice3;
    ImageView imgv1, imgv2, imgv3;
    ImageView titlePic;
    Button start;
    HBox thirdLay;

    /**
     * Constructs an {@code ApiApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public ApiApp() {
        start = new Button("Start");
        root = new VBox();
        stage = null;
        scene = null;
        row1 = new HBox();
        row2 = new HBox();
        row3 = new HBox();
        topLay = new HBox();
        secLay = new HBox();
        thirdLay = new HBox();
        imgv1 = new ImageView();
        imgv2 = new ImageView();
        imgv3 = new ImageView();
        choice1 = new Button();
        choice2 = new Button();
        choice3 = new Button();
        titlePic = new ImageView();
        question = new Label("Who said this quote?");
        quote = new Label("QUOTE");
        title = new Label("BREAKING BAD TRIVIA");
    } // ApiApp

    /** {@inheritDoc} */
    @Override
    public void init() {
        //root.setPrefWidth(625);
        root.getChildren().addAll(topLay, titlePic, secLay, thirdLay, row1, row2, row3);
        topLay.getChildren().add(title);
        topLay.setAlignment(Pos.CENTER);


        String breakBadURL = "resources/breakingbad-large-logo.png";
        Image breakBadImg = new Image("file:" + breakBadURL, 500, 125, false, false);
        titlePic.setImage(breakBadImg);

        topLay.setHgrow(title, Priority.ALWAYS);
        secLay.getChildren().addAll(question, start);
        secLay.setAlignment(Pos.CENTER);
        thirdLay.getChildren().add(quote);
        thirdLay.setAlignment(Pos.CENTER);

        secLay.setHgrow(question, Priority.ALWAYS);
        row1.getChildren().addAll(choice1, imgv1);
        row2.getChildren().addAll(choice2, imgv2);
        row3.getChildren().addAll(choice3, imgv3);

        row1.setHgrow(choice1, Priority.ALWAYS);
        row2.setHgrow(choice2, Priority.ALWAYS);
        row3.setHgrow(choice3, Priority.ALWAYS);

        choice1.setMaxWidth(Double.MAX_VALUE);
        choice2.setMaxWidth(Double.MAX_VALUE);
        choice3.setMaxWidth(Double.MAX_VALUE);

        choice1.setMaxHeight(Double.MAX_VALUE);
        choice2.setMaxHeight(Double.MAX_VALUE);
        choice3.setMaxHeight(Double.MAX_VALUE);

        setDefault();
        quotesSetUp();


    }

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        this.stage = stage;

        scene = new Scene(root);

        // setup stage
        stage.setTitle("ApiApp!");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        stage.show();

    } // start

    /**
     * Helper method to set default images.
     */
    private void setDefault() {
        String defaultURL = "resources/walter-whiteFace.png";
        Image defaultImg = new Image("file:" + defaultURL, 175, 175, false, false);
        imgv1.setImage(defaultImg);
        imgv2.setImage(defaultImg);
        imgv3.setImage(defaultImg);
    }

    /**
     * Sets up the quotes.
     */
    private void quotesSetUp() {
        try {
            String uri = BREAKING_API;
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();
            // send request / receive response in the form of a String
            HttpResponse<String> response = HTTP_CLIENT
                .send(request, BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new IOException(response.toString());
            } // if
            String jsonString = response.body();
            System.out.println("********** RAW JSON STRING: **********");
            System.out.println(jsonString.trim());
            // parse the JSON-formatted string using GSON
            BreakingQuotes[] breakingquotes = GSON
                .fromJson(jsonString, BreakingQuotes[].class);
            getQuote(breakingquotes);
        } catch (IOException | InterruptedException e) {

            System.err.println(e);
            e.printStackTrace();
        } // try
    }

    /**
     * Retrieves the quote from the api.
     * @param breakingquotes the response object
     */
    private void getQuote(BreakingQuotes[] breakingquotes) {
        this.quote.setText(breakingquotes[0].quote);
    }

} // ApiApp
