package devoteam.checkpoint_eight.util;

import devoteam.checkpoint_eight.domain.Automaker;

public class AutomakerCreator {

        public static Automaker createAutomakerToBeSaved() {
            return Automaker.builder().name("BMW").build();
        }

        public static Automaker createValidAutomaker() {
            return Automaker.builder().name("BMW").id(1).build();
        }
        public static Automaker createValidUpdatedAutomaker() {
            return Automaker.builder().name("BMW").id(1).build();
        }
}
