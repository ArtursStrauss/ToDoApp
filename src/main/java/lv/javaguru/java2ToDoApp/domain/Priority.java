package lv.javaguru.java2ToDoApp.domain;


public enum Priority {
    LOW,
    MEDIUM,
    HIGH;

    public static boolean contains(String value){
        for (Priority p: Priority.values()){
            if (p.name().equals(value)){
                return true;
            }
        }
        return false;
    }
}
