package tn.esprit.sigma.witnessbook.resources;


import java.util.Set;
import javax.ws.rs.core.Application;


@javax.ws.rs.ApplicationPath("resources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }


    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(tn.esprit.sigma.witnessbook.resources.BadWordController.class);
        resources.add(tn.esprit.sigma.witnessbook.resources.CategoryController.class);
        resources.add(tn.esprit.sigma.witnessbook.resources.ChallengeController.class);
        resources.add(tn.esprit.sigma.witnessbook.resources.EventController.class);
        resources.add(tn.esprit.sigma.witnessbook.resources.ModeratorController.class);
        resources.add(tn.esprit.sigma.witnessbook.resources.NotificationController.class);
        resources.add(tn.esprit.sigma.witnessbook.resources.PostController.class);
        resources.add(tn.esprit.sigma.witnessbook.resources.ProductOwnerController.class);
        resources.add(tn.esprit.sigma.witnessbook.resources.ReportController.class);
        resources.add(tn.esprit.sigma.witnessbook.resources.UsersController.class);
        resources.add(tn.esprit.sigma.witnessbook.resources.WitnessCardController.class);
        resources.add(tn.esprit.sigma.witnessbook.resources.WitnessController.class);
    }
    
}
