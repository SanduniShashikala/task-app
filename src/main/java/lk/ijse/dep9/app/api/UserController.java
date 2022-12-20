package lk.ijse.dep9.app.api;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @PostMapping
    public void createUserAccount(){

    }
    @PatchMapping("/me")
    public void updateUserAccountDetail(){

    }

    @GetMapping("/me")
    public void getUserAccountDetails(){

    }

    @DeleteMapping("/me")
    public void deleteUserAccount(){

    }
}
