public class Alphabet {
	private final StringBuilder pool;

	public Alphabet(boolean uppercaseIncluded, boolean lowercaseIncluded, boolean numbersIncluded, boolean specialCharactersIncluded) {
		pool = new StringBuilder();

		if (uppercaseIncluded) pool.append(PasswordOptions.UPPERCASE_LETTERS);

		if (lowercaseIncluded) pool.append(PasswordOptions.LOWERCASE_LETTERS);

		if (numbersIncluded) pool.append(PasswordOptions.NUMBERS);

		if (specialCharactersIncluded) pool.append(PasswordOptions.SYMBOLS);
	}
	
	public String getAlphabet() {
		return pool.toString();
	}
}
