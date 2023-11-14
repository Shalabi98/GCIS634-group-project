import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
//import static org.mockito.Mockito.*;
//import org.mockito.Mockito;

//java package manager, gradle

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PasswordTest {

    private Password password;

    @Before
    public void setUp() throws Exception {
        password = new Password("");
    }

    // testing charType()
    @Test
    //uppercase
    public void charType1() {
        int val = password.charType('A');
        Assert.assertEquals(1,val);
    }
    @Test
    //lowercase
    public void charType2() {
        int val = password.charType('a');
        Assert.assertEquals(2,val);
    }
    @Test
    //digit
    public void charType3() {
        int val = password.charType('0');
        Assert.assertEquals(3,val);
    }
    @Test
    //symbol
    public void charType4() {
        int val = password.charType('&');
        Assert.assertEquals(4,val);
    }

    //testing passwordStrength()
    @Test
    public void passwordStrengthScore6() {
        password = new Password("Aa1&Aa1&Aa1&Aa1&");
        int score = password.passwordStrength();

        //expected output
        int expectedScore = 6;

        //Asesertion
        Assert.assertEquals(expectedScore, score);
    }
    @Test
    public void passwordStrengthScore5() {
        password = new Password("Aa1&Aa1&Aa1&Aa");
        int score = password.passwordStrength();

        //expected output
        int expectedScore = 5;

        //Asesertion
        Assert.assertEquals(expectedScore, score);
    }
    @Test
    public void passwordStrengthScore4() {
        password = new Password("Aa1&");
        int score = password.passwordStrength();

        //expected output
        int expectedScore = 4;

        //Asesertion
        Assert.assertEquals(expectedScore, score);
    }
    @Test
    public void passwordStrengthScore3() {
        password = new Password("Aa1");
        int score = password.passwordStrength();

        //expected output
        int expectedScore = 3;

        //Asesertion
        Assert.assertEquals(expectedScore, score);
    }
    @Test
    public void passwordStrengthScore2() {
        password = new Password("Aa");
        int score = password.passwordStrength();

        //expected output
        int expectedScore = 2;

        //Asesertion
        Assert.assertEquals(expectedScore, score);
    }
    @Test
    public void passwordStrengthScore1() {
        password = new Password("A");
        int score = password.passwordStrength();

        //expected output
        int expectedScore = 1;

        //Asesertion
        Assert.assertEquals(expectedScore, score);
    }

    //testing calculateScore()
    @Test
    public void calculateScoreVeryStrong() {
        password = new Password("Aa1&Aa1&Aa1&Aa1&");
        String scoreResultMsg = password.calculateScore();

        //expected output
        String msg = "This is a very good password :D check the Useful Information section to make sure it satisfies the guidelines";

        //Asesertion
        Assert.assertEquals(msg, scoreResultMsg);
    }

    @Test
    public void calculateScoreStrong() {
        password = new Password("Aa1&Aa1&Aa1&");
        String scoreResultMsg = password.calculateScore();

        //expected output
        String msg = "This is a good password :) but you can still do better";

        //Asesertion
        Assert.assertEquals(msg, scoreResultMsg);
    }
    @Test
    public void calculateScoreMedium() {
        password = new Password("Aa1");
        String scoreResultMsg = password.calculateScore();

        //expected output
        String msg = "This is a medium password :/ try making it better";

        //Asesertion
        Assert.assertEquals(msg, scoreResultMsg);
    }
    @Test
    public void calculateScoreWeak() {
        password = new Password("Aa");
        String scoreResultMsg = password.calculateScore();

        //expected output
        String msg = "This is a weak password :( definitely find a new one";

        //Asesertion
        Assert.assertEquals(msg, scoreResultMsg);
    }
}