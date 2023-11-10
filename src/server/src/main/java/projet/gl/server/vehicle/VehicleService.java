package projet.gl.server.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Optional<Vehicle> vehicleData = vehicleRepository.findById(id);

        if (vehicleData.isPresent()) {
            Vehicle updatedVehicle = vehicleData.get();
            updatedVehicle.setYearOfCreation(vehicle.getYearOfCreation());
            updatedVehicle.setPriceWithoutConfiguration(vehicle.getPriceWithoutConfiguration());
            updatedVehicle.setCreatedAt(vehicle.getCreatedAt());
            updatedVehicle.setUpdatedAt(vehicle.getUpdatedAt());
            updatedVehicle.setModel(vehicle.getModel());
            updatedVehicle.setColor(vehicle.getColor());
            updatedVehicle.setConfigurations(vehicle.getConfigurations());
            return vehicleRepository.save(updatedVehicle);
        } else {
            return null;
        }
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public List<Vehicle> findByFilters(VehiculeFilterDTO vehiculeFilterDTO) {
        return vehicleRepository.findByFilters(vehiculeFilterDTO);
    }

    public List<Object[]> findByFiltersByBrand(VehiculeFilterDTO vehiculeFilterDTO) {
        return vehicleRepository.findByFiltersByBrand(vehiculeFilterDTO);
    }

    public List<Object[]> findByFiltersByColor(VehiculeFilterDTO vehiculeFilterDTO) {
        return vehicleRepository.findByFiltersByColor(vehiculeFilterDTO);
    }

    public List<Object[]> findByFiltersByConfigurations(VehiculeFilterDTO vehiculeFilterDTO) {
        return vehicleRepository.findByFiltersByConfigurations(vehiculeFilterDTO);
    }

    public List<Object[]> findByFiltersByModel(VehiculeFilterDTO vehiculeFilterDTO) {
        return vehicleRepository.findByFiltersByModel(vehiculeFilterDTO);
    }

    public Page<Vehicle> findByFiltersAndPageSize(VehiculeFilterDTO vehiculeFilterDTO, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return vehicleRepository.findByFiltersAndPageSize(vehiculeFilterDTO, pageable);
    }

    public Page<Vehicle> getPaginatedData(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return vehicleRepository.findAll(pageable);
    }

    public long countVehicles() {
        return vehicleRepository.count();
    }

    public long countByFilter(VehiculeFilterDTO vehiculeFilterDTO) {
        return vehicleRepository.countByFilter(vehiculeFilterDTO);
    }

    public List<Object[]> countByModel() {
        return vehicleRepository.countVehiclesByModel();
    }

    public List<Object[]> countByBrand() {
        return vehicleRepository.countVehiclesByBrand();
    }


    public List<Object[]> countByBrandName() {
        return vehicleRepository.countVehiclesByBrandName();
    }

    public List<Object[]> countByColor() {
        return vehicleRepository.countVehiclesByColor();
    }
        
}
