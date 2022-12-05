/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 *
 * @author Ulima
 */

public enum SHIP {
    BLUE("view/resources/shipchooser/playerShip1_blue.png", "view/resources/shipchooser/playerLife1_blue.png"),
    GREEN("view/resources/shipchooser/playerShip1_green.png", "view/resources/shipchooser/playerLife1_green.png"),
    ORANGE("view/resources/shipchooser/playerShip1_orange.png", "view/resources/shipchooser/playerLife1_orange.png"),
    RED("view/resources/shipchooser/playerShip1_red.png", "view/resources/shipchooser/playerLife1_red.png");

    private String urlShip;
    private String urlLife;

    private SHIP(String urlShip, String urlLife){
        this.urlShip = urlShip;
        this.urlLife = urlLife;
    }

    public String getUrl(){
        return this.urlShip;
    }

    public String getUrlLife(){
        return urlLife;
    }
}

