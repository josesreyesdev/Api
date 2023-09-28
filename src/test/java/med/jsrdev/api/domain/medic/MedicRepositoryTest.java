package med.jsrdev.api.domain.medic;

import med.jsrdev.api.domain.address.MedicAddressData;
import med.jsrdev.api.domain.address.PatientAddressData;
import med.jsrdev.api.domain.consult.Consult;
import med.jsrdev.api.domain.patient.AddPatientData;
import med.jsrdev.api.domain.patient.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Java6Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles
class MedicRepositoryTest {

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deberia retornar nulo cuando el medico se encuentre en consulta con otro paciente en ese horario")
    void selectMedicWithSpecialtyInDateScenario1() {

        var nextMondayAt10am = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        // registrar un medico, un paciente y una consulta
        var medic = addMedic("Juan P", "juanp@example.com", "12443", Specialty.CARDIOLOGIA);
        var patient = addPatient("jose Juan", "josejuan@example.com", "12321");
        addConsult(medic, patient, nextMondayAt10am);

        // medico libre
        var freeDoctor = medicRepository.selectMedicWithSpecialtyInDate(Specialty.CARDIOLOGIA, nextMondayAt10am);

        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Deberia retornar veradero cuando el medico registrado se encuentre libre en el horario seleccionado")
    void selectMedicWithSpecialtyInDateScenario2() {

        var nextMondayAt10am = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        // registrar un medico
        var medic = addMedic("Juan P", "juanp@example.com", "12443", Specialty.CARDIOLOGIA);

        // medico libre
        var freeDoctor = medicRepository.selectMedicWithSpecialtyInDate(Specialty.CARDIOLOGIA, nextMondayAt10am);

        assertThat(freeDoctor).isEqualTo(medic);
    }

    private void addConsult(Medic medic, Patient patient, LocalDateTime date) {
        entityManager.persist(new Consult(null, medic, patient, date, null));
    }

    private Medic addMedic(String name, String email, String document, Specialty specialty) {
        var medic = new Medic(medicData(name, email, document, specialty));
        entityManager.persist(medic);
        return medic;
    }

    private Patient addPatient(String name, String email, String document) {
        var patient = new Patient(patientData(name, email, document));
        entityManager.persist(patient);
        return patient;
    }

    private AddMedicData medicData(String name, String email, String document, Specialty specialty) {
        return new AddMedicData(
                name, email, "1234123421", document, specialty, medicAddressData()
        );
    }

    private AddPatientData patientData(String name, String email, String document) {
        return new AddPatientData(
                name, email, "1234123421", document, patientAddressData()
        );
    }

    private MedicAddressData medicAddressData() {
        return new MedicAddressData(
                "street loca", "district 32", "City City", 23, "Complement 12"
        );
    }

    private PatientAddressData patientAddressData() {
        return new PatientAddressData(
                "Urban",
                "District 23",
                "14737",
                "Complement District 23",
                12,
                1212,
                "City central"
        );
    }
}