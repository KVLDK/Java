/**
 Vahldieck, Kara
 COP-3252
 Project 1
 2/14/17
 */


public class Player {

    private String symbol;
    private String name;

    public Player(String s1, String s2)
    {
        symbol = s1;
        name = s2;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String s1)
    {
        symbol = s1;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String s1)
    {
        name = s1;
    }

}
