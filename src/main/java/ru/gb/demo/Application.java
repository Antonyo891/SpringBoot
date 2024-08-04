package ru.gb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.demo.api.UserController;
import ru.gb.demo.model.Role;
import ru.gb.demo.model.Roles;
import ru.gb.demo.model.User;
import ru.gb.demo.service.RoleService;
import ru.gb.demo.service.RoleServiceInterface;
import ru.gb.demo.service.UserService;
import ru.gb.demo.service.UserServiceInterface;

import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Application {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				SpringApplication.run(Application.class, args);
		UserController controller = context.getBean(UserController.class);
		System.out.println(controller.getUsers());
//		UserServiceInterface userService = context.getBean(UserService.class);
//		User admin = userService.findUser("admin");
//		User reader = userService.findUser("reader");
//		System.out.println(userService.update(admin));
//		System.out.println(userService.update(reader));
		RoleServiceInterface roleServiceInterface = context.getBean(RoleService.class);
//		Role adminRole= roleServiceInterface.findRole("admin").orElse(null);
//		Role readerRole= roleServiceInterface.findRole("reader").orElse(null);
//		roleServiceInterface.deleteAll();
		System.out.println(roleServiceInterface.findAll());
//		Role adminRole= roleServiceInterface.findRole(Roles.ADMIN);
//		System.out.println(adminRole);
//		System.out.println("***********************************");
//		adminRole.setName("admin");
//		Role readerRole= roleServiceInterface.findRole(Roles.READER);
//		readerRole.setName("reader");

//		System.out.println(readerRole);
//		User admin = userService.findUser("admin");
//		admin.setLogin("admin");
//		admin.setPassword("admin");
//        assert admin != null;
//        assert adminRole != null;
//        assert readerRole != null;
//        admin.setRoles(Set.of(adminRole,readerRole));
//		User reader = userService.findUser("reader");
//		reader.setLogin("reader");
//		reader.setPassword("reader");
//        assert reader != null;
//        reader.setRoles(Set.of(readerRole));
//		System.out.println(admin);
//		System.out.println(reader);
//		System.out.println(userService.findUser("admin"));
//		System.out.println(userService.findUser("reader"));
//		System.out.println("Reader: " +userService.findUser("reader").getRoles() +
//				"\n Admin: " + userService.findUser("admin")
//				.getRoles());

	}

}
