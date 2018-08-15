package ua.training.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ADMIN")
public class Admin extends Employee {
    private String passportNumber;
    private String passportRegistration;

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportRegistration() {
        return passportRegistration;
    }

    public void setPassportRegistration(String passportRegistration) {
        this.passportRegistration = passportRegistration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Admin admin = (Admin) o;

        if (passportNumber != null ? !passportNumber.equals(admin.passportNumber) : admin.passportNumber != null)
            return false;
        return passportRegistration != null ? passportRegistration.equals(admin.passportRegistration)
                : admin.passportRegistration == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (passportNumber != null ? passportNumber.hashCode() : 0);
        result = 31 * result + (passportRegistration != null ? passportRegistration.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Admin{" +
                super.toString() +
                "passportNumber='" + passportNumber + '\'' +
                ", passportRegistration='" + passportRegistration + '\'' +
                '}';
    }

    public static final class AdminBuilder {
        private int id;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private String password;
        private ROLE role;
        private String passportNumber;
        private String passportRegistration;

        public AdminBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public AdminBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AdminBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public AdminBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public AdminBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public AdminBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public AdminBuilder setRole(ROLE role) {
            this.role = role;
            return this;
        }

        public AdminBuilder setPassportNumber(String passportNumber) {
            this.passportNumber = passportNumber;
            return this;
        }

        public AdminBuilder setPassportRegistration(String passportRegistration) {
            this.passportRegistration = passportRegistration;
            return this;
        }

        public Admin createAdmin() {
            Admin admin = new Admin();
            admin.setId(id);
            admin.setFirstName(firstName);
            admin.setLastName(lastName);
            admin.setPhoneNumber(phoneNumber);
            admin.setEmail(email);
            admin.setPassword(password);
            admin.setRole(role);
            admin.setPassportNumber(passportNumber);
            admin.setPassportRegistration(passportRegistration);
            return admin;
        }
    }
}
