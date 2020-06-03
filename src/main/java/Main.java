import com.kosarev.dbconnection.domain.Customers;
import com.kosarev.dbconnection.domain.Developers;
import com.kosarev.dbconnection.domain.Projects;
import com.kosarev.dbconnection.domain.Sex;
import com.kosarev.dbconnection.repository.CustomersDAO;
import com.kosarev.dbconnection.service.CustomersService;
import com.kosarev.dbconnection.service.DevelopersService;
import com.kosarev.dbconnection.service.ProjectsService;

import java.time.LocalDate;

public class Main {

    private static final CustomersService customersService = new CustomersService();
    private static final ProjectsService projectsService = new ProjectsService();
    private static final DevelopersService developersService = new DevelopersService();

    public static void main(String[] args) {

        /*
        customersService.addCustomers(new Customers("Apple", "Apple"));
        System.out.println("find customers by name: " + customersService.getCustomers("Apple"));
        customersService.addCustomers(new Customers("IBM", "IBM"));
        customersService.deleteCustomers(customersService.getCustomers("IBM"));
        customersService.editCustomers(customersService.getCustomers("Apple"), "Apple+", "Apple+");
        System.out.println("all customers: " + customersService.getAllCustomers());

        projectsService.addProjects(new Projects("Apple to do", "to do", 1000, LocalDate.of(2020, 01, 01)));
        System.out.println("find projects by name: " + projectsService.getProjects("Apple to do"));
        projectsService.addProjects(new Projects("IBM to do", "to do", 2000, LocalDate.of(2020, 01, 01)));
        projectsService.deleteProjects(projectsService.getProjects("IBM to do"));
        projectsService.editProjects(projectsService.getProjects("Apple to do"), "Apple to do +", "to do +", 5000, LocalDate.of(2020, 01, 01));
        System.out.println("all projects: " + projectsService.getAllProjects());

        developersService.addDevelopers(new Developers("Petro", 40, Sex.MEN, 4000));
        System.out.println("find projects by name: " + developersService.getDevelopers("Petro"));
        developersService.addDevelopers(new Developers("Vas-Vas", 20, Sex.MEN, 800));
        developersService.deleteDevelopers(developersService.getDevelopers("Vas-Vas"));
        developersService.editDevelopers(developersService.getDevelopers("Petro"), "Petro +", 41, Sex.MEN, 4500);
        System.out.println("all projects: " + developersService.getAllDevelopers());
         */

        System.out.println("Зарплата всех разработчиков отдельного проекта ('boots'): " +
                developersService.salaryFromAllDevelopersInProject("boots"));

        System.out.println("Cписок разработчиков отдельного проекта ('boots'): " +
                developersService.projectDevelopers("boots"));

        System.out.println("Cписок всех Java разработчиков: " +
                developersService.developersSkill("Java"));

        System.out.println("Cписок всех middle разработчиков: " +
                developersService.developersSkillLevel("Middle"));

        System.out.println("Cписок проектов в следующем формате: дата создания - название проекта - количество разработчиков на этом проекте: ");
        System.out.println(projectsService.formatedProjectList());

    }

}
