package med.jsrdev.api.domain.patient;

public record GetPatientDataList(Long id, String name, String email, String identityDocument) {
    public GetPatientDataList(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getIdentityDocument());
    }
}
