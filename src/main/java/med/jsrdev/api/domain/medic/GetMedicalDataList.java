package med.jsrdev.api.domain.medic;

public record GetMedicalDataList(Long id, String name, Specialty specialty, String document, String email) {
    public GetMedicalDataList(Medic medic) {
        this(medic.getId(), medic.getName(), medic.getSpecialty(), medic.getDocument(), medic.getEmail());
    }
}
