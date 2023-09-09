package med.jsrdev.api.medic;

public record MedicalDataList(
        String name,
        Specialty specialty,
        String document,
        String email
) {
    public MedicalDataList(Medic medic) {
        this(
                medic.getName(),
                medic.getSpecialty(),
                medic.getDocument(),
                medic.getEmail()
        );
    }
}
