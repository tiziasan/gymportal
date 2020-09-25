package it.univaq.disim.mwt.gymportal.domain;

public enum Role {

    CUSTOMER("C"),
    MANAGER("M");

    private String value;

    private Role(String role) {
        switch (role){
            case "M":
                setValue("M");
                break;
            case "C":
                setValue("C");
                break;
            default:
                throw new IllegalArgumentException("Incorrect use of Role");
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static class Values {
        public static final String CUSTOMER= "C";
        public static final String MANAGER= "M";
    }

}
