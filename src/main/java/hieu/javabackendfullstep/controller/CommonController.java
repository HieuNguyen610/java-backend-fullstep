//package hieu.javabackendfullstep.controller;
//
//import hieu.javabackendfullstep.response.ApiResponse;
//import hieu.javabackendfullstep.service.impl.MailService;
//import jakarta.mail.MessagingException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.support.SessionStatus;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.UnsupportedEncodingException;
//
//import static org.springframework.http.HttpStatus.ACCEPTED;
//import static org.springframework.http.HttpStatus.BAD_REQUEST;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/common")
//public class CommonController {
//
//    private final MailService mailService;
//
//    @PostMapping("/send-email")
//    public ResponseEntity<ApiResponse> sendEmail(@RequestParam String recipients, @RequestParam String subject,
//                                                 @RequestParam String content, @RequestParam(required = false) MultipartFile[] files, SessionStatus sessionStatus) {
//        log.info("Request GET /common/send-email");
//        try {
//            return ResponseEntity.ok(ApiResponse.builder()
//                            .status(ACCEPTED.value())
//                            .message(mailService.sendEmail(recipients, subject, content, files))
//                            .data(null).build());
//        } catch (UnsupportedEncodingException | MessagingException e) {
//            log.error("Sending email was failure, message={}", e.getMessage(), e);
//            return ResponseEntity.ok(
//                    ApiResponse.builder()
//                            .status(BAD_REQUEST.value())
//                            .message("Sending email was failure")
//                            .data(null).build());
//        }
//    }
//}
