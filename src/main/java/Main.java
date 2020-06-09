import com.kosarev.dbconnection.domain.Customer;
import com.kosarev.dbconnection.domain.Developer;
import com.kosarev.dbconnection.domain.Project;
import com.kosarev.dbconnection.domain.Sex;
import com.kosarev.dbconnection.error.InternalException;
import com.kosarev.dbconnection.service.CustomerService;
import com.kosarev.dbconnection.service.DeveloperService;
import com.kosarev.dbconnection.service.ProjectService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.time.LocalDate;

@Log4j2
public class Main {

    private static final CustomerService customerService = new CustomerService();
    private static final ProjectService projectService = new ProjectService();
    private static final DeveloperService developerService = new DeveloperService();


    public static void main(String[] args) throws InternalException {

        customerService.addCustomer(new Customer("Apple", "Apple"));
        log.info("find customers by name: " + customerService.getCustomer("Apple"));
        customerService.addCustomer(new Customer("IBM", "IBM"));
        customerService.deleteCustomer(customerService.getCustomer("IBM"));
        customerService.editCustomer(customerService.getCustomer("Apple"), "Apple+", "Apple+");
        log.info("all customers: " + customerService.getAllCustomers());

        projectService.addProject(new Project("Apple to do", "to do", new BigDecimal(1000), LocalDate.of(2020, 01, 01)));
        log.info("find projects by name: " + projectService.getProject("Apple to do"));
        projectService.addProject(new Project("IBM to do", "to do", new BigDecimal(2000), LocalDate.of(2020, 01, 01)));
        projectService.deleteProject(projectService.getProject("IBM to do"));
        projectService.editProject(projectService.getProject("Apple to do"), "Apple to do +", "to do +", 5000, LocalDate.of(2020, 01, 01));
        log.info("all projects: " + projectService.getAllProjects());

        developerService.addDeveloper(new Developer("Petro", 40, Sex.MEN, new BigDecimal(4000)));
        log.info("find projects by name: " + developerService.getDeveloper("Petro"));
        developerService.addDeveloper(new Developer("Vas-Vas", 20, Sex.MEN, new BigDecimal(800)));
        developerService.deleteDeveloper(developerService.getDeveloper("Vas-Vas"));
        developerService.editDeveloper(developerService.getDeveloper("Petro"), "Petro +", 41, Sex.MEN, 4500);
        log.info("all projects: " + developerService.getAllDevelopers());




        log.info("Зарплата всех разработчиков отдельного проекта ('boots'): " +
                developerService.salaryFromAllDevelopersInProject("boots"));

        log.info("Cписок разработчиков отдельного проекта ('boots'): " +
                developerService.projectDevelopers("boots"));

        log.info("Cписок всех Java разработчиков: " +
                developerService.developersSkill("Java"));

        log.info("Cписок всех middle разработчиков: " +
                developerService.developersSkillLevel("Middle"));

        log.info("Cписок проектов в следующем формате: дата создания - название проекта - количество разработчиков на этом проекте: ");
        log.info(projectService.formatedProjectList());

    }

}
