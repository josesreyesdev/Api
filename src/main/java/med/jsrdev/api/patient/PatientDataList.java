package med.jsrdev.api.patient;

public record PatientDataList(Long id, String name, String email, String identityDocument) {
    public PatientDataList(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getIdentityDocument());
    }
}
