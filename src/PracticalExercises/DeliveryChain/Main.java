package PracticalExercises.DeliveryChain;

import PracticalExercises.DeliveryChain.Persistence.CourierRepository;
import PracticalExercises.DeliveryChain.Persistence.InMemoryCourierRepository;
import PracticalExercises.DeliveryChain.Service.CourierRegistrationService;
import PracticalExercises.DeliveryChain.Service.CourierReportService;

public class Main {
    public static void main(String[] args) {
        final CourierRepository repo =new InMemoryCourierRepository();
        final var registrationService = new CourierRegistrationService(repo);
        final var reportService = new CourierReportService(repo);

        registrationService.register("100", 200, "Luciana Ramos",  null, null);
        registrationService.register("101", 150, "Bruno Costa", null,"100");
        registrationService.register("102", 120, "Fernanda Dias", null, "100");
        registrationService.register("103", 100, "Hugo Lima", "101", "100");
        registrationService.register("104", 80, "Amanda Souza", "101","100");
        registrationService.register("105", 70, "Carla Meirelles", "102","100");
        System.out.println(reportService.ReportOf("100"));
    }
}
