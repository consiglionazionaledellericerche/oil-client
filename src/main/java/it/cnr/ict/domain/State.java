package it.cnr.ict.domain;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Optional;

@JsonAdapter(State.Adapter.class)
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

    private static State fromValue(Integer integer) {
        for (State b : State.values()) {
            if (b.value.equals(integer)) {
                return b;
            }
        }
        return null;
    }

    public static class Adapter extends TypeAdapter<State> {
        @Override
        public void write(final JsonWriter jsonWriter, final State enumeration) throws IOException {
            jsonWriter.value(
                    Optional.ofNullable(enumeration)
                        .map(State::getValue)
                        .orElse(null)
            );
        }

        @Override
        public State read(final JsonReader jsonReader) throws IOException {
            return State.fromValue(jsonReader.nextInt());
        }
    }

}
