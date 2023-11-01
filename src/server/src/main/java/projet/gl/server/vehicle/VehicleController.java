package projet.gl.server.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import projet.gl.server.filters.Filters;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vehicules")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicules() {
        return ResponseEntity.ok().body(vehicleService.getAllVehicules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehiculeById(@PathVariable Long id) {
        return vehicleService.getVehiculeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicule(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok().body(vehicleService.createVehicule(vehicle));
    }

    @GetMapping("/Filters")
    public ResponseEntity<List<Vehicle>> getVehiculeByFilters(@RequestBody Filters filters){
        return ResponseEntity.ok().body(vehicleService.getVehiculeByFilters(filters));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicule(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.updateVehicule(id, vehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        vehicleService.deleteVehicule(id);
        return ResponseEntity.ok().build();
    }
}
