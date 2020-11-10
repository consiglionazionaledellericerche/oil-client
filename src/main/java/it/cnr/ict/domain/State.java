package it.cnr.ict.domain;

public enum State {

    NUOVA(0),
    APERTA(1),
    IN_LAVORAZIONE(2),
    LAVORAZIONE_ESTERNA(3),
    CHIUSA(4),
    VERIFICATA(5),
    VALIDAZIONE_APERTA(6),
    IN_VALIDAZIONE(7);

    private final Integer value;

    private State(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
