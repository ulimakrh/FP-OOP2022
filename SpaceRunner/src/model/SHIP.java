package model;

public enum SHIP {

    //encapsulation
    BLUE("view/resources/shipchooser/playerShip1_blue.png", "view/resources/shipchooser/playerLife1_blue.png"),
    GREEN("view/resources/shipchooser/playerShip1_green.png", "view/resources/shipchooser/playerLife1_green.png"),
    ORANGE("view/resources/shipchooser/playerShip1_orange.png", "view/resources/shipchooser/playerLife1_orange.png"),
    RED("view/resources/shipchooser/playerShip1_red.png", "view/resources/shipchooser/playerLife1_red.png");

    private String urlShip;
    private String urlLife;

    //constructor
    private SHIP(String urlShip, String urlLife){
        this.urlShip = urlShip;
        this.urlLife = urlLife;
    }

    //getter
    public String getUrl(){
        return this.urlShip;
    }

    //getter
    public String getUrlLife(){
        return urlLife;
    }
    
}
