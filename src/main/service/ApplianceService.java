package main.service;

import main.DAO.ApplianceDAO;
import main.model.Appliance;
import main.model.Kettle;

import java.util.List;

public class ApplianceService {
    private final ApplianceDAO applianceDAO;

    public ApplianceService() {
        this.applianceDAO = new ApplianceDAO();
    }

    /**
     * <p>This method finds refers to the dao layer to find the cheapest product
     * </p>
     * @return the cheapest product
     */
    public Appliance getCheapestAppliance() {
        return applianceDAO.findCheapestAppliance();
    }

    /**
     * <p>This method finds refers to the dao layer to find all kettles
     * </p>
     * @return list of kettles
     */
    public List<Kettle> getKettles() {
        return applianceDAO.findKettles();
    }
}
