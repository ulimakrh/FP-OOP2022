package view;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.SHIP;
import model.SmallInfoLabel;

//class yang berisi logika berjalannya game
public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 800;

    private Stage menuStage;
    private ImageView ship;

    private boolean isLeftKeyPressed;
    private boolean isRigthtKeyPressed;
    private int angle;
    private AnimationTimer gameTimer;

    private GridPane gridPane1;
    private GridPane gridPane2;
    private final static String BACKGROUND_IMAGE = "view/resources/blue.png";

    private final static String METEOR_BROWN_IMAGE = "view/resources/shipchooser/meteorBrown_med1.png";
    private final static String METEOR_GREY_IMAGE = "view/resources/shipchooser/meteorGrey_med2.png";

    //arraylist
    private ImageView[] brownMeteors;
    private ImageView[] greyMeteors;
    Random randomPositionGenerator;

    private ImageView star;
    private SmallInfoLabel pointsLabel;
    private ImageView[] playerLifes;
    private int playerLife;
    private int points;
    private final static String GOLD_STAR_IMAGE = "view/resources/star_gold.png";

    private final static int STAR_RADIUS = 12;
    private final static int SHIP_RADIUS = 27;
    private final static int METEOR_RADIUS = 20;
    
    //constructor
    public GameViewManager(){

        initializeStage();
        createKeyListeners();
        randomPositionGenerator = new Random();
        
    }

    //untuk mengeset apakah kita menekan tombol di keyboard (left, right)
    private void createKeyListeners(){

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                
                if(event.getCode() == KeyCode.LEFT){

                    isLeftKeyPressed = true;

                }
                
                else if(event.getCode() == KeyCode.RIGHT){

                    isRigthtKeyPressed = true;

                }
                
            }
            
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                
                if(event.getCode() == KeyCode.LEFT){

                    isLeftKeyPressed = false;

                }
                
                else if(event.getCode() == KeyCode.RIGHT){

                    isRigthtKeyPressed = false;
                    
                }
                
            }
            
        });

    }

    //fungsi untuk menginisiasi pane baru ketika sudah memilih kapal
    private void initializeStage(){

        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);

    }

    //fungsi untuk membuat game baru
    public void createNewGame(Stage menuStage, SHIP choosenShip){

        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        createShip(choosenShip);
        createGameElements(choosenShip);

        createGameLoop();
        gameStage.show();

    }

    //fungsi untuk membuat elemen elemen dalam game seperti meteor, skor, bintang, dll
    private void createGameElements(SHIP chooseShip){

        playerLife = 2;
        star = new ImageView(GOLD_STAR_IMAGE);
        setNewElementPosition(star);
        gamePane.getChildren().add(star);

        pointsLabel = new SmallInfoLabel("POINTS : 00");
        pointsLabel.setLayoutX(460);
        pointsLabel.setLayoutY(20);
        gamePane.getChildren().add(pointsLabel);
        playerLifes = new ImageView[3];

        for(int i = 0; i < playerLifes.length; i++){

            playerLifes[i] = new ImageView(chooseShip.getUrlLife());
            playerLifes[i].setLayoutX(455 + (i * 50));
            playerLifes[i].setLayoutY(80);
            gamePane.getChildren().add(playerLifes[i]);

        }

        brownMeteors = new ImageView[3];
        for(int i = 0; i < brownMeteors.length; i++){

            brownMeteors[i] = new ImageView(METEOR_BROWN_IMAGE);
            setNewElementPosition(brownMeteors[i]);
            gamePane.getChildren().add(brownMeteors[i]);

        }

        greyMeteors = new ImageView[3];
        for(int i = 0; i < greyMeteors.length; i++){

            greyMeteors[i] = new ImageView(METEOR_GREY_IMAGE);
            setNewElementPosition(greyMeteors[i]);
            gamePane.getChildren().add(greyMeteors[i]);

        }

    }

    //fungsi untuk menggerakan elemen meteor (berputar)
    private void moveGameElements(){

        star.setLayoutY(star.getLayoutY() + 5);

        for(int i = 0; i < brownMeteors.length; i++){
            brownMeteors[i].setLayoutY(brownMeteors[i].getLayoutY() + 7);
            brownMeteors[i].setRotate(brownMeteors[i].getRotate() + 4);
        }

        for(int i = 0; i < greyMeteors.length; i++){
            greyMeteors[i].setLayoutY(greyMeteors[i].getLayoutY() + 7);
            greyMeteors[i].setRotate(greyMeteors[i].getRotate() + 4);
        }

    }

    //fungsi untuk mengecek apakah meteor dibelakang kapal, apabila iya, akan direlokasi secara random
    private void checkIfElementsAreBehindTheShipAndRelocate(){

        if(star.getLayoutY() > 1200){
            setNewElementPosition(star);
        }

        for(int i = 0; i < brownMeteors.length; i++){

            if(brownMeteors[i].getLayoutY() > 900){
                setNewElementPosition(brownMeteors[i]);
            }

        }

        for(int i = 0; i < greyMeteors.length; i++){

            if(greyMeteors[i].getLayoutY() > 900){
                setNewElementPosition(greyMeteors[i]);
            }

        }

    }

    //mengeset layout secara random untuk bintang dan meteor
    private void setNewElementPosition(ImageView image){

        image.setLayoutX(randomPositionGenerator.nextInt(370));
        image.setLayoutY(-(randomPositionGenerator.nextInt(3200) + 600));

    }

    //fungsi untuk mengeset kapal
    private void createShip(SHIP choosenShip){

        ship = new ImageView(choosenShip.getUrl());
        ship.setLayoutX(GAME_WIDTH/2);
        ship.setLayoutY(GAME_HEIGHT - 90);
        gamePane.getChildren().add(ship);

    }

    //fungsi untuk membuat loop game
    private void createGameLoop(){

        gameTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                
                moveBackground();
                moveGameElements();
                checkIfElementsAreBehindTheShipAndRelocate();
                checkIfElementsCollide();
                moveShip();
                
            }
            
        };

        gameTimer.start();

    }

    //fungsi untuk memberikan gambaran ke kapal apabila bergerak ke kiri dan ke kanan
    private void moveShip(){

        if(isLeftKeyPressed && !isRigthtKeyPressed){

            if(angle > -30){
                angle -= 5;
            }
            ship.setRotate(angle);
            if(ship.getLayoutX() > -20){
                ship.setLayoutX(ship.getLayoutX() - 3);
            }

        }

        if(isRigthtKeyPressed && !isLeftKeyPressed){

            if(angle < 30){
                angle += 5;
            }
            ship.setRotate(angle);

            if(ship.getLayoutX() < 522){
                ship.setLayoutX(ship.getLayoutX() + 3);
            }

        }

        if(!isLeftKeyPressed && !isRigthtKeyPressed){

            if(angle < 0){
                angle += 5;
            }
            else if(angle > 0){
                angle -= 5;
            }
            ship.setRotate(angle);

        }

        if(isLeftKeyPressed && isRigthtKeyPressed){

            if(angle < 0){
                angle += 5;
            }
            else if(angle > 0){
                angle -= 5;
            }
            ship.setRotate(angle);

        }

    }

    //fungsi untuk membuat background game
    private void createBackground(){

        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for(int i = 0; i < 12; i++){

            ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
            ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
            GridPane.setConstraints(backgroundImage1, i%3, i/3);
            GridPane.setConstraints(backgroundImage2, i%3, i/3);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);

        }

        gridPane2.setLayoutY(-1024);

        gamePane.getChildren().addAll(gridPane1, gridPane2);

    }

    //fungsi untuk menggerakan background supaya tidak membosankan
    private void moveBackground(){

        gridPane1.setLayoutY(gridPane1.getLayoutY() + 0.5);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 0.5);
        
        if(gridPane1.getLayoutY() >= 1024){
            gridPane1.setLayoutY(-1024);
        }

        if(gridPane2.getLayoutY() >= 1024){
            gridPane2.setLayoutY(-1024);
        }

    }

    //logika yang berisi apakah kapal bertabrakan dengan meteor dan bintang
    //menambah poin saat menabrak bintang
    //mengurangi nyawa saat menabrak meteor
    private void checkIfElementsCollide(){

        if(SHIP_RADIUS + STAR_RADIUS > calculateDistance(ship.getLayoutX() + 49, star.getLayoutX() + 15, 
            ship.getLayoutY() + 37, star.getLayoutY() + 15)){

                setNewElementPosition(star);

                points++;
                String textToSet = "POINTS : ";
                if(points < 10){
                    textToSet = textToSet + "0";
                }
                pointsLabel.setText(textToSet + points);
        }

        for(int i = 0; i < brownMeteors.length; i++){

            if(METEOR_RADIUS + SHIP_RADIUS > calculateDistance(ship.getLayoutX() + 49, 
                brownMeteors[i].getLayoutX() + 20, ship.getLayoutY() + 37, brownMeteors[i].getLayoutY() + 20)){
                    removeLife();
                    setNewElementPosition(brownMeteors[i]);
            }

        }

        for(int i = 0; i < greyMeteors.length; i++){

            if(METEOR_RADIUS + SHIP_RADIUS > calculateDistance(ship.getLayoutX() + 49, 
                greyMeteors[i].getLayoutX() + 20, ship.getLayoutY() + 37, greyMeteors[i].getLayoutY() + 20)){
                    removeLife();
                    setNewElementPosition(greyMeteors[i]);
            }

        }

    }

    //fungsi untuk menghilangkan nyawa
    private void removeLife(){

        gamePane.getChildren().remove(playerLifes[playerLife]);
        playerLife--;
        if(playerLife < 0){
            gameStage.close();
            gameTimer.stop();
            menuStage.show();
        }

    }

    //fungsi untuk mengecek dimensi
    private double calculateDistance(double x1, double x2, double y1, double y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}
