package main;

import main.service.ApplianceService;

public class App {
//    Точка входа
    public static void main(String[] args) {
        ApplianceService applianceService = new ApplianceService();

//        Вывод
        System.out.println(applianceService.getCheapestAppliance());
        System.out.println(applianceService.getKettles());
    }
}
