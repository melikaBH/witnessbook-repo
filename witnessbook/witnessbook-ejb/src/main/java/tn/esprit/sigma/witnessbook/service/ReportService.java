package tn.esprit.sigma.witnessbook.service;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.sigma.witnessbook.entities.Report;
import tn.esprit.sigma.witnessbook.interfaces.IReportServiceLocal;
import tn.esprit.sigma.witnessbook.interfaces.IReportServiceRemote;
import tn.esprit.sigma.witnessbook.service.AbstractService;

@Stateless
@Named("report")
public class ReportService extends AbstractService<Report> implements IReportServiceLocal,IReportServiceRemote{
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReportService() {
        super(Report.class);
    }

}
