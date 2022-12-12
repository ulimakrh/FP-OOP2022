package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class SpaceRunnerButton extends Button{//inheritence

    private final String FONT_PATH = "src/model/resources/kenvector_future.ttf";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/yellow_button01.png');";
    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/yellow_button00.png');";
    
    //untuk mengenerate tombol tombol yang sudah dibuat
    public SpaceRunnerButton(String text){
        setText(text);
        setButtonFont();
        setPrefWidth(190);
        setPrefHeight(49);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
    }

    //fungsi untuk mengeset font label
    //apabila font yang diminta tidak ada, maka akan mengeset font ke verdana 
    private void setButtonFont(){

        try{
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        }catch(FileNotFoundException e){//exception handling
            setFont(Font.font("Verdana", 23));
        }

        
    }

    //fungsi untuk mengeset apabila tombol sudah ditekan (akan bergerak sedikit)
    private void setButtonPressedStyle(){

        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);

    }

    //fungsi untuk mengeset apabila tombol sudah tidak ditekan
    private void setButtonReleasedStyle(){

        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);

    }

    //fungsi untuk mengeset logika apabila tombol sudah ditekan dan dilepas
    private void initializeButtonListeners(){

        setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event){

                if(event.getButton().equals(MouseButton.PRIMARY)){
                    setButtonPressedStyle();
                }

            }
            
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event){

                if(event.getButton().equals(MouseButton.PRIMARY)){
                    setButtonReleasedStyle();
                }

            }
            
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event){
                setEffect(null);
            }

        });

    }

}
