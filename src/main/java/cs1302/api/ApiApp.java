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


/**
 * REPLACE WITH NON-SHOUTING DESCRIPTION OF YOUR APP.
 */
public class ApiApp extends Application {
    Stage stage;
    Scene scene;
    VBox root;
    HBox topLay;
    HBox secLay;
    HBox row1, row2, row3;
    Label title;
    Label question;
    Button choice1, choice2, choice3;
    ImageView imgv1, imgv2, imgv3;
    ImageView titlePic;

    /**
     * Constructs an {@code ApiApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public ApiApp() {
        root = new VBox();
        stage = null;
        scene = null;
        row1 = new HBox();
        row2 = new HBox();
        row3 = new HBox();
        topLay = new HBox();
        secLay = new HBox();
        imgv1 = new ImageView();
        imgv2 = new ImageView();
        imgv3 = new ImageView();
        choice1 = new Button();
        choice2 = new Button();
        choice3 = new Button();
        titlePic = new ImageView();
        question = new Label("Who said this quote?");
        title = new Label("BREAKING BAD TRIVIA");
    } // ApiApp

    /** {@inheritDoc} */
    @Override
    public void init() {
        //root.setPrefWidth(625);
        root.getChildren().addAll(topLay, titlePic, secLay, row1, row2, row3);
        topLay.getChildren().add(title);

        String breakBadURL = "resources/breakingbad-large-logo.png";
        Image breakBadImg = new Image("file:" + breakBadURL, 500, 125, false, false);
        titlePic.setImage(breakBadImg);

        topLay.setHgrow(title, Priority.ALWAYS);
        secLay.getChildren().add(question);
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
    }

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        this.stage = stage;

        // demonstrate how to load local asset using "file:resources/"
        /*Image bannerImage = new Image("file:resources/readme-banner.png");
        ImageView banner = new ImageView(bannerImage);
        banner.setPreserveRatio(true);
        banner.setFitWidth(640);

        // some labels to display information
        Label notice = new Label("Modify the starter code to suit your needs.");

        // setup scene
        root.getChildren().addAll(banner, notice);*/
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

} // ApiApp
