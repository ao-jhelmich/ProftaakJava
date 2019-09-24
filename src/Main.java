import datastorage.ShapeDAO;

public class Main {

    public static void main(String[] args) {

        System.out.println(
            new ShapeDAO().all()
        );

    }
}
