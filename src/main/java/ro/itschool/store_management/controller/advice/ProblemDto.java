package ro.itschool.store_management.controller.advice;

public record ProblemDto(String message, String status) {
}

// old version
//public class ProblemDto {
//    private final String message;
//    private final String status;
//
//    public ProblemDto(String message, String status) {
//        this.message = message;
//        this.status = status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//}
