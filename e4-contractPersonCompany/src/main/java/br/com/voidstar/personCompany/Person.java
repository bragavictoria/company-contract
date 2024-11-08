package br.com.voidstar.personCompany;

public class Person {
    public Company company;
    private String name;
    private String surname;
    private double salary;

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    private Contract contract;

    public Person() {
        this.name = "";
        this.surname = "";
        this.salary = 0.0;
        this.contract = null;
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.salary = 0.0;
        this.contract = null;
    }
    public Person(String name, String surname, double salary, Company company) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        if (company != null) {
            this.contract = new Contract(company, this, salary);
        } else {
            this.contract = null;
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getSalary() {
        return salary;
    }

    public Company getCompany() {
        return (contract != null) ? contract.getCompany() : null;
    }

    protected void selfHire(Contract contract) {
        if (contract != null){
            this.contract = contract;
            this.salary = contract.getSalary();
        }
    }

    public void selfHire(Company company, double salary) {
        if (contract == null || contract.getCompany() != company) {
            this.contract = new Contract(company, this, salary);
            this.salary = salary;
        }
    }

    public void hire (Person person, Company company, double salary){
        if (person.getContract() != null){
            throw new IllegalStateException("Essa pessoa já tem contrato.");
        }

        Contract contract = new Contract(company, person, salary);
        person.setContract(contract);
        person.setCompany(company);
        company.hire(contract);
    }

    protected void selfDismiss(Contract contract) {
        if (this.contract == contract){
            this.contract = null;
            this.salary = 0.0;
        }
    }

    public void selfDismiss(Company company) {
            if (contract != null && contract.getCompany() == company) {
                company.getContracts().remove(contract);
                this.contract = null;
                this.salary = 0.0;
            }
    }

    public void dismiss(Person person) {
        if (person.getContract() == null) {
            throw new IllegalArgumentException("Esta pessoa não tem contrato.");
        }

        Contract contract = person.getContract();
        company.dismiss(contract);
        person.setContract(null);
    }

    private void setContract(Object o) {
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
