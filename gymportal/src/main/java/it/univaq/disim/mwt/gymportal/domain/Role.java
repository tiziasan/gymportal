package it.univaq.disim.mwt.gymportal.domain;

public enum Role {

    CUSTOMER("CUSTOMER"),
    MANAGER("MANAGER");

    private String value;

    private Role(String role) {
        switch (role){
            case "MANAGER":
                setValue(role);
                break;
            case "CUSTOMER":
                setValue(role);
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
        public static final String CUSTOMER= "CUSTOMER";
        public static final String MANAGER= "MANAGER";
    }

//        CUSTOMER('C'),
//    MANAGER('M');
//
//    private char value;
//
//    private Role(char role) {
//        switch (role){
//            case 'M':
//                setValue('M');
//                break;
//            case 'C':
//                setValue('C');
//                break;
//            default:
//                throw new IllegalArgumentException("Incorrect use of Role");
//        }
//    }
//
//    public char getValue() {
//        return value;
//    }
//
//    public void setValue(char value) {
//        this.value = value;
//    }
//
//    public static class Values {
//        public static final String CUSTOMER= "C";
//        public static final String MANAGER= "M";
//    }

}
