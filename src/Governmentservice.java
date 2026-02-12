public abstract  class Governmentservice {
    protected  String ServiceName;
    protected  double FeeCharged;


    public Governmentservice(String ServiceName, String s, double FeeCharged) {
        this.ServiceName = ServiceName;
        this.FeeCharged = FeeCharged;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public Double getFeeCharged() {
        return FeeCharged;

    }

    public void setServiceName(String serviceName) {
        this.ServiceName = serviceName;
    }

    public void setFeeCharged(double feeCharged) {
        this.FeeCharged = feeCharged;

    }
    public abstract void processService();
}



