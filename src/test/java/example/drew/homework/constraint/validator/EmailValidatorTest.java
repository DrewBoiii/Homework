package example.drew.homework.constraint.validator;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmailValidatorTest {

    private EmailValidator emailValidator;

    @Before
    public void init(){
        emailValidator = new EmailValidator();
    }

    @After
    public void destroy(){
        emailValidator = null;
    }

    @Test
    public void inputEmptyEmail_whenCallIsValid_thenNotOk(){
        final boolean expected = false;
        final boolean actual = emailValidator.isValid("", null);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void inputCorrectEmail_whenCallIsValidPassingMailRu_thenOk(){
        final boolean expected = true;
        final boolean actual = emailValidator.isValid("correct@mail.ru", null);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void inputCorrectEmail_whenCallIsValidPassingGMailCom_thenOk(){
        final boolean expected = true;
        final boolean actual = emailValidator.isValid("correct@gmail.com", null);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void inputIncorrectEmail_whenCallIsValid_thenNotOk(){
        final boolean expected = false;
        final boolean actual = emailValidator.isValid("incorrectmail.com", null);

        Assert.assertEquals(expected, actual);
    }

}
