package med.jsrdev.api.patient;

public record PatientDataList(String name, String email, String identityDocument) {
    public PatientDataList(Patient patient) {
        this(patient.getName(), patient.getEmail(), patient.getIdentityDocument());
    }
}
