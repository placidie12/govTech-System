import java.util.*;
import java.io.*;

public class ManagerApplication {
    private List<ServiceApplication> applications;
    private final String dataFile = "applications.txt";
    private final String revenueFile = "revenue_report.txt";
    private Citizens Citizens;

     List<ServiceApplication> Applications = new ArrayList<>();

    public void RevenueReport() {

        double totalRevenue = 0;
        Map<String, Double> revenueByService = new HashMap<>();

        for (ServiceApplication app : applications) {

            if (app.getStatus() == ServiceApplication.Status.APPROVED) {

                double fee = app.getService().getFeeCharged();
                totalRevenue += fee;

                String serviceName = app.getService().getServiceName();

                revenueByService.put(
                        serviceName,
                        revenueByService.getOrDefault(serviceName, 0.0) + fee
                );
            }
        }

        System.out.println("Total Revenue: $" + totalRevenue);
    }

    public ManagerApplication() {
        applications = new ArrayList<>();
        loadApplications();
    }


    public void addApplication(ServiceApplication app) {
        applications.add(app);
        System.out.println("Application submitted successfully: " + app.getApplicationId());
        saveApplications();
    }


    public void approveApplication(String id) throws ApplicationNotFoundException, InvalidStatusException {
        ServiceApplication app = findApplicationById(id);
        app.approve();
        saveApplications();
    }

    public void rejectApplication(String id) throws ApplicationNotFoundException, InvalidStatusException {
        ServiceApplication app = findApplicationById(id);
        app.reject();
        saveApplications();
    }


    public ServiceApplication findApplicationById(String id) throws ApplicationNotFoundException {
        for(ServiceApplication app : applications) {
            if(app.getApplicationId().equals(id)) return app;
        }
        throw new ApplicationNotFoundException("Application with ID " + id + " not found.");
    }


    public void displayAllApplications() {
        if(applications.isEmpty()) {
            System.out.println("No applications found.");
            return;
          }
        for(ServiceApplication app : applications) {
            System.out.println(app);
         }
       }


    public void saveApplications() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(dataFile))) {
            for(ServiceApplication app : applications) {

                pw.println(app.getApplicationId() + "|" +
                        app.getApplicant().toString() + "|" +
                        app.getService().getServiceName() + "|" +
                        app.getService().getFeeCharged() + "|" +
                        app.getStatus());
            }
        } catch(IOException e) {
            System.out.println("Error saving applications: " + e.getMessage());
        }
    }


    public void loadApplications() {
        File file = new File(dataFile);
        if(!file.exists()) return;

        try (Scanner sc = new Scanner(file)) {
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");


                String appId = parts[0];
                String fullName = parts[1];
                String serviceName = parts[2];
                double fee = Double.parseDouble(parts[3]);
                ServiceApplication.Status status = ServiceApplication.Status.valueOf(parts[4]);


                Citizens citizen = new Citizens(fullName, "N/A", "N/A");

                Governmentservice service = null;
                if(serviceName.equals("Marriage Certificate")) service = new MarriageCertificate();
                else if(serviceName.equals("Criminal Record")) service = new Criminal_record();
                ServiceApplication app = new ServiceApplication(Citizens, service);

                app.setStatus(status);
                applications.add(app);
            }
        } catch(Exception e) {
            System.out.println("Error loading applications: " + e.getMessage());
        }
    }


    public void generateRevenueReport() {
        double totalRevenue = 0;
        Map<String, Double> revenueByService = new HashMap<>();

        for(ServiceApplication app : applications) {
            if(app.getStatus() == ServiceApplication.Status.APPROVED) {
                totalRevenue += app.getService().getFeeCharged();
                revenueByService.put(app.getService().getServiceName(),
                        revenueByService.getOrDefault(app.getService().getServiceName(), 0.0)
                                + app.getService().getFeeCharged());
            }
        }


        System.out.println("=== Revenue Summary ===");
        System.out.println("Total Revenue: $" + totalRevenue);
        for(String service : revenueByService.keySet()) {
            System.out.println(service + ": $" + revenueByService.get(service));
        }


        try (PrintWriter pw = new PrintWriter(new FileWriter(revenueFile))) {
            pw.println("=== Revenue Summary ===");
            pw.println("Total Revenue: $" + totalRevenue);
            for(String service : revenueByService.keySet()) {
                pw.println(service + ": $" + revenueByService.get(service));
            }
            System.out.println("Revenue report saved to " + revenueFile);
        } catch(IOException e) {
            System.out.println("Error writing revenue report: " + e.getMessage());
        }
    }

    public List<ServiceApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<ServiceApplication> applications) {
        this.applications = applications;
    }
}

