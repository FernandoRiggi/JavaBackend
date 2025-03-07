    package Ex01;

    public class Player {
        private String name;
        private int number;
        private String position;
        private boolean isFielded;

        public Player() {
        }

        public Player(String name, String position, int number, boolean isFielded) {
            this.name = name;
            this.position = position;
            this.number = number;
            this.isFielded = isFielded;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public boolean isFielded() {
            return isFielded;
        }

        public void setFielded(boolean fielded) {
            isFielded = fielded;
        }

        public String getStateAsString(){
            return "Name: " + name + ", Number: " + number + ", Position: " + position + ", isFielded: " + isFielded;
        }
    }
