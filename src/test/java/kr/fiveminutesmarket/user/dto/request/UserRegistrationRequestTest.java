package kr.fiveminutesmarket.user.dto.request;

import org.hibernate.validator.constraints.Length;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRegistrationRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validateTest() {
        class UserEmail {
            @NotNull
            @Email
            @Length(max = 20)
            String email;

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }
        }

        UserEmail userEmail = new UserEmail();
        userEmail.setEmail(" ");
        Set<ConstraintViolation<UserEmail>> violations = validator.validate(userEmail);

        assertEquals(1, violations.size());

    }

}