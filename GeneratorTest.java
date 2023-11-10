import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

	private final Password password= new Password("Secret");
	private final Alphabet firstAlphabet = new Alphabet(true,false,false,false);
	private final Alphabet secondAlphabet = new Alphabet(false,true,true,true);
	private final Generator generator = new Generator(true,false,false,false);
//	private final Password generatedPassword = generator.GeneratePassword(4);
	
	@Test
	void test1() {
		assertEquals("Secret", password.toString());
	}

	@Test
	void test2() {
		assertEquals(firstAlphabet.getAlphabet(), PasswordOptions.UPPERCASE_LETTERS);
	}

	@Test
	void test3() {
		assertEquals(secondAlphabet.getAlphabet(), PasswordOptions.LOWERCASE_LETTERS + PasswordOptions.NUMBERS + PasswordOptions.SYMBOLS);
	}
	
	@Test
	void test4() {
		assertEquals(generator.alphabet.getAlphabet(), PasswordOptions.UPPERCASE_LETTERS);
	}
	
	@Test
	void test5() {
		assertEquals(generator.alphabet.getAlphabet().length(), 26);
	}

}
