package med.jsrdev.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.jsrdev.api.domain.address.MedicAddressData;
import med.jsrdev.api.domain.medic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medics")
public class MedicController {

    @Autowired
    private MedicRepository medicRepository;

    //register medicData
    @PostMapping("/register")
    public ResponseEntity<MedicDataResponse> addMedic(
            @RequestBody @Valid AddMedicData medicData, UriComponentsBuilder uri) {

        Medic medic = medicRepository.save(new Medic(medicData));

        /*
         * return 201 created
         * URL donde encontrar al medico
         * ej:  GET http://localhost:8080/medics/xx
         *  */

        URI url = uri.path("/medics/register/{id}").buildAndExpand(medic.getId()).toUri();
        return ResponseEntity.created(url).body(responseMedicData(medic));
    }

    @PostMapping("/register-medics")
    public ResponseEntity<MedicDataResponse> addMedicList(@RequestBody @Valid List<AddMedicData> medicList, UriComponentsBuilder uri) {

        /*medicList.forEach(medic -> medicRepository.save(new Medic(medic))); */
        for (AddMedicData addMedic : medicList) {
            Medic medic = medicRepository.save(new Medic(addMedic));
            URI url = uri.path("/medics/register-medics/{id}").buildAndExpand(medic.getId()).toUri();
            return ResponseEntity.created(url).body(responseMedicData(medic));
        }
        return null;
    }

    // Get all medics
    @GetMapping
    public ResponseEntity<Page<GetMedicalDataList>> getMedicList(@PageableDefault(size = 4) Pageable pagination)  {
        return ResponseEntity.ok(medicRepository.findAll(pagination).map(GetMedicalDataList::new));
    }

    // Get all medics active
    @GetMapping("/get-active")
    public ResponseEntity<Page<GetMedicalDataList>> getActiveMedicList(@PageableDefault(size = 4) Pageable pagination)  {
        return ResponseEntity.ok(medicRepository.findByActiveTrue(pagination).map(GetMedicalDataList::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicDataResponse> updateMedic(@RequestBody @Valid UpdateMedicData updateMedic) {
        Medic medic = medicRepository.getReferenceById(updateMedic.id());
        medic.updateMedic(updateMedic);

        return ResponseEntity.ok(responseMedicData(medic));
    }

    //Delete en BD => no recomendable, hay que tener un historico
    @DeleteMapping("/delete-in-db/{id}")
    @Transactional
    public ResponseEntity<Void> deleteLogicMedic(@PathVariable Long id) {
        Medic medic = medicRepository.getReferenceById(id);
        medicRepository.delete(medic);
        return ResponseEntity.noContent().build();
    }

    //Delete logic or exclusion logic
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deactivateMedic(@PathVariable Long id) {
        Medic medic = medicRepository.getReferenceById(id);
        medic.deactiveMedic();
        return ResponseEntity.noContent().build();
    }

    // Metodo que retorna los datos ingresados para un registro
    @GetMapping("/register/{id}")
    public ResponseEntity<MedicDataResponse> returnMedicData(@PathVariable Long id) {
        Medic medic = medicRepository.getReferenceById(id);

        return ResponseEntity.ok(responseMedicData(medic));
    }

    private MedicDataResponse responseMedicData(Medic medic) {
        return new MedicDataResponse(
                medic.getId(), medic.getName(), medic.getEmail(), medic.getPhone(),
                medic.getSpecialty(), medic.getDocument(),
                new MedicAddressData(
                        medic.getAddress().getStreet(),
                        medic.getAddress().getDistrict(),
                        medic.getAddress().getCity(),
                        medic.getAddress().getNumber(),
                        medic.getAddress().getComplement()
                )
        );
    }
}