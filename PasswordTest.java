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


    @Test
    public void passwordStrength() {

    }

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
        password = new Password("Aa1&Aa1&Aa1&Aa1&");
        String scoreResultMsg = password.calculateScore();

        //expected output
        String msg = "";

        //Asesertion
        Assert.assertEquals(msg, scoreResultMsg);
    }
    @Test
    public void calculateScoreWeak() {
        password = new Password("Aa");
        String scoreResultMsg = password.calculateScore();

        //expected output
        String msg = "";

        //Asesertion
        Assert.assertEquals(msg, scoreResultMsg);
    }
}