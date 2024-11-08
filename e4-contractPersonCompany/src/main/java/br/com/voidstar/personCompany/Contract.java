package br.com.voidstar.personCompany;

public class Contract {
    private final Company company;
    private final Person person;
    private final double salary;

    public Contract(Company company, Person person, double salary) {
        this.company = company;
        this.person = person;
        this.salary = salary;
    }

    public Company getCompany() {
        return company;
    }

    public Person getPerson() {
        return person;
    }

    public double getSalary() {
        return salary;
    }
}
