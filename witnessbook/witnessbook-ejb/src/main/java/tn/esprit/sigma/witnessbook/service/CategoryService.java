package tn.esprit.sigma.witnessbook.service;


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.sigma.witnessbook.entities.Category;
import tn.esprit.sigma.witnessbook.interfaces.ICategoryServiceLocal;
import tn.esprit.sigma.witnessbook.interfaces.ICategoryServiceRemote;

@Stateless
@Named("category")
public class CategoryService extends AbstractService<Category> implements ICategoryServiceRemote,ICategoryServiceLocal{
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
  

    public CategoryService() {
        super(Category.class);
    }
   
    public List<Category> findAllCategoryName(){
    	String jpql="SELECT c.name from Category c ";
    			
    			Query query =em.createQuery(jpql);
    			
    			return query.getResultList();
    }

}
