package util;

public class ValidationUtil {

	// validate name
	public static final boolean validateName(String firstName) {
	    // Check if the name is not empty
	    if (!firstName.isEmpty()) {
	        // Check if all characters in the string are letters
	        for (int i = 0; i < firstName.length(); i++) {
	            if (!Character.isLetter(firstName.charAt(i))) {
	                return false; // If any character is not a letter, return false
	            }
	        }
	        return true; // If all characters are letters, return true
	    }
	    return false; // If the name is empty, return false
	}
	
	// validate last  name
	public static final boolean validatelastName(String lastName) {
	    // Check if the name is not empty
	    if (!lastName.isEmpty()) {
	        // Check if all characters in the string are letters
	        for (int i = 0; i < lastName.length(); i++) {
	            if (!Character.isLetter(lastName.charAt(i))) {
	                return false; // If any character is not a letter, return false
	            }
	        }
	        return true; // If all characters are letters, return true
	    }
	    return false; // If the name is empty, return false
	}
	
	public static final boolean validatePhoneNumber(String phoneNumber) {
	    // Check if the phone number starts with a plus sign or a digit
	    if (!(phoneNumber.startsWith("+") || Character.isDigit(phoneNumber.charAt(0)))) {
	        return false; // If it doesn't start with '+' or a digit, return false
	    }
	    
	    int expectedLength;
	    if (phoneNumber.startsWith("+")) {
	        expectedLength = 14; // 1 for '+' and 10 for digits 
	    } else {
	        expectedLength = 10; // Only digits without '+'
	    }
	    
	    if (phoneNumber.length() != expectedLength) {
	        return false; // If the length is not as expected, return false
	    }
	    
	    // Check if the rest of the string contains only digits
	    for (int i = phoneNumber.startsWith("+") ? 1 : 0; i < phoneNumber.length(); i++) {
	        if (!Character.isDigit(phoneNumber.charAt(i))) {
	            return false; // If any character after the first one is not a digit, return false
	        }
	    }
	    
	    return true; // If all checks pass, return true
	}
	
	//Password validation
	public static final boolean validatePassword(String decryptedPwd) {
	    boolean isFirstCharUppercase = Character.isUpperCase(decryptedPwd.charAt(0));
	    boolean hasDigit = false;

	    // Check if the password contains at least one digit
	    for (int i = 1; i < decryptedPwd.length(); i++) {
	        if (Character.isDigit(decryptedPwd.charAt(i))) {
	            hasDigit = true;
	            break; // Exit the loop early if a digit is found
	        }
	    }

	    // Return true if the first character is uppercase and the password contains at least one digit
	    return isFirstCharUppercase && hasDigit;
	}
	
	/**
     * Validates if the provided text contains only letters and whitespace characters.
     * 
     * @param text The text to be validated.
     * @return True if the text contains only letters and whitespace, false otherwise.
     */
    public static boolean isTextOnly(String text) {
        return text.matches("[a-zA-Z\\s]+"); // Match letters and whitespace only
    }
	
	/**
     * Validates if the provided text contains only digits (0-9).
     * 
     * @param text The text to be validated.
     * @return True if the text contains only digits, false otherwise.
     */
    public static boolean isNumbersOnly(int number) {
        String text = Integer.toString(number);
        return text.matches("\\d+");
    }
}