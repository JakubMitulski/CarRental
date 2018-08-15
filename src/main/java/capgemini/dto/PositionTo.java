package capgemini.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionTo {

    private Long id;
    private String name;

    public PositionTo() {
    }

    public PositionTo(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public static PositionToBuilder builder() {
        return new PositionToBuilder();
    }

    public static class PositionToBuilder {
        private Long id;
        private String name;

        public PositionToBuilder() {
            super();
        }

        public PositionToBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PositionToBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PositionTo build() {
            checkBeforeBuild(name);
            return new PositionTo(id, name);
        }

        private void checkBeforeBuild(String name) {
            if (name == null || name.isEmpty()) {
                throw new RuntimeException("Incorrect position_to be created");
            }
        }
    }
}
