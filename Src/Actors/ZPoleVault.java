package Src.Actors;

public class ZPoleVault extends Zombie {
    public ZPoleVault(String name, int[] position){
        super(name, 175, 100, 1, position, false);}

    public void action() {
         //! melompati petak pertama yang berisi tanaman di depannya. Lompatan 
         //! ini hanya dapat dilakukan sekali. Lompatan ini mengakibatkan tanaman 
         //! yang ada di petak tersebut terhapus dari map
    }

    public void attack() {
    }
}