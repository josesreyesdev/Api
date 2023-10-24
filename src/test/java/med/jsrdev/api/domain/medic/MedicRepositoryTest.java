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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest //Busca una DB en memoria que permite acceso a la BD y consultas
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Indico que BD utilizaré para pruebas, si utilizo una en fisico eliminar esta linea
@ActiveProfiles("test") //Indico que perfil utilizar, en este caso el de test
class MedicRepositoryTest {

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deberia retornar null cuando el medico se encuentre en consulta con otro paciente en ese horario")
    void selectMedicWithSpecialtyInDateScenario1() {

        // GIVEN
        var nexMondayAt10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0); // 10 am

        /* registrar un medico, paciente y una consulta */
        var medic = addMedic("Juan P", "juanp@example.com", "12443", Specialty.CARDIOLOGIA);
        var patient = addPatient("Paciente Prueba", "patienttest@example.com", "213242");
        addConsult(medic, patient, nexMondayAt10H);

        // WHEN
        // Ver  si el medico disponible
        var freeMedic = medicRepository.selectMedicWithSpecialtyInDate(
                Specialty.CARDIOLOGIA, nexMondayAt10H
        );

        // THEN
        assertThat(freeMedic).isNull();
    }

    @Test
    @DisplayName("Retorna un medico cuando si se encuentra disponible para ese horario")
    void selectMedicWithSpecialtyInDateScenario2() {

        // GIVEN
        var nexMondayAt10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0); // 10 am

        var medic = addMedic("Juan P", "juanp@example.com", "12443", Specialty.CARDIOLOGIA);

        // WHEN
        var freeMedic = medicRepository.selectMedicWithSpecialtyInDate(
                Specialty.CARDIOLOGIA, nexMondayAt10H
        );

        // THEN
        assertThat(freeMedic).isEqualTo(medic);
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
                "street loca",
                "district 32",
                "City City",
                23,
                "Complement 12"
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