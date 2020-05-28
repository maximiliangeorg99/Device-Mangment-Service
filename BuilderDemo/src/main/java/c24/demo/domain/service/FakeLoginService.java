package c24.demo.domain.service;

import c24.demo.domain.entity.User;
import c24.demo.persistance.InMemRepo;
import org.jusecase.inject.Component;

import javax.inject.Inject;

@Component
public class FakeLoginService {

    @Inject
    InMemRepo inMemRepo;

    public FakeLoginService() {
    }

    public void loginUser(final User user) {
        if (inMemRepo.find(user) != null)
            System.out.println("Hello " + user.getName());
        else
            System.out.println("Not Registered");
    }
}
