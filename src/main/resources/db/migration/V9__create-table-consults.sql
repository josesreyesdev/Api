create table consults(
    id bigint not null auto_increment,
    id_medic bigint not null,
    id_patient bigint not null,
    date datetime not null,
    motivo_cancel varchar(200),

    primary key(id),

    constraint fk_consults_medic_id foreign key(id_medic) references medics(id),
    constraint fk_consults_patient_id foreign key(id_patient) references patients(id)
)