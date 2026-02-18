public abstract class Governmentservice {
    protected String ServiceName;
    protected double FeeCharged;

    public Governmentservice(String ServiceName, double FeeCharged) {
        this.ServiceName = ServiceName;
        this.FeeCharged = FeeCharged;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public Double getFeeCharged() {
        return FeeCharged;
    }

    @Override
    public String toString() {
        return "Governmentservice{" +
                "ServiceName='" + ServiceName + '\'' +
                ", FeeCharged=" + FeeCharged +
                '}';
    }
}