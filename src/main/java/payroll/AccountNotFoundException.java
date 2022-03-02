package payroll;

class AccountNotFoundException extends RuntimeException {

    AccountNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}