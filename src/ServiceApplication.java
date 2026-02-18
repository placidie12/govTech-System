

public class ServiceApplication {

    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    private String applicationId;
    private Citizens applicant;
    private Governmentservice service;
    private Status status;

    public ServiceApplication(Citizens applicant, Governmentservice service) {
        this.applicationId = getApplicationId();
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



    public void Status(Status Status) throws InvalidStatusException {
        if (this.status == Status) {
            throw new InvalidStatusException("Application is already " + Status);
        }
        this.status = Status;
    }

    @Override
    public String toString() {
        return "ApplicationID: " + applicationId + "\nCitizen: " + applicant + "\nService: " + service.getServiceName() +
                "\nFee: $" + service.getFeeCharged() + "\nStatus: " + status + "\n-----------------------";
    }
}