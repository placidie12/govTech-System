import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ApplicationManager {
    private List<ServiceApplication> applications;

    public ApplicationManager() {
        this.applications = new ArrayList<>();
    }


    public void addApplication(ServiceApplication application) {
        applications.add(application);
    }


    public void approveApplication(String applicationId) throws ApplicationNotFoundException, InvalidStatusException {
        ServiceApplication app = findApplication(applicationId);
        app.setStatus(ServiceApplication.Status.APPROVED);
    }


    public void rejectApplication(String applicationId) throws ApplicationNotFoundException, InvalidStatusException {
        ServiceApplication app = findApplication(applicationId);
        app.setStatus(ServiceApplication.Status.REJECTED);
    }


    public ServiceApplication findApplication(String applicationId) throws ApplicationNotFoundException {
        for (ServiceApplication app : applications) {
            if (app.getApplicationId().equals(applicationId)) {
                return app;
            }
        }
        throw new ApplicationNotFoundException("Application not found: " + applicationId);
    }


    public void displayAllApplications() {
        for (ServiceApplication app : applications) {
            System.out.println(app);
        }
    }


    public List<ServiceApplication> getApplications() {
        return applications;
    }
    public String generateRevenueReport() {
        double totalRevenue = 0.0;
        Map<String, Double> revenueByService = new HashMap<>();

        for (ServiceApplication app : applications) {
            if (app.getStatus() == ServiceApplication.Status.APPROVED) {
                double fee = app.getService().getFeeCharged();
                totalRevenue += fee;

                String serviceName = app.getService().getServiceName();
                revenueByService.merge(serviceName, fee, Double::sum);
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("=== Revenue Report ===\n");
        report.append("Total Revenue: ").append(totalRevenue).append("\n\n");
        report.append("Revenue by Service:\n");

        for (Map.Entry<String, Double> entry : revenueByService.entrySet()) {
            report.append("- ").append(entry.getKey())
                    .append(": ").append(entry.getValue()).append("\n");
        }

        return report.toString();
    }
    public void saveRevenueReportToFile(String filePath) throws IOException {
        String report = generateRevenueReport();
        Files.write(Paths.get(filePath), report.getBytes());
    }
}
