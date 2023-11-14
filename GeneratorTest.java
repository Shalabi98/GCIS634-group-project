
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GeneratorTest {
	GeneratorTest() {
	}

	@Test
	void testRequestPasswordWithValidInput() {
		String input = "y\ny\ny\ny\n8\n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Generator generator = new Generator(new Scanner(System.in));
		Objects.requireNonNull(generator);
		Assertions.assertDoesNotThrow(generator::requestPassword);
	}

	@Test
	void testRequestPasswordWithInvalidLengthInput() {
		String input = "y\ny\ny\ny\n-2\n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Generator generator = new Generator(new Scanner(System.in));
		Objects.requireNonNull(generator);
		Assertions.assertThrows(IllegalArgumentException.class, generator::requestPassword);
	}

	@Test
	void testGeneratePassword() {
		Generator generator = new Generator(true, true, true, true);
		Password password = generator.generatePassword(12);
		Assertions.assertEquals(12, password.toString().length());
		Assertions.assertTrue(password.toString().matches("[A-Za-z0-9!@#$%^&*()_+\\-=[]{};':\",./<>?]*"));
	}

	@Test
	void testCheckPasswordStrength() {
		Generator generator = new Generator(new Scanner(System.in));
		String input = "TestPassword123!\n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Objects.requireNonNull(generator);
		Assertions.assertDoesNotThrow(generator::checkPassword);
	}
}
