package APITEST.registration;

public class UnSuccessReg {
    private String error;

    public UnSuccessReg(String error){
        this.error=error;
    }
    public UnSuccessReg(){

    }

    public String getError() {
        return error;
    }
}
