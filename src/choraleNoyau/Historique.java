package choraleNoyau;

import java.io.Serializable;
import java.util.Date;

public class Historique implements Serializable {
    private String name;
    private Date date;
    private int money;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Historique (String name, Date date, int money){
        this.name = name;
        this.date = date;
        this.money =money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
