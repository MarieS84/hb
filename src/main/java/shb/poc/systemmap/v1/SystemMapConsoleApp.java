package shb.poc.systemmap.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SystemMapConsoleApp implements ApplicationRunner {

	@Autowired
	SystemMapManagerImpl systemMapManagerImpl;

	@Autowired
	BusinessSystemRepository businessSystemRepository;

	@Autowired
	RuntimeRepository runtimeRepository;

	Scanner scanner = new Scanner(System.in);
	int choice;

	public static void main(String[] args) {

		ConfigurableApplicationContext appContext = SpringApplication.run(SystemMapConsoleApp.class, args);
	}
	//method to print the list of options
	public void printList(){
		List<String> options = new ArrayList<>(){{
			add("1. Create Business System");
			add("2. Create Runtime");
			add("3. Create Service");
			add("4. Create System Integrator");
			add("5. Delete Business System");
			add("6. Delete Runtime");
			add("7. Delete Service");
			add("8. Delete System Integrator");
			add("9. List Business Systems");
			add("10. Update Business System");
		}};
		for(String task : options){
			System.out.println(task);
		}
	}
	@Override
	public void run(ApplicationArguments args) throws Exception{
		String answer;
		do{
			printList();
			System.out.print("Choose task by entering number: ");
			choice = scanner.nextInt();
			scanner.nextLine();
			if(choice == 1){
				System.out.print("Business System Name: ");
				String name = scanner.nextLine();
				System.out.print("Business System Description: ");
				String description = scanner.nextLine();
				System.out.print("Description Url: ");
				String url = scanner.nextLine();
				systemMapManagerImpl.createBusinessSystem(name, description, url);
				System.out.println("Task completed");


			}else if(choice == 2){

				System.out.print("Which system do you want to create runtime for? Enter system id: ");

				int systemId = scanner.nextInt();
				Long id = Long.valueOf(systemId);
				if(businessSystemRepository.existsById(id)){
					scanner.nextLine();
					System.out.print("Runtime name: ");
					String name = scanner.nextLine();
					System.out.print("Runtime description: ");
					String description = scanner.nextLine();
					systemMapManagerImpl.createRuntime(id, name, description);
					System.out.println("Task completed");
				}else{
					System.out.println("Invalid system id. Try again.");
				}

			}else if(choice == 3){
				System.out.print("Which system do you want to create service for? Enter system id: ");
				int runtimeId = scanner.nextInt();
				scanner.nextLine();
				Long id = Long.valueOf(runtimeId);
				if(businessSystemRepository.existsById(id)) {
					System.out.print("Service name: ");
					String name = scanner.nextLine();
					System.out.print("Service description: ");
					String description = scanner.nextLine();
					systemMapManagerImpl.createService(id, name, description);
					System.out.println("Task completed");
				}else{
					System.out.println("Invalid system id. Try again.");
				}
			}else if(choice == 4){
				System.out.print("Which system do you want to create system integration for? Enter system id: ");
				int systemId = scanner.nextInt();
				scanner.nextLine();
				Long id = Long.valueOf(systemId);
				if(businessSystemRepository.existsById(id)) {
				System.out.print("System integration name: ");
				String name = scanner.nextLine();
				System.out.print("System integration description: ");
				String description = scanner.nextLine();
				System.out.print("Producer service (enter number): ");
				int producerService = scanner.nextInt();
				Long producerServ = Long.valueOf(producerService);
				System.out.print("Consumer service (enter number): ");
				int consumerService = scanner.nextInt();
				Long consumerServ = Long.valueOf(consumerService);
				systemMapManagerImpl.createSystemIntegration(id, name, description, producerServ, consumerServ);
				System.out.println("Task completed");
				}else{
					System.out.println("Invalid system id. Try again.");
				}
			}else if(choice == 5){
				systemMapManagerImpl.listBusinessSystems();
				System.out.print("Enter system id for the system you want to delete: ");
				int id = scanner.nextInt();
				Long systemId = Long.valueOf(id);
				if(businessSystemRepository.existsById(systemId)) {
				scanner.nextLine();
				systemMapManagerImpl.deleteBusinessSystem(systemId);
				System.out.println("Task completed");
				}else{
					System.out.println("Invalid system id. Try again.");
				}
			}else if(choice == 6){
				systemMapManagerImpl.listBusinessSystems();
				System.out.print("Enter system id for the runtime you want to delete: ");
				int id = scanner.nextInt();
				Long systemId = Long.valueOf(id);
				if(businessSystemRepository.existsById(systemId)) {
				scanner.nextLine();
				systemMapManagerImpl.deleteRuntime(systemId);
				System.out.println("Task completed");
				}else{
					System.out.println("Invalid system id. Try again.");
				}
			}else if(choice == 7){
				systemMapManagerImpl.listBusinessSystems();
				System.out.print("Enter system id for the service you want to delete: ");
				int id = scanner.nextInt();
				Long runtimeId = Long.valueOf(id);
				if(businessSystemRepository.existsById(runtimeId)) {
				scanner.nextLine();
				systemMapManagerImpl.deleteService(runtimeId);
				System.out.println("Task completed");
				}else{
					System.out.println("Invalid system id. Try again.");
				}
			}else if(choice == 8){
				systemMapManagerImpl.listBusinessSystems();
				System.out.print("Enter system id for the system integration you want to delete: ");
				int id = scanner.nextInt();
				Long systemId = Long.valueOf(id);
				if(businessSystemRepository.existsById(systemId)) {
				scanner.nextLine();
				systemMapManagerImpl.deleteSystemIntegration(systemId);
				System.out.println("Task completed");
				}else{
					System.out.println("Invalid system id. Try again.");
				}
			}else if(choice == 9){
				systemMapManagerImpl.listBusinessSystems();
			}else if(choice == 10){
				systemMapManagerImpl.listBusinessSystems();
				System.out.print("Enter system id for the system integration you want to update: ");
				int id = scanner.nextInt();
				Long systemId = Long.valueOf(id);
				if(businessSystemRepository.existsById(systemId)) {
					scanner.nextLine();
				System.out.print("What do you want to update? Name / Description: ");
				String updateItem = scanner.nextLine().toLowerCase();
				System.out.print("Enter updated value: ");
				String change = scanner.nextLine();
				systemMapManagerImpl.updateBusinessSystems(systemId, updateItem, change);
				}else{
					System.out.println("Invalid system id. Try again.");
				}
			}
			System.out.println("Go back to menu? y/n?");
			answer = scanner.next();
			if(!answer.toLowerCase().equals("y")){
				System.exit(1);
			}

		}while(answer.toLowerCase().equals("y"));


	}

}
