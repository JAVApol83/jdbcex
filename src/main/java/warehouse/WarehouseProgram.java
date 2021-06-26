package warehouse;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WarehouseProgram {

    /*
    create table warehouses (
	id bigint not null auto_increment,
    name varchar(255) not null,
    primary key(id));
     */

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        WarehouseService warehouseService = new WarehouseService();

        System.out.println("1 - wyświetl magazyny");
        System.out.println("2 - dodaj nowy magazyn");

        int option = Integer.parseInt(scanner.nextLine());

        switch (option) {

            case 1:
                List<Warehouse> warehouseList = warehouseService.getAllWarehouses();
                for (Warehouse warehouse : warehouseList) {
                    System.out.println("Magazyn: " + warehouse.getId() + ": " + warehouse.getName());
                }
                break;
            case 2:
                break;
        }
    }
}
