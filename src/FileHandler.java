import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String APPLICATIONS_FILE = "applications.txt";
    private static final String REVENUE_REPORT_FILE = "revenue_report.txt";


    public void saveApplications(List<ServiceApplication> applications) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(APPLICATIONS_FILE))) {
            for (ServiceApplication app : applications) {
                writer.println(app.toString());
            }
        }
    }


    public List<ServiceApplication> loadApplications() throws IOException {
        List<ServiceApplication> applications = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(APPLICATIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {

                System.out.println("Loaded: " + line);
            }
        }
        return applications;
    }


    public void generateRevenueReport(List<ServiceApplication> applications) throws IOException {
        double totalRevenue = 0.0;
        try (PrintWriter writer = new PrintWriter(new FileWriter(REVENUE_REPORT_FILE))) {
            writer.println("=== Revenue Report ===");
            for (ServiceApplication app : applications) {
                if (app.getStatus() == ServiceApplication.Status.APPROVED) {
                    totalRevenue += app.getService().getFeeCharged();
                    writer.printf("%s: %.2f%n", app.getService().getServiceName(), app.getService().getFeeCharged());
                }
            }
            writer.printf("Total Revenue: %.2f%n", totalRevenue);
        }
    }
}
