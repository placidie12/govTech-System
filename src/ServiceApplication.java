import java.util.UUID;

public class ServiceApplication {

    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    private String applicationId;
    private Citizens applicant;
    private Governmentservice service;
    private Status status;

    public ServiceApplication(Citizens applicant, Governmentservice service) {
        this.applicationId = UUID.randomUUID().toString();
        this.applicant = applicant;
        this.service = service;
        this.status = Status.PENDING;
    }

    public String getApplicationId() {
        return applicationId;
            }
    public Citizens getApplicant() {
        return applicant;
                  }
    public Governmentservice getService() {
        return service;
             }
    public Status getStatus() {
        return status;
             }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void approve() throws InvalidStatusException {
        if (status != Status.PENDING) {
            throw new InvalidStatusException("Cannot approve an application that is not pending!");
        }
        status = Status.APPROVED;
    }

    public void reject() throws InvalidStatusException {
        if (status != Status.PENDING) {
            throw new InvalidStatusException("Cannot reject an application that is not pending!");
        }
        status = Status.REJECTED;
    }

    @Override
    public String toString() {
        return "ApplicationID: " + applicationId + "\nCitizen: " + applicant + "\nService: " + service.getServiceName() +
                "\nFee: $" + service.getFeeCharged() + "\nStatus: " + status + "\n-----------------------";
    }
}